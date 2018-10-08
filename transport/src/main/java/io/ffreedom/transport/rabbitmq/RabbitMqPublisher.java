package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;

import io.ffreedom.common.charset.Charsets;
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

	/**
	 * 
	 * @param configurator
	 */
	public RabbitMqPublisher(String tag, RmqPublisherConfigurator configurator) {
		super(tag, configurator);
		this.exchange = configurator.getExchange();
		this.routingKey = configurator.getRoutingKey();
		this.msgProperties = configurator.getMsgProperties();
		this.bindQueues = configurator.getBindQueues();
		this.exchangeType = configurator.getExchangeType();
		this.directQueue = configurator.getDirectQueue();
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
			logger.error("throws IOException ->" + e.getMessage(), e);
			logger.error("call destroy() method.");
			destroy();
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
				logger.error("isConnected() == false, retry " + (++retry));
				destroy();
				createConnection();
				ThreadUtil.sleep(configurator.getRecoveryInterval());
			}
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
			logger.error("channel#basicPublish -> " + e.getMessage());
			logger.error("throws IOException -> ", e);
			destroy();
		}
	}

	@Override
	public boolean destroy() {
		logger.info("call method -> RabbitPublisher.destroy()");
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
