package io.ffreedom.transport.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public final class RabbitMqOperatingTools {

	public static class OperationalChannel {
		private ConnectionFactory connectionFactory = new ConnectionFactory();
		private Connection connection;
		private Channel channel;

		private OperationalChannel(String host, int port, String username, String password) {
			connectionFactory.setHost(host);
			connectionFactory.setPort(port);
			connectionFactory.setUsername(username);
			connectionFactory.setPassword(password);
			try {
				this.connection = connectionFactory.newConnection();
				this.channel = connection.createChannel();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
			
			try {
				channel.exchangeDeclare("", BuiltinExchangeType.FANOUT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static OperationalChannel openChannel(String host, int port, String username, String password) {
		return new OperationalChannel(host, port, username, password);
	}

}
