package org.beam.transport.rabbit;

import java.io.IOException;

import org.beam.common.callback.Callback;
import org.beam.common.charsets.Charsets;
import org.beam.transport.base.role.Subscriber;
import org.beam.transport.rabbit.config.RabbitConfigurator;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitSubscriber extends BaseRabbitTransport implements Subscriber {

	// 接收消息时使用的回调函数
	private Callback<byte[]> callback;

	private String subscriberName;

	/**
	 * 
	 * @param configurator
	 * @param callback
	 */
	public RabbitSubscriber(String tag, RabbitConfigurator configurator, Callback<byte[]> callback) {
		super(tag, configurator);
		this.callback = callback;
		this.subscriberName = "Sub->" + configurator.host() + ":" + configurator.port() + "$" + configurator.queue();
		createConnection();
	}

	@Override
	public void subscribe() {
		try {
			// param1: queue
			// param2: autoAck
			// param3: consumeCallback
			channel.basicConsume(configurator.queue(), configurator.autoAck(), new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					try {
						callback.onEvent(body);
					} catch (Exception e) {
						logger.error(e.getMessage());
						logger.error(e.getStackTrace());
						// 退回消息->关闭连接
						logger.info("Reject Msg : " + new String(body, Charsets.UTF8));
						channel.basicReject(envelope.getDeliveryTag(), true);
						destroy();
					}
					try {
						int ack = 0;
						while (!channel.isOpen()) {
							logger.error("channel.isOpen() == false, ack " + (++ack));
							destroy();
							createConnection();
							try {
								Thread.sleep(configurator.recoveryInterval());
							} catch (InterruptedException e) {
								logger.error(e.getMessage());
								logger.error(e.getStackTrace());
							}
						}
						channel.basicAck(envelope.getDeliveryTag(), false);
					} catch (IOException e) {
						logger.error(e.getMessage());
						logger.error(e.getStackTrace());
					} finally {
						
					}
				}
			});
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void destroy() {
		logger.debug("call method -> RabbitSubscriber.destroy()");
		closeConnection();
	}

	@Override
	public String getSubscriberName() {
		return subscriberName;
	}

	public static void main(String[] args) {

		RabbitConfigurator configurator = RabbitConfigurator.builder().setHost("192.168.1.152").setPort(5672)
				.setUsername("jetmq").setPassword("root").setQueue("hello").setAutomaticRecovery(true).build();

		RabbitSubscriber subscriber = new RabbitSubscriber("TEST_SUB", configurator, (byte[] msg) -> {
			System.out.println(new String(msg, Charsets.UTF8));
		});

		subscriber.subscribe();

	}

}
