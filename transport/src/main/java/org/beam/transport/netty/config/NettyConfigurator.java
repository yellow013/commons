package org.beam.transport.netty.config;

import org.beam.transport.base.config.TransportConfigurator;
import org.beam.transport.rabbit.ShutdownEvent;

public class NettyConfigurator implements TransportConfigurator {

	private String host;
	private int port;

	private ShutdownEvent shutdownEvent;

	private String configuratorName = "NettyConfigurator";

	private NettyConfigurator(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.shutdownEvent = builder.shutdownEvent;
	}

	public static Builder builder() {
		return new Builder();
	}

	public String host() {
		return host;
	}

	public int port() {
		return port;
	}

	public ShutdownEvent getShutdownEvent() {
		return shutdownEvent;
	}

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host;
		private int port;
		private ShutdownEvent shutdownEvent;

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

		public Builder shutdownEvent(ShutdownEvent shutdownEvent) {
			this.shutdownEvent = shutdownEvent;
			return this;
		}

		public NettyConfigurator build() {
			return new NettyConfigurator(this);
		}

	}

}
