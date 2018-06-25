package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbitmq.config.RabbitMqConfigurator;

public class RabbitMqPublisher extends BaseRabbitMqTransport implements Publisher<byte[]> {

	// 发布消息使用的routingKey
	private String routingKey;

	// 发布消息使用的exchange
	private String exchange;

	private String publisherName;

	/**
	 * 
	 * @param configurator
	 */
	public RabbitMqPublisher(String tag, RabbitMqConfigurator configurator) {
		super(tag, configurator);
		this.exchange = configurator.getPubExchange();
		this.routingKey = StringUtil.isNullOrEmpty(configurator.getPubRoutingKey()) ? configurator.getQueue()
				: configurator.getPubRoutingKey();
		this.publisherName = "Pub->" + configurator.getHost() + ":" + configurator.getPort() + "$" + routingKey;
		createConnection();
		init();
	}

	private void init() {
		
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
			channel.basicPublish(exchange, target, null, msg);
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

		RabbitMqConfigurator configurator = RabbitMqConfigurator.builder().setHost("192.168.1.152").setPort(5672)
				.setUsername("thadmq").setPassword("root").setQueue("hello").setAutomaticRecovery(true).build();

		RabbitMqPublisher rabbitMqRequester = new RabbitMqPublisher("TEST_PUB", configurator);

		ThreadUtil.startNewThread(() -> {
			int count = 0;
			while (true) {
				ThreadUtil.sleep(5000);
				rabbitMqRequester.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
				System.out.println(count);
			}
		});

	}

}
