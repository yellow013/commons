package io.ffreedom.transport.rabbitmq.config;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.MessageProperties;

import io.ffreedom.common.functional.ShutdownEvent;
import io.ffreedom.transport.base.config.TransportConfigurator;

public class RabbitMqConfigurator implements TransportConfigurator {

	/**
	 * 连接参数
	 */
	private String host;
	private int port;
	private String username;
	private String password;
	// 连接超时时间
	private int connectionTimeout;
	// 握手通信超时时间
	private int handshakeTimeout;
	// 关闭超时时间
	private int shutdownTimeout;
	// 请求心跳超时时间
	private int requestedHeartbeat;
	// 自动恢复连接
	private boolean automaticRecovery;
	// 重试连接间隔
	private long recoveryInterval;

	/**
	 * 队列定义参数
	 */
	// 队列持久化
	private boolean durable;
	// 连接独占此队列
	private boolean exclusive;
	// channel关闭后自动删除队列
	private boolean autoDelete;

	// 停机处理回调函数
	private ShutdownEvent<Exception> shutdownEvent;

	/**
	 * 发布者参数
	 */
	private String exchange;
	private String routingKey;
	private String[] queues;
	private BasicProperties msgProperties;

	/**
	 * 接收者参数
	 */
	private String receiveQueue;
	// 自动ACK
	private boolean autoAck;
	// 最大自动重试次数
	private int maxAckTotal;

	private String configuratorName;

	private RabbitMqConfigurator(Builder builder) {
		super();
		// 设置连接信息
		this.host = builder.host;
		this.port = builder.port;
		this.username = builder.username;
		this.password = builder.password;
		this.connectionTimeout = builder.connectionTimeout;
		this.handshakeTimeout = builder.handshakeTimeout;
		this.shutdownTimeout = builder.shutdownTimeout;
		this.requestedHeartbeat = builder.requestedHeartbeat;
		this.automaticRecovery = builder.automaticRecovery;
		this.recoveryInterval = builder.recoveryInterval;
		// 设置队列定义信息
		this.durable = builder.durable;
		this.exclusive = builder.exclusive;
		this.autoDelete = builder.autoDelete;
		// 停机回调
		this.shutdownEvent = builder.shutdownEvent;
		// 发布者信息
		this.exchange = builder.exchange;
		this.routingKey = builder.routingKey;
		this.queues = builder.queues;
		this.msgProperties = builder.msgProperties;
		// 接收者信息
		this.receiveQueue = builder.receiveQueue;
		this.autoAck = builder.autoAck;
		this.maxAckTotal = builder.maxAckTotal;
		// 配置器名称
		this.configuratorName = builder.configuratorName;
	}

	public static Builder publisherBuilder() {
		return new Builder("RabbitMqPublisherConfigurator");
	}

	public static Builder receiverBuilder() {
		return new Builder("RabbitMqReceiverConfigurator");
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isDurable() {
		return durable;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public boolean isAutoDelete() {
		return autoDelete;
	}

	public boolean isAutomaticRecovery() {
		return automaticRecovery;
	}

	public long getRecoveryInterval() {
		return recoveryInterval;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getHandshakeTimeout() {
		return handshakeTimeout;
	}

	public int getShutdownTimeout() {
		return shutdownTimeout;
	}

	public int getRequestedHeartbeat() {
		return requestedHeartbeat;
	}

	public ShutdownEvent<Exception> getShutdownEvent() {
		return shutdownEvent;
	}

	public String getExchange() {
		return exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String[] getQueues() {
		return queues;
	}

	public BasicProperties getMsgProperties() {
		return msgProperties;
	}

	public String getReceiveQueue() {
		return receiveQueue;
	}

	public boolean isAutoAck() {
		return autoAck;
	}

	public int getMaxAckTotal() {
		return maxAckTotal;
	}

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host;
		private int port;
		private String username;
		private String password;

		private boolean durable = true;
		private boolean exclusive = false;
		private boolean autoDelete = false;
		private boolean automaticRecovery = false;
		private long recoveryInterval = 10 * 1000;
		private int connectionTimeout = 60 * 1000;
		private int handshakeTimeout = 10 * 1000;
		private int shutdownTimeout = 10 * 1000;
		private int requestedHeartbeat = 20;
		private ShutdownEvent<Exception> shutdownEvent;

		private String exchange = "";
		private String routingKey = "";
		private String[] queues = new String[] { "" };
		private BasicProperties msgProperties = MessageProperties.PERSISTENT_BASIC;

		private String receiveQueue;
		private boolean autoAck = true;
		private int maxAckTotal = 32;

		private String configuratorName;

		protected Builder(String configuratorName) {
			this.configuratorName = configuratorName;
		}

		public Builder setHost(String host) {
			this.host = host;
			return this;
		}

		public Builder setPort(int port) {
			this.port = port;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setDurable(boolean durable) {
			this.durable = durable;
			return this;
		}

		public Builder setExclusive(boolean exclusive) {
			this.exclusive = exclusive;
			return this;
		}

		public Builder setAutoDelete(boolean autoDelete) {
			this.autoDelete = autoDelete;
			return this;
		}

		public Builder setAutomaticRecovery(boolean automaticRecovery) {
			this.automaticRecovery = automaticRecovery;
			return this;
		}

		public Builder setRecoveryInterval(long recoveryInterval) {
			this.recoveryInterval = recoveryInterval;
			return this;
		}

		public Builder setConnectionTimeout(int connectionTimeout) {
			this.connectionTimeout = connectionTimeout;
			return this;
		}

		public Builder setHandshakeTimeout(int handshakeTimeout) {
			this.handshakeTimeout = handshakeTimeout;
			return this;
		}

		public Builder setShutdownTimeout(int shutdownTimeout) {
			this.shutdownTimeout = shutdownTimeout;
			return this;
		}

		public Builder setRequestedHeartbeat(int requestedHeartbeat) {
			this.requestedHeartbeat = requestedHeartbeat;
			return this;
		}

		public Builder setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
			this.shutdownEvent = shutdownEvent;
			return this;
		}

		public Builder setExchange(String exchange) {
			this.exchange = exchange;
			return this;
		}

		public Builder setRoutingKey(String routingKey) {
			this.routingKey = routingKey;
			return this;
		}

		public Builder setQueues(String... queues) {
			this.queues = queues;
			return this;
		}

		public Builder setMsgProperties(BasicProperties msgProperties) {
			this.msgProperties = msgProperties;
			return this;
		}

		public Builder setReceiveQueue(String receiveQueue) {
			this.receiveQueue = receiveQueue;
			return this;
		}

		public Builder setAutoAck(boolean autoAck) {
			this.autoAck = autoAck;
			return this;
		}

		public Builder setMaxAckTotal(int maxAckTotal) {
			this.maxAckTotal = maxAckTotal;
			return this;
		}

		public RabbitMqConfigurator build() {
			return new RabbitMqConfigurator(this);
		}

	}

}
