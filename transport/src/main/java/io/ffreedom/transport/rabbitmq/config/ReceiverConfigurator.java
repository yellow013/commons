package io.ffreedom.transport.rabbitmq.config;

import io.ffreedom.common.functional.ShutdownEvent;

public final class ReceiverConfigurator extends ConnectionConfigurator<ReceiverConfigurator> {

	/**
	 * 接收者参数
	 */
	private String exchange;
	private String receiveQueue;
	// 自动ACK
	private boolean autoAck = true;
	// 最大自动重试次数
	private int maxAckTotal = 32;

	private ReceiverConfigurator() {
		super("RabbitMqReceiverConfigurator");
	}

	public static ReceiverConfigurator configuration() {
		return new ReceiverConfigurator();
	}

	public String getExchange() {
		return exchange;
	}

	public ReceiverConfigurator setExchange(String exchange) {
		this.exchange = exchange;
		return this;
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

	@Override
	public ReceiverConfigurator setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
		return this;
	}

	@Override
	public ReceiverConfigurator setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	@Override
	public ReceiverConfigurator setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	@Override
	public ReceiverConfigurator setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
		return this;
	}

	@Override
	public ReceiverConfigurator setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	@Override
	public ReceiverConfigurator setAutomaticRecovery(boolean automaticRecovery) {
		this.automaticRecovery = automaticRecovery;
		return this;
	}

	@Override
	public ReceiverConfigurator setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
		return this;
	}

	@Override

	public ReceiverConfigurator setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
		return this;
	}

	@Override
	public ReceiverConfigurator setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
		return this;
	}

	@Override
	public ReceiverConfigurator setRequestedHeartbeat(int requestedHeartbeat) {
		this.requestedHeartbeat = requestedHeartbeat;
		return this;
	}

	@Override
	public ReceiverConfigurator setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
		this.shutdownEvent = shutdownEvent;
		return this;
	}

	/**
	 * 配置连接信息 END
	 */

}
