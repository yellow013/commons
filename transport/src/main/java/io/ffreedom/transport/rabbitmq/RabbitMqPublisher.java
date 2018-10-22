package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.log.UseLogger;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbitmq.RabbitMqOperatingTools.OperationalChannel;
import io.ffreedom.transport.rabbitmq.config.RmqPublisherConfigurator;

public class RabbitMqPublisher extends BaseRabbitMqTransport implements Publisher<byte[]> {

	// 发布消息使用的routingKey
	private String routingKey;

	// 发布消息使用的exchange
	private String exchange;

	// 发布消息使用的参数
	private BasicProperties msgProperties;

	private String publisherName;

	private String[] bindQueues;

	private String directQueue;

	private BuiltinExchangeType exchangeType;

	private boolean isOpenTx;

	private boolean isOpenConfirm;

	private Callback<Long> ackCallback;

	private Callback<Long> noAckCallback;

	/**
	 * 
	 * @param configurator
	 */
	public RabbitMqPublisher(String tag, RmqPublisherConfigurator configurator) {
		this(tag, configurator, null, null);
	}

	public RabbitMqPublisher(String tag, RmqPublisherConfigurator configurator, Callback<Long> ackCallback,
			Callback<Long> noAckCallback) {
		super(tag, configurator);
		this.exchange = configurator.getExchange();
		this.routingKey = configurator.getRoutingKey();
		this.msgProperties = configurator.getMsgProperties();
		this.bindQueues = configurator.getBindQueues();
		this.exchangeType = configurator.getExchangeType();
		this.directQueue = configurator.getDirectQueue();
		this.isOpenTx = configurator.isOpenTx();
		this.isOpenConfirm = configurator.isOpenConfirm();
		createConnection();
		init();
	}

	private void init() {
		this.publisherName = "Publisher->" + configurator.getHost() + ":" + configurator.getPort() + "$" + routingKey;
		try {
			OperationalChannel operationalChannel = RabbitMqOperatingTools.ofChannel(channel);
			switch (exchangeType) {
			case DIRECT:
				this.routingKey = directQueue;
				operationalChannel.declareQueue(directQueue, configurator.isDurable(), configurator.isExclusive(),
						configurator.isAutoDelete());
				break;
			case FANOUT:
				operationalChannel.declareFanoutExchange(exchange);
				if (bindQueues != null) {
					for (String queue : bindQueues) {
						if (!StringUtil.isNullOrEmpty(queue)) {
							operationalChannel.declareQueue(queue, configurator.isDurable(), configurator.isExclusive(),
									configurator.isAutoDelete());
							operationalChannel.bindQueue(queue, exchange);
						}
					}
				}
				break;
			case TOPIC:
				operationalChannel.declareTopicExchange(exchange);
				if (bindQueues != null) {
					for (String queue : bindQueues) {
						if (!StringUtil.isNullOrEmpty(queue)) {
							operationalChannel.declareQueue(queue, configurator.isDurable(), configurator.isExclusive(),
									configurator.isAutoDelete());
							operationalChannel.bindQueue(queue, exchange, routingKey);
						}
					}
				}
				break;
			default:
				break;
			}
		} catch (IOException e) {
			UseLogger.error(logger, e, "Call method init() throw IOException -> {}", e.getMessage());
			destroy();
		}
		if (isOpenConfirm) {
			try {
				channel.confirmSelect();
				channel.addConfirmListener((deliveryTag, multiple) -> {
					UseLogger.info(logger, "Ack Callback -> deliveryTag==[{}], multiple==[{}]", deliveryTag, multiple);
					if (ackCallback != null)
						ackCallback.onEvent(deliveryTag);
				}, (deliveryTag, multiple) -> {
					UseLogger.info(logger, "NoAck Callback -> deliveryTag==[{}], multiple==[{}]", deliveryTag,
							multiple);
					if (noAckCallback != null)
						noAckCallback.onEvent(deliveryTag);
				});
			} catch (IOException e) {
				UseLogger.error(logger, e, "init() Call method channel.confirmSelect() throw IOException -> {}",
						e.getMessage());
			}
		}
	}

	@Override
	public void publish(byte[] msg) {
		publish(routingKey, msg);
	}

	@Override
	public void publish(String target, byte[] msg) {
		try {
			// 记录重试次数
			int retry = 0;
			// 调用isConnected()检查channel和connection是否打开, 如果没有打开, 先销毁连接, 再重新创建连接.
			while (!isConnected()) {
				logger.error("Detect connection isConnected() == false, retry {}", (++retry));
				destroy();
				ThreadUtil.sleep(configurator.getRecoveryInterval());
				createConnection();
			}
			if (isOpenTx) {
				txPublish(target, msg);
			} else {
				basicPublish(target, msg);
			}
		} catch (IOException e) {
			UseLogger.error(logger, e, "Call method publish() isOpenTx==[{}] throw IOException -> {} ", isOpenTx,
					e.getMessage());
			destroy();
		}
	}

	private void basicPublish(String target, byte[] msg) throws IOException {
		try {
			channel.basicPublish(
					// param1: exchange
					exchange,
					// param2: routingKey
					target,
					// param3: properties
					msgProperties,
					// param4: msgBody
					msg);
		} catch (IOException e) {
			UseLogger.error(logger, e,
					"basicPublish() Call method channel.basicPublish(exchange==[{}], routingKey==[{}], properties==[{}], msg==[...]) throw IOException -> {}",
					exchange, target, msgProperties, e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	private void txPublish(String target, byte[] msg) throws IOException {
		try {
			channel.txSelect();
			channel.basicPublish(
					// param1: exchange
					exchange,
					// param2: routingKey
					target,
					// param3: properties
					msgProperties,
					// param4: msgBody
					msg);
			channel.txCommit();
		} catch (IOException e) {
			try {
				channel.txRollback();
			} catch (IOException te) {
				UseLogger.error(logger, e, "txPublish() Call method channel.txRollback() throw IOException -> {}",
						te.getMessage());
				throw new IOException(e.getMessage());
			}
			UseLogger.error(logger, e,
					"txPublish() Call method channel.txSelect() or channel.basicPublish(exchange==[{}], routingKey==[{}], properties==[{}], msg==[...]) or channel.txCommit() throw IOException -> {}",
					exchange, target, msgProperties, e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public boolean destroy() {
		logger.info("Call method -> RabbitPublisher.destroy()");
		closeConnection();
		return true;
	}

	@Override
	public String getName() {
		return publisherName;
	}

	public static void main(String[] args) {

		RabbitMqPublisher publisher = new RabbitMqPublisher("", RmqPublisherConfigurator.configuration()
				.setConnectionParam("", 5672).setUserParam("", "").setModeDirect("").setAutomaticRecovery(true));

		ThreadUtil.startNewThread(() -> {
			int count = 0;
			while (true) {
				ThreadUtil.sleep(5000);
				publisher.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
				System.out.println(count);
			}
		});

	}

}
