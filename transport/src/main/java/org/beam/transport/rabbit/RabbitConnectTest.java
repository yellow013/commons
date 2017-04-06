package org.beam.transport.rabbit;

import org.beam.common.charsets.Charsets;
import org.beam.transport.rabbit.config.RabbitConfigurator;

public class RabbitConnectTest {

	// 鲁证cole: 58.246.96.190:5672
	// 广发cole: 222.66.0.195:62102

	private static String host = "192.168.1.109";
	private static int port = 5672;
	private static String username = "jetmq";
	private static String password = "root";
	private static String queue = "ServerToJet999";
	private static boolean automaticRecovery = true;

	public static void main(String[] args) {

		RabbitConfigurator configurator = RabbitConfigurator.builder().setHost(host).setPort(port).setUsername(username)
				.setPassword(password).setQueue(queue).setAutomaticRecovery(automaticRecovery).build();

		RabbitPublisher publisher = new RabbitPublisher("PUB_TEST", configurator);

		new Thread(() -> {
			int count = 0;
			for (;;) {
				try {
					publisher.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		RabbitSubscriber subscriber = new RabbitSubscriber("SUB_TEST", configurator, (msg) -> {
			System.out.println("Recv -> " + new String(msg, Charsets.UTF8));
		});

		subscriber.subscribe();

		System.out.println(subscriber.getSubscriberName() + " statred....");

	}

}
