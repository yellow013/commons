package io.ffreedom.transport.netty.config;

import java.util.concurrent.TimeUnit;

import io.ffreedom.common.functional.ShutdownEvent;
import io.ffreedom.transport.base.config.TransportConfigurator;

public class NettyConfigurator implements TransportConfigurator {

	private String host;
	private int port;
	private int backlog;
	private boolean keepAlive;
	private boolean tcpNoDelay;
	private long sendInterval;
	private TimeUnit sendIntervalTimeUnit;
	private int writeByteBufSize;
	private char separator;

	private ShutdownEvent<Exception> shutdownEvent;

	private String configuratorName = "NettyConfigurator";

	private NettyConfigurator(Builder builder) {
		this.host = builder.host;
		this.port = builder.port;
		this.backlog = builder.backlog;
		this.keepAlive = builder.keepAlive;
		this.tcpNoDelay = builder.tcpNoDelay;
		this.sendInterval = builder.sendInterval;
		this.sendIntervalTimeUnit = builder.sendIntervalTimeUnit;
		this.writeByteBufSize = builder.writeByteBufSize;
		this.separator = builder.separator;
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

	public int getBacklog() {
		return backlog;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public boolean isTcpNoDelay() {
		return tcpNoDelay;
	}

	public long getSendInterval() {
		return sendInterval;
	}

	public TimeUnit getSendIntervalTimeUnit() {
		return sendIntervalTimeUnit;
	}

	public int getWriteByteBufSize() {
		return writeByteBufSize;
	}

	public char getSeparator() {
		return separator;
	}

	public ShutdownEvent<Exception> getShutdownEvent() {
		return shutdownEvent;
	}

	@Override
	public String getConfiguratorName() {
		return configuratorName;
	}

	public static class Builder {

		private String host = "127.0.0.1";
		private int port = 9500;
		private int backlog = 128;
		private boolean keepAlive = true;
		private boolean tcpNoDelay = true;
		private long sendInterval;
		private TimeUnit sendIntervalTimeUnit;
		private int writeByteBufSize = 1024 * 8;
		private char separator = ';';
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

		public Builder setBacklog(int backlog) {
			this.backlog = backlog;
			return this;
		}

		public Builder setKeepAlive(boolean keepAlive) {
			this.keepAlive = keepAlive;
			return this;
		}

		public Builder setTcpNoDelay(boolean tcpNoDelay) {
			this.tcpNoDelay = tcpNoDelay;
			return this;
		}

		public Builder setSendInterval(long sendInterval, TimeUnit sendIntervalTimeUnit) {
			this.sendInterval = sendInterval;
			this.sendIntervalTimeUnit = sendIntervalTimeUnit;
			return this;
		}

		public Builder setWriteByteBufSize(int writeByteBufSize) {
			this.writeByteBufSize = writeByteBufSize;
			return this;
		}

		public Builder setSeparator(char separator) {
			this.separator = separator;
			return this;
		}

		public Builder setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
			this.shutdownEvent = shutdownEvent;
			return this;
		}

		public NettyConfigurator build() {
			return new NettyConfigurator(this);
		}

	}

}
