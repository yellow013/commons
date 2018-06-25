package io.ffreedom.transport.rabbitmq;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.rabbitmq.config.RabbitMqConfigurator;

public class RabbitMqConnectTest {

	private static String host = "192.168.1.48";
	private static int port = 5672;
	private static String username = "mq_user";
	private static String password = "mquser";
	private static String queue0 = "test1";
	private static String queue1 = "test2";
	private static boolean automaticRecovery = true;

	public static void main(String[] args) {

		RabbitMqConfigurator configurator0 = RabbitMqConfigurator.publisherBuilder().setHost(host).setPort(port)
				.setUsername(username).setPassword(password).setQueues(queue0).setAutomaticRecovery(automaticRecovery)
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

		RabbitMqConfigurator configurator1 = RabbitMqConfigurator.receiverBuilder().setHost(host).setPort(port)
				.setUsername(username).setPassword(password).setReceiveQueue(queue1).setAutomaticRecovery(automaticRecovery)
				.build();

		RabbitMqReceiver receiver = new RabbitMqReceiver("SUB_TEST", configurator1, (msg) -> {
			System.out.println("Recv msg -> " + new String(msg, Charsets.UTF8));
		});

		receiver.receive();

		System.out.println(receiver.getName() + " statred....");
		
	}

}
