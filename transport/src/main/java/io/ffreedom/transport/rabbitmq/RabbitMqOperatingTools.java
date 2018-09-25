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
		private boolean isAutoClose;

		private OperationalChannel(String host, int port, String username, String password, boolean isAutoClose) {
			connectionFactory.setHost(host);
			connectionFactory.setPort(port);
			connectionFactory.setUsername(username);
			connectionFactory.setPassword(password);
			this.isAutoClose = isAutoClose;
			try {
				this.connection = connectionFactory.newConnection();
				this.channel = connection.createChannel();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 * @param String           -> queue name
		 * @param DefaultParameter -> isDurable == true, isExclusive == false,
		 *                         isAutoDelete == false
		 */
		public boolean declareQueueUseDefaultParameter(String queue) {
			return declareQueue(queue, true, false, false);
		}

		public boolean declareQueue(String queue, boolean isDurable, boolean isExclusive, boolean isAutoDelete) {
			try {
				channel.queueDeclare(queue, isDurable, isExclusive, isAutoDelete, null);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} finally {
				if (isAutoClose)
					autoClose();
			}
		}

		public boolean declareFanoutExchange(String exchange) {
			return declareExchange(exchange, BuiltinExchangeType.FANOUT);
		}

		public boolean declareDirectExchange(String exchange) {
			return declareExchange(exchange, BuiltinExchangeType.DIRECT);
		}

		private boolean declareExchange(String exchange, BuiltinExchangeType type) {
			try {
				channel.exchangeDeclare(exchange, type, true, false, false, null);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				if (isAutoClose)
					autoClose();
			}
		}

		public boolean bindQueue(String queue, String exchange) {
			return bindQueue(queue, exchange, "");
		}

		public boolean bindQueue(String queue, String exchange, String routingKey) {
			try {
				channel.queueBind(queue, exchange, routingKey);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				if (isAutoClose)
					autoClose();
			}
		}

		private void autoClose() {
			int retry = 0;
			while (close()) {
				retry++;
				if (retry == 5)
					break;
			}
		}

		public boolean isOpen() {
			return channel.isOpen();
		}

		public boolean close() {
			try {
				channel.close();
				connection.close();
				return true;
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	public static OperationalChannel autoCloseChannel(String host, int port, String username, String password) {
		return new OperationalChannel(host, port, username, password, true);
	}

	public static OperationalChannel manualCloseChannel(String host, int port, String username, String password) {
		return new OperationalChannel(host, port, username, password, false);
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

		OperationalChannel manualCloseChannel = manualCloseChannel("127.0.0.1", 5672, "guest", "guest");

		System.out.println(manualCloseChannel.isOpen());

		manualCloseChannel.declareFanoutExchange("MarketData");

		manualCloseChannel.close();

		System.out.println(manualCloseChannel.isOpen());
	}

}
