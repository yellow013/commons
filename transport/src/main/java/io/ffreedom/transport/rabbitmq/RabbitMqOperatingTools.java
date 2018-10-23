package io.ffreedom.transport.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public final class RabbitMqOperatingTools {

	public static class OperationalChannel {
		private ConnectionFactory connectionFactory;
		private Connection connection;
		private Channel channel;

		private OperationalChannel(String host, int port, String username, String password)
				throws IOException, TimeoutException {
			this.connectionFactory = new ConnectionFactory();
			connectionFactory.setHost(host);
			connectionFactory.setPort(port);
			connectionFactory.setUsername(username);
			connectionFactory.setPassword(password);
			this.connection = connectionFactory.newConnection();
			this.channel = connection.createChannel();
		}

		private OperationalChannel(Channel channel) {
			this.channel = channel;
		}

		/**
		 * 
		 * @param String           -> queue name
		 * @param DefaultParameter -> isDurable == true, isExclusive == false,
		 *                         isAutoDelete == false
		 * @throws IOException
		 */
		public boolean declareQueueUseDefaultParameter(String queue) throws IOException {
			return declareQueue(queue, true, false, false);
		}

		public boolean declareQueue(String queue, boolean isDurable, boolean isExclusive, boolean isAutoDelete)
				throws IOException {
			channel.queueDeclare(queue, isDurable, isExclusive, isAutoDelete, null);
			return true;
		}

		public boolean declareFanoutExchange(String exchange) throws IOException {
			return declareExchange(exchange, BuiltinExchangeType.FANOUT);
		}

		public boolean declareTopicExchange(String exchange) throws IOException {
			return declareExchange(exchange, BuiltinExchangeType.TOPIC);
		}

		public boolean declareDirectExchange(String exchange) throws IOException {
			return declareExchange(exchange, BuiltinExchangeType.DIRECT);
		}

		private boolean declareExchange(String exchange, BuiltinExchangeType type) throws IOException {
			channel.exchangeDeclare(exchange, type, true, false, false, null);
			return true;
		}

		public boolean bindQueue(String queue, String exchange) throws IOException {
			return bindQueue(queue, exchange, "");
		}

		public boolean bindQueue(String queue, String exchange, String routingKey) throws IOException {
			channel.queueBind(queue, exchange, routingKey);
			return true;
		}

		public boolean deleteQueue(String queue, boolean isForce) throws IOException {
			channel.queueDelete(queue, !isForce, !isForce);
			return true;
		}

		public boolean deleteExchange(String exchange, boolean isForce) throws IOException {
			channel.exchangeDelete(exchange, !isForce);
			return true;
		}

		public boolean isOpen() {
			return channel.isOpen();
		}

		public boolean close() throws IOException, TimeoutException {
			channel.close();
			connection.close();
			return true;
		}
	}

	public static OperationalChannel createChannel(String host, int port, String username, String password)
			throws IOException, TimeoutException {
		return new OperationalChannel(host, port, username, password);
	}

	public static OperationalChannel ofChannel(Channel channel) {
		return new OperationalChannel(channel);
	}

//	static class TestBean {
//		private long L;
//		private int I;
//		private double D;
//		private String S;
//
//		public long getL() {
//			return L;
//		}
//
//		public TestBean setL(long l) {
//			L = l;
//			return this;
//		}
//
//		public int getI() {
//			return I;
//		}
//
//		public TestBean setI(int i) {
//			I = i;
//			return this;
//		}
//
//		public double getD() {
//			return D;
//		}
//
//		public TestBean setD(double d) {
//			D = d;
//			return this;
//		}
//
//		public String getS() {
//			return S;
//		}
//
//		public TestBean setS(String s) {
//			S = s;
//			return this;
//		}
//
//	}

	public static void main(String[] args) {

//		MutableList<TestBean> list = new FastList<>();
//
//		list.add(new TestBean().setI(1).setD(3.0D).setL(2L).setS("AT"));
//		list.add(new TestBean().setI(1).setD(4.0D).setL(5L).setS("AT"));
//		list.add(new TestBean().setI(2).setD(3.0D).setL(5L).setS("AS"));
//		list.add(new TestBean().setI(2).setD(4.0D).setL(5L).setS("AT"));
//
//		list.collectDouble(bean -> {
//			return bean.getD();
//		}).distinct().forEach(d -> {
//			System.out.println(d * 2);
//		});
//
//		MutableList<TestBean> collect = list.distinctBy(TestBean::getS);
//
//		collect.forEach(bean -> {
//			System.out.println(bean.getS());
//		});

		OperationalChannel manualCloseChannel;
		try {
			manualCloseChannel = createChannel("127.0.0.1", 5672, "guest", "guest");
			System.out.println(manualCloseChannel.isOpen());
			manualCloseChannel.declareFanoutExchange("MarketData");
			manualCloseChannel.close();
			System.out.println(manualCloseChannel.isOpen());
		} catch (IOException | TimeoutException e) {

		}

	}

}
