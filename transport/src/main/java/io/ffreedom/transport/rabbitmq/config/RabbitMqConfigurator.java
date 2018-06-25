package io.ffreedom.transport.rabbitmq.config;

import io.ffreedom.common.functional.ShutdownEvent;
import io.ffreedom.transport.base.config.TransportConfigurator;

public class RabbitMqConfigurator implements TransportConfigurator {

	// 连接参数
	private String host;
	private int port;
	private String username;
	private String password;

	// 发布订阅参数
	private String pubExchange;
	private String pubRoutingKey;
	private String queue;

	// 自动ACK
	private boolean autoAck;
	// 最大自动重试次数
	private int ackMaxTotal;
	// 队列持久化
	private boolean durable;
	// 连接独占此队列
	private boolean exclusive;
	// channel关闭后自动删除队列
	private boolean autoDelete;
	// 自动恢复连接
	private boolean automaticRecovery;
	// 重试连接间隔
	private long recoveryInterval;

	// 连接超时时间
	private int connectionTimeout;
	// 握手通信超时时间
	private int handshakeTimeout;
	// 关闭超时时间
	private int shutdownTimeout;
	// 请求心跳超时时间
	private int requestedHeartbeat;

	// 停机处理回调函数
	private ShutdownEvent<Exception> shutdownEvent;

	private String configuratorName = "RabbitMqConfigurator";

	private RabbitMqConfigurator(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.pubExchange = builder.pubExchange;
		this.pubRoutingKey = builder.pubRoutingKey;
		this.queue = builder.queue;
		this.username = builder.username;
		this.password = builder.password;
		this.autoAck = builder.autoAck;
		this.durable = builder.durable;
		this.exclusive = builder.exclusive;
		this.autoDelete = builder.autoDelete;
		this.automaticRecovery = builder.automaticRecovery;
		this.recoveryInterval = builder.recoveryInterval;
		this.connectionTimeout = builder.connectionTimeout;
		this.handshakeTimeout = builder.handshakeTimeout;
		this.shutdownTimeout = builder.shutdownTimeout;
		this.requestedHeartbeat = builder.requestedHeartbeat;
		this.shutdownEvent = builder.shutdownEvent;
	}

	public static Builder builder() {
		return new Builder();
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

	public String getPubExchange() {
		return pubExchange;
	}

	public String getPubRoutingKey() {
		return pubRoutingKey;
	}

	public String getQueue() {
		return queue;
	}

	public boolean isAutoAck() {
		return autoAck;
	}

	public int getAckMaxTotal() {
		return ackMaxTotal;
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

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host;
		private int port;
		private String username;
		private String password;
		private String pubExchange = "";
		private String pubRoutingKey = "";
		private String queue;
		private boolean autoAck = false;
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

		private Builder() {
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

		public Builder setPubExchange(String pubExchange) {
			this.pubExchange = pubExchange;
			return this;
		}

		public Builder setPubRoutingKey(String pubRoutingKey) {
			this.pubRoutingKey = pubRoutingKey;
			return this;
		}

		public Builder setQueue(String queue) {
			this.queue = queue;
			return this;
		}

		public Builder setAutoAck(boolean autoAck) {
			this.autoAck = autoAck;
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

		public RabbitMqConfigurator build() {
			return new RabbitMqConfigurator(this);
		}

	}

}
