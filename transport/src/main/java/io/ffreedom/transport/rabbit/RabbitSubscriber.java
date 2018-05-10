package io.ffreedom.transport.rabbit;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import io.ffreedom.common.callback.Callback;
import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Subscriber;
import io.ffreedom.transport.rabbit.config.RabbitConfigurator;

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
		this.subscriberName = "Sub->" + configurator.getHost() + ":" + configurator.getPort() + "$" + configurator.getQueue();
		createConnection();
	}

	@Override
	public void subscribe() {
		try {
			// param1: queue
			// param2: autoAck
			// param3: consumeCallback
			channel.basicConsume(configurator.getQueue(), configurator.isAutoAck(), new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					try {
						callback.onEvent(body);
					} catch (Exception e) {
						logger.error("Callback#onEvent -> " + e.getMessage());
						logger.error(e.getStackTrace());
						// 退回消息->关闭连接
						logger.info("Reject Msg : " + new String(body, Charsets.UTF8));
						channel.basicReject(envelope.getDeliveryTag(), true);
						destroy();
					}
					try {
						int ack = 0;
						while (!isConnected()) {
							logger.error("isConnected() == false, ack " + (++ack));
							destroy();
							createConnection();
							ThreadUtil.sleep(configurator.getRecoveryInterval());
						}
						channel.basicAck(envelope.getDeliveryTag(), false);
					} catch (IOException e) {
						logger.error("Channel#basicAck() -> " + e.getMessage());
						logger.error(e.getStackTrace());
					}
				}
			});
		} catch (IOException e) {
			logger.error("Channel#basicConsume() -> " + e.getMessage());
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void destroy() {
		logger.info("call method -> RabbitSubscriber.destroy()");
		closeConnection();
	}

	@Override
	public String getName() {
		return subscriberName;
	}

	public static void main(String[] args) {

		RabbitConfigurator configurator = RabbitConfigurator.builder().setHost("192.168.1.152").setPort(5672)
				.setUsername("thadmq").setPassword("root").setQueue("hello").setAutomaticRecovery(true).build();

		RabbitSubscriber subscriber = new RabbitSubscriber("TEST_SUB", configurator, (byte[] msg) -> {
			System.out.println(new String(msg, Charsets.UTF8));
		});

		subscriber.subscribe();

	}

}
