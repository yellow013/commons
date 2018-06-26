package io.ffreedom.transport.rabbitmq.config;

import io.ffreedom.common.functional.ShutdownEvent;

public final class ReceiverConfigurator extends ConnectionConfigurator<ReceiverConfigurator> {

	/**
	 * 接收者参数
	 */
	private String receiveQueue;
	// 自动ACK
	private boolean autoAck = true;
	// 最大自动重试次数
	private int maxAckTotal = 32;

	private ReceiverConfigurator() {
		super("RabbitMqReceiverConfigurator");
		// TODO Auto-generated constructor stub
	}

	public static ReceiverConfigurator configuration() {
		return new ReceiverConfigurator();
	}
	
	public String getReceiveQueue() {
		return receiveQueue;
	}

	public ReceiverConfigurator setReceiveQueue(String receiveQueue) {
		this.receiveQueue = receiveQueue;
		return this;
	}

	public boolean isAutoAck() {
		return autoAck;
	}

	public ReceiverConfigurator setAutoAck(boolean autoAck) {
		this.autoAck = autoAck;
		return this;
	}

	public int getMaxAckTotal() {
		return maxAckTotal;
	}

	public ReceiverConfigurator setMaxAckTotal(int maxAckTotal) {
		this.maxAckTotal = maxAckTotal;
		return this;
	}

	/**
	 * 配置连接信息 START
	 */

	public ReceiverConfigurator setHost(String host) {
		this.host = host;
		return this;
	}

	public ReceiverConfigurator setPort(int port) {
		this.port = port;
		return this;
	}

	public ReceiverConfigurator setUsername(String username) {
		this.username = username;
		return this;
	}

	public ReceiverConfigurator setPassword(String password) {
		this.password = password;
		return this;
	}

	public ReceiverConfigurator setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	public ReceiverConfigurator setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	public ReceiverConfigurator setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
		return this;
	}

	public ReceiverConfigurator setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	public ReceiverConfigurator setAutomaticRecovery(boolean automaticRecovery) {
		this.automaticRecovery = automaticRecovery;
		return this;
	}

	public ReceiverConfigurator setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
		return this;
	}

	public ReceiverConfigurator setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
		return this;
	}

	public ReceiverConfigurator setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
		return this;
	}

	public ReceiverConfigurator setRequestedHeartbeat(int requestedHeartbeat) {
		this.requestedHeartbeat = requestedHeartbeat;
		return this;
	}

	public ReceiverConfigurator setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
		this.shutdownEvent = shutdownEvent;
		return this;
	}

	public ReceiverConfigurator setConfiguratorName(String configuratorName) {
		this.configuratorName = configuratorName;
		return this;
	}
	/**
	 * 配置连接信息 END
	 */

}
