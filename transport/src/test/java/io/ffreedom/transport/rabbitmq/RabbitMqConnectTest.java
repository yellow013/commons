package io.ffreedom.transport.rabbitmq;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.rabbit.config.RabbitConfigurator;

public class RabbitMqConnectTest {

	// 鲁证cole: 58.246.96.190:5672
	// 广发cole: 222.66.0.195:62102
	// 建信期货: 118.126.16.205
	private static String host = "192.168.1.48";
	private static int port = 5672;
	private static String username = "mq_user";
	private static String password = "mquser";
	private static String queue0 = "test1";
	private static String queue1 = "test2";
	private static boolean automaticRecovery = true;

	public static void main(String[] args) {

		RabbitConfigurator configurator0 = RabbitConfigurator.builder().setHost(host).setPort(port)
				.setUsername(username).setPassword(password).setQueue(queue0).setAutomaticRecovery(automaticRecovery)
				.build();

		RabbitMqPublisher publisher = new RabbitMqPublisher("PUB_TEST", configurator0);

		ThreadUtil.startNewThread(() -> {
			int count = 0;
			for (;;) {
				publisher.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
				System.out.println("Send msg -> " + count);
				ThreadUtil.sleep(3000);
			}
		});

		System.out.println(publisher.getName() + " statred....");

		RabbitConfigurator configurator1 = RabbitConfigurator.builder().setHost(host).setPort(port)
				.setUsername(username).setPassword(password).setQueue(queue1).setAutomaticRecovery(automaticRecovery)
				.build();

		RabbitMqSubscriber subscriber = new RabbitMqSubscriber("SUB_TEST", configurator1, (msg) -> {
			System.out.println("Recv msg -> " + new String(msg, Charsets.UTF8));
		});

		subscriber.subscribe();

		System.out.println(subscriber.getName() + " statred....");
		
	}

}
