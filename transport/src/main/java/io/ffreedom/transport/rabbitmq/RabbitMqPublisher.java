package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbitmq.config.PublisherConfigurator;

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
	public RabbitMqPublisher(String tag, PublisherConfigurator configurator) {
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
		try {
			switch (exchangeType) {
			case DIRECT:
				this.routingKey = directQueue;
				channel.queueDeclare(directQueue, configurator.isDurable(), configurator.isExclusive(),
						configurator.isAutoDelete(), null);
				break;
			case FANOUT:
				channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
				for (String queue : bindQueues) {
					channel.queueDeclare(queue, configurator.isDurable(), configurator.isExclusive(),
							configurator.isAutoDelete(), null);
					channel.queueBind(queue, exchange, routingKey);
				}
				break;
			case TOPIC:
				// TODO 扩展TOPIC模式
				break;
			default:
				break;
			}

		} catch (IOException e) {
			logger.error("IOException ->" + e.getMessage());
			logger.error(e.getStackTrace());
			e.printStackTrace();
			destroy();
			logger.error("call destroy() method.");
		}
		this.publisherName = "Publisher->" + configurator.getHost() + ":" + configurator.getPort() + "$" + routingKey;
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
			// param1: exchange
			// param2: routingKey
			// param3: properties
			// param4: msgBody
			channel.basicPublish(exchange, target, msgProperties, msg);
		} catch (IOException e) {
			logger.error("Channel#basicPublish -> " + e.getMessage());
			logger.error(e.getStackTrace());
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

		PublisherConfigurator configurator = PublisherConfigurator.configuration().setHost("192.168.1.152")
				.setPort(5672).setUsername("thadmq").setPassword("root").setDirectMode("hello")
				.setAutomaticRecovery(true);

		RabbitMqPublisher publisher = new RabbitMqPublisher("TEST_PUB", configurator);

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
