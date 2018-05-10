package io.ffreedom.transport.socket.config;

import io.ffreedom.transport.base.config.TransportConfigurator;

public class SocketConfigurator implements TransportConfigurator {

	private String host;
	private int port;
	private long receiveInterval;
	private int sendQueueSize;

	private final String configuratorName = "SocketConfigurator";

	private SocketConfigurator(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.receiveInterval = builder.receiveInterval;
		this.sendQueueSize = builder.sendQueueSize;
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

	public long getReceiveInterval() {
		return receiveInterval;
	}

	public int getSendQueueSize() {
		return sendQueueSize;
	}

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host = "127.0.0.1";
		private int port;
		private long receiveInterval = 100;
		private int sendQueueSize = 256;

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

		public Builder setReceiveInterval(long receiveInterval) {
			this.receiveInterval = receiveInterval;
			return this;
		}

		public Builder setSendQueueSize(int sendQueueSize) {
			this.sendQueueSize = sendQueueSize;
			return this;
		}

		public SocketConfigurator build() {
			return new SocketConfigurator(this);
		}

	}
}