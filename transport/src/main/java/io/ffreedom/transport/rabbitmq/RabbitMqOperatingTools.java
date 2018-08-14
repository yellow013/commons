package io.ffreedom.transport.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

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

	static class TestBean {
		private long L;
		private int I;
		private double D;
		private String S;

		public long getL() {
			return L;
		}

		public TestBean setL(long l) {
			L = l;
			return this;
		}

		public int getI() {
			return I;
		}

		public TestBean setI(int i) {
			I = i;
			return this;
		}

		public double getD() {
			return D;
		}

		public TestBean setD(double d) {
			D = d;
			return this;
		}

		public String getS() {
			return S;
		}

		public TestBean setS(String s) {
			S = s;
			return this;
		}

	}

	public static void main(String[] args) {

		MutableList<TestBean> list = new FastList<>();

		list.add(new TestBean().setI(1).setD(3.0D).setL(2L).setS("AT"));
		list.add(new TestBean().setI(1).setD(4.0D).setL(5L).setS("AT"));
		list.add(new TestBean().setI(2).setD(3.0D).setL(5L).setS("AS"));
		list.add(new TestBean().setI(2).setD(4.0D).setL(5L).setS("AT"));

		list.collectDouble(bean -> {
			return bean.getD();
		}).forEach(d -> {
			System.out.println(d);
		});
	}

}
