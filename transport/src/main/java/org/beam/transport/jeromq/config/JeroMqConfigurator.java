package org.beam.transport.jeromq.config;

import org.beam.transport.base.config.TransportConfigurator;

public class JeroMqConfigurator implements TransportConfigurator {

	private String host;
	private int port;
	private String topic;
	private int ioThreads;

	private final String configuratorName = "JeroMqConfigurator";

	private JeroMqConfigurator(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.topic = builder.topic;
		this.ioThreads = builder.ioThreads;
	}

	public static Builder builder() {
		return new Builder();
	}

	public int ioThreads() {
		return ioThreads;
	}

	public String host() {
		return host;
	}

	public int port() {
		return port;
	}

	public String topic() {
		return topic;
	}

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host;
		private int port;
		private String topic;
		private int ioThreads;

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

		public Builder setTopic(String topic) {
			this.topic = topic;
			return this;
		}
		
		public Builder setIoThreads(int ioThreads) {
			this.ioThreads = ioThreads;
			return this;
		}

		public JeroMqConfigurator build() {
			return new JeroMqConfigurator(this);
		}

	}
}