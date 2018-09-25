package io.ffreedom.transport.rabbitmq;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.rabbitmq.config.RmqPublisherConfigurator;
import io.ffreedom.transport.rabbitmq.config.RmqReceiverConfigurator;

public class RabbitMqConnectTest {

	private static String host = "116.62.228.4";
	private static int port = 5672;
	private static String username = "reduser";
	private static String password = "Centos123";
	private static String queue0 = "test1";
	private static String queue1 = "test2";
	private static boolean automaticRecovery = true;

	public static void main(String[] args) {

		// PublisherConfigurator pubConfigurator0 =
		// PublisherConfigurator.configuration().setHost(host).setPort(port)
		// .setUsername(username).setPassword(password).setModeDirect(queue0).setAutomaticRecovery(automaticRecovery);

		RmqPublisherConfigurator pubConfigurator0 = RmqPublisherConfigurator.configuration()
				.setConnectionParam(host, port).setUserParam(username, password)
				.setModeFanoutAndBindQueues("TestExchange", new String[] { queue0, queue1 })
				.setAutomaticRecovery(automaticRecovery);

		RabbitMqPublisher publisher = new RabbitMqPublisher("PUB_TEST", pubConfigurator0);

		ThreadUtil.startNewThread(() -> {
			int count = 0;
			for (;;) {
				publisher.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
				System.out.println("Send msg -> " + count);
				ThreadUtil.sleep(3000);
			}
		});

		System.out.println(publisher.getName() + " statred....");

		RmqReceiverConfigurator recvConfigurator0 = RmqReceiverConfigurator.configuration()
				.setConnectionParam(host, port).setUserParam(username, password).setReceiveQueue(queue0)
				.setAutomaticRecovery(automaticRecovery);

		RabbitMqReceiver receiver0 = new RabbitMqReceiver("SUB_TEST", recvConfigurator0, (msg) -> {
			System.out.println("receiver_0 msg -> " + new String(msg, Charsets.UTF8));
		});

		RmqReceiverConfigurator recvConfigurator1 = RmqReceiverConfigurator.configuration()
				.setConnectionParam(host, port).setUserParam(username, password).setReceiveQueue(queue1)
				.setAutomaticRecovery(automaticRecovery);

		RabbitMqReceiver receiver1 = new RabbitMqReceiver("SUB_TEST", recvConfigurator1, (msg) -> {
			System.out.println("receiver_1 msg -> " + new String(msg, Charsets.UTF8));
		});

		receiver0.receive();

		receiver1.receive();

		System.out.println(receiver0.getName() + " statred....");

		System.out.println(receiver1.getName() + " statred....");

	}

}
