package org.beam.transport.rabbit.config;

import org.beam.transport.base.config.TransportConfigurator;
import org.beam.transport.rabbit.ShutdownEvent;

public class RabbitConfigurator implements TransportConfigurator {

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

	private ShutdownEvent shutdownEvent;

	private String configuratorName = "RabbitMqConfigurator";

	private RabbitConfigurator(ConfiguratorBuilder builder) {
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
		this.shutdownEvent = builder.shutdownEvent;
	}

	public static ConfiguratorBuilder builder() {
		return new ConfiguratorBuilder();
	}

	public String host() {
		return host;
	}

	public int port() {
		return port;
	}

	public String exchange() {
		return exchange;
	}

	public String routingKey() {
		return routingKey;
	}

	public String queue() {
		return queue;
	}

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}

	public boolean autoAck() {
		return autoAck;
	}

	public boolean durable() {
		return durable;
	}

	public boolean exclusive() {
		return exclusive;
	}

	public boolean autoDelete() {
		return autoDelete;
	}

	public boolean automaticRecovery() {
		return automaticRecovery;
	}

	public long recoveryInterval() {
		return recoveryInterval;
	}

	public ShutdownEvent shutdownEvent() {
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
		private ShutdownEvent shutdownEvent;

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

		public ConfiguratorBuilder setShutdownEvent(ShutdownEvent shutdownEvent) {
			this.shutdownEvent = shutdownEvent;
			return this;
		}

		public RabbitConfigurator build() {
			return new RabbitConfigurator(this);
		}

	}

}
