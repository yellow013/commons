package io.ffreedom.transport.rabbit;

import java.io.IOException;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbit.config.RabbitConfigurator;

public class RabbitPublisher extends BaseRabbitTransport implements Publisher<byte[]> {

	// 发布消息使用的routingKey
	private String routingKey;

	private String publisherName;

	/**
	 * 
	 * @param configurator
	 */
	public RabbitPublisher(String tag, RabbitConfigurator configurator) {
		super(tag, configurator);
		this.routingKey = StringUtil.isNullOrEmpty(configurator.getRoutingKey()) ? configurator.getQueue()
				: configurator.getRoutingKey();
		this.publisherName = "Pub->" + configurator.getHost() + ":" + configurator.getPort() + "$" + routingKey;
		createConnection();
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
			channel.basicPublish(configurator.getExchange(), target, null, msg);
		} catch (IOException e) {
			logger.error("Channel#basicPublish -> " + e.getMessage());
			logger.error(e.getStackTrace());
			destroy();
		}
	}

	@Override
	public void destroy() {
		logger.info("call method -> RabbitPublisher.destroy()");
		closeConnection();
	}

	@Override
	public String getName() {
		return publisherName;
	}

	public static void main(String[] args) {

		RabbitConfigurator configurator = RabbitConfigurator.builder().setHost("192.168.1.152").setPort(5672)
				.setUsername("thadmq").setPassword("root").setQueue("hello").setAutomaticRecovery(true).build();

		RabbitPublisher rabbitMqRequester = new RabbitPublisher("TEST_PUB", configurator);

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
