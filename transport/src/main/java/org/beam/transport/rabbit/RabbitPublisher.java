package org.beam.transport.rabbit;

import java.io.IOException;

import org.beam.common.charsets.Charsets;
import org.beam.common.utils.StringUtil;
import org.beam.transport.base.role.Publisher;
import org.beam.transport.rabbit.config.RabbitConfigurator;

public class RabbitPublisher extends BaseRabbitTransport implements Publisher<byte[]> {

	// 发布消息使用的routingKey
	private String routingKey;

	// 记录重试次数
	private int retry = 0;

	private String publisherName;

	/**
	 * 
	 * @param configurator
	 */
	public RabbitPublisher(String tag, RabbitConfigurator configurator) {
		super(tag, configurator);
		this.routingKey = StringUtil.isNullOrEmpty(configurator.routingKey()) ? configurator.queue()
				: configurator.routingKey();
		this.publisherName = "Pub->" + configurator.host() + ":" + configurator.port() + "$" + routingKey;
		createConnection();
	}

	@Override
	public void publish(byte[] msg) {
		publish(routingKey, msg);
	}

	@Override
	public void publish(String target, byte[] msg) {
		try {
			// 检查channel是否打开,如果没有打开,先销毁连接,再重新创建连接.
			while (!channel.isOpen()) {
				logger.error("channel.isOpen() == false, retry " + (++retry));
				destroy();
				createConnection();
				Thread.sleep(configurator.recoveryInterval());
			}
			// param1: exchange
			// param2: routingKey
			// param3: properties
			// param4: msgBody
			channel.basicPublish(configurator.exchange(), target, null, msg);
			this.retry = 0;
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
			destroy();
		} catch (InterruptedException e) {
			logger.error("Thread.sleep() : " + e.getMessage());
		}
	}

	@Override
	public void destroy() {
		logger.debug("call method -> RabbitPublisher.destroy()");
		closeConnection();
	}

	@Override
	public String getPublisherName() {
		return publisherName;
	}

	
	
	
	public static void main(String[] args) {

		RabbitConfigurator configurator = RabbitConfigurator.builder().setHost("192.168.1.152").setPort(5672)
				.setUsername("jetmq").setPassword("root").setQueue("hello").setAutomaticRecovery(true).build();

		RabbitPublisher rabbitMqRequester = new RabbitPublisher("TEST_PUB", configurator);

		new Thread(() -> {
			int count = 0;
			try {
				while (true) {
					Thread.sleep(5000);
					rabbitMqRequester.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
					System.out.println(count);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}

	

}
