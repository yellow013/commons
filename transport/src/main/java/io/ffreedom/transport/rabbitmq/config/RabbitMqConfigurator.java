package io.ffreedom.transport.rabbitmq.config;

import io.ffreedom.common.functional.ShutdownEvent;
import io.ffreedom.transport.base.config.TransportConfigurator;

public class RabbitMqConfigurator implements TransportConfigurator {

	private String host;
	private int port;

	private String exchange;
	private String routingKey;
	private String queue;
	private String username;
	private String password;

	private boolean autoAck;
	private boolean durable;
	private boolean exclusive;
	private boolean autoDelete;
	private boolean automaticRecovery;
	private long recoveryInterval;

	private int connectionTimeout;
	private int handshakeTimeout;
	private int shutdownTimeout;
	private int requestedHeartbeat;

	private ShutdownEvent<Exception> shutdownEvent;

	private String configuratorName = "RabbitMqConfigurator";

	private RabbitMqConfigurator(ConfiguratorBuilder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.exchange = builder.exchange;
		this.routingKey = builder.routingKey;
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

	public static ConfiguratorBuilder builder() {
		return new ConfiguratorBuilder();
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getExchange() {
		return exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String getQueue() {
		return queue;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAutoAck() {
		return autoAck;
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

	public static class ConfiguratorBuilder {

		private String host;
		private int port;
		private String exchange = "";
		private String routingKey;
		private String queue;
		private String username;
		private String password;
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

		private ConfiguratorBuilder() {
		}

		public ConfiguratorBuilder setHost(String host) {
			this.host = host;
			return this;
		}

		public ConfiguratorBuilder setPort(int port) {
			this.port = port;
			return this;
		}

		public ConfiguratorBuilder setExchange(String exchange) {
			this.exchange = exchange;
			return this;
		}

		public ConfiguratorBuilder setRoutingKey(String routingKey) {
			this.routingKey = routingKey;
			return this;
		}

		public ConfiguratorBuilder setQueue(String queue) {
			this.queue = queue;
			return this;
		}

		public ConfiguratorBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		public ConfiguratorBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public ConfiguratorBuilder setAutoAck(boolean autoAck) {
			this.autoAck = autoAck;
			return this;
		}

		public ConfiguratorBuilder setDurable(boolean durable) {
			this.durable = durable;
			return this;
		}

		public ConfiguratorBuilder setExclusive(boolean exclusive) {
			this.exclusive = exclusive;
			return this;
		}

		public ConfiguratorBuilder setAutoDelete(boolean autoDelete) {
			this.autoDelete = autoDelete;
			return this;
		}

		public ConfiguratorBuilder setAutomaticRecovery(boolean automaticRecovery) {
			this.automaticRecovery = automaticRecovery;
			return this;
		}

		public ConfiguratorBuilder setRecoveryInterval(long recoveryInterval) {
			this.recoveryInterval = recoveryInterval;
			return this;
		}

		public ConfiguratorBuilder setConnectionTimeout(int connectionTimeout) {
			this.connectionTimeout = connectionTimeout;
			return this;
		}

		public ConfiguratorBuilder setHandshakeTimeout(int handshakeTimeout) {
			this.handshakeTimeout = handshakeTimeout;
			return this;
		}

		public ConfiguratorBuilder setShutdownTimeout(int shutdownTimeout) {
			this.shutdownTimeout = shutdownTimeout;
			return this;
		}

		public ConfiguratorBuilder setRequestedHeartbeat(int requestedHeartbeat) {
			this.requestedHeartbeat = requestedHeartbeat;
			return this;
		}

		public ConfiguratorBuilder setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
			this.shutdownEvent = shutdownEvent;
			return this;
		}

		public RabbitMqConfigurator build() {
			return new RabbitMqConfigurator(this);
		}

	}

}
