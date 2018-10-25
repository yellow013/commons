package io.ffreedom.transport.rabbitmq.config;

import io.ffreedom.common.functional.ShutdownEvent;

public final class RmqReceiverConfigurator extends ConnectionConfigurator<RmqReceiverConfigurator> {

	/**
	 * 接收者参数
	 */
	private String exchange;
	private String receiveQueue;
	private String errorMsgToExchange;
	// 自动ACK
	private boolean isAutoAck = true;
	// 一次ACK多条
	private boolean isMultipleAck = false;

	// 最大自动重试次数
	private int maxAckTotal = 32;

	private RmqReceiverConfigurator() {
		super("RabbitMqReceiverConfigurator");
	}

	public static RmqReceiverConfigurator configuration() {
		return new RmqReceiverConfigurator();
	}

	public String getExchange() {
		return exchange;
	}

	public RmqReceiverConfigurator setExchange(String exchange) {
		this.exchange = exchange;
		return this;
	}

	public String getErrorMsgToExchange() {
		return errorMsgToExchange;
	}

	public RmqReceiverConfigurator setErrorMsgToExchange(String errorMsgToExchange) {
		this.errorMsgToExchange = errorMsgToExchange;
		return this;
	}

	public String getReceiveQueue() {
		return receiveQueue;
	}

	public RmqReceiverConfigurator setReceiveQueue(String receiveQueue) {
		this.receiveQueue = receiveQueue;
		return this;
	}

	public boolean isAutoAck() {
		return isAutoAck;
	}

	public RmqReceiverConfigurator setAutoAck(boolean isAutoAck) {
		this.isAutoAck = isAutoAck;
		return this;
	}

	public boolean isMultipleAck() {
		return isMultipleAck;
	}

	public RmqReceiverConfigurator setMultipleAck(boolean isMultipleAck) {
		this.isMultipleAck = isMultipleAck;
		return this;
	}

	public int getMaxAckTotal() {
		return maxAckTotal;
	}

	public RmqReceiverConfigurator setMaxAckTotal(int maxAckTotal) {
		this.maxAckTotal = maxAckTotal;
		return this;
	}

	/**
	 * 配置连接信息 START
	 */

	@Override
	public RmqReceiverConfigurator setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setAutomaticRecovery(boolean automaticRecovery) {
		this.automaticRecovery = automaticRecovery;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
		return this;
	}

	@Override

	public RmqReceiverConfigurator setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setRequestedHeartbeat(int requestedHeartbeat) {
		this.requestedHeartbeat = requestedHeartbeat;
		return this;
	}

	@Override
	public RmqReceiverConfigurator setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
		this.shutdownEvent = shutdownEvent;
		return this;
	}

	/**
	 * 配置连接信息 END
	 */

}
