package io.ffreedom.transport.rabbitmq.config;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.MessageProperties;

import io.ffreedom.common.functional.ShutdownEvent;

public class RmqPublisherConfigurator extends ConnectionConfigurator<RmqPublisherConfigurator> {

	/**
	 * 发布者参数
	 */
	private String exchange = "";
	private String routingKey = "";
	private String[] bindQueues = null;
	private String directQueue = null;
	private BasicProperties msgProperties = MessageProperties.PERSISTENT_BASIC;
	private BuiltinExchangeType builtinExchangeType = BuiltinExchangeType.DIRECT;
	private boolean isConfirm = false;
	private long confirmTimeout = 5000;
	private int confirmRetry = 3;

	private RmqPublisherConfigurator() {
		super("RabbitMqPublisherConfigurator");
	}

	public static RmqPublisherConfigurator configuration() {
		return new RmqPublisherConfigurator();
	}

	public String getExchange() {
		return exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String[] getBindQueues() {
		return bindQueues;
	}

	public String getDirectQueue() {
		return directQueue;
	}

	public BasicProperties getMsgProperties() {
		return msgProperties;
	}

	public RmqPublisherConfigurator setMsgProperties(BasicProperties msgProperties) {
		this.msgProperties = msgProperties;
		return this;
	}

	public boolean isConfirm() {
		return isConfirm;
	}

	public RmqPublisherConfigurator openConfirm() {
		this.isConfirm = true;
		return this;
	}

	public RmqPublisherConfigurator closeConfirm() {
		this.isConfirm = false;
		return this;
	}

	public long getConfirmTimeout() {
		return confirmTimeout;
	}

	public RmqPublisherConfigurator setConfirmTimeout(long confirmTimeout) {
		this.confirmTimeout = confirmTimeout;
		return this;
	}

	public int getConfirmRetry() {
		return confirmRetry;
	}

	public RmqPublisherConfigurator setConfirmRetry(int confirmRetry) {
		this.confirmRetry = confirmRetry;
		return this;
	}

	public BuiltinExchangeType getBuiltinExchangeType() {
		return builtinExchangeType;
	}

	public RmqPublisherConfigurator setMode(ExchangeType exchangeType) {
		if (exchangeType == null)
			throw new IllegalArgumentException("Param exchangeType not allowed null");
		switch (exchangeType) {
		case DIRECT:
			this.builtinExchangeType = BuiltinExchangeType.DIRECT;
			return this;
		case FANOUT:
			this.builtinExchangeType = BuiltinExchangeType.FANOUT;
			return this;
		case TOPIC:
			this.builtinExchangeType = BuiltinExchangeType.TOPIC;
			return this;
		default:
			return this;
		}
	}

	public RmqPublisherConfigurator setModeDirect(String directQueue) {
		this.builtinExchangeType = BuiltinExchangeType.DIRECT;
		this.directQueue = directQueue;
		return this;
	}

	public RmqPublisherConfigurator setModeFanout(String exchange) {
		return setModeFanoutAndBindQueues(exchange, null);
	}

	public RmqPublisherConfigurator setModeFanoutAndBindQueues(String exchange, String[] bindQueues) {
		this.builtinExchangeType = BuiltinExchangeType.FANOUT;
		this.exchange = exchange;
		this.bindQueues = bindQueues;
		return this;
	}

	public RmqPublisherConfigurator setModeTopic(String exchange, String routingKey, String[] bindQueues) {
		this.builtinExchangeType = BuiltinExchangeType.TOPIC;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.bindQueues = bindQueues;
		return this;
	}

	/**
	 * 配置连接信息 START
	 */

	@Override
	public RmqPublisherConfigurator setConnectionParam(String host, int port) {
		this.host = host;
		this.port = port;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setUserParam(String username, String password) {
		this.username = username;
		this.password = password;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setAutomaticRecovery(boolean automaticRecovery) {
		this.automaticRecovery = automaticRecovery;
		return this;
	}

	@Override

	public RmqPublisherConfigurator setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setRequestedHeartbeat(int requestedHeartbeat) {
		this.requestedHeartbeat = requestedHeartbeat;
		return this;
	}

	@Override
	public RmqPublisherConfigurator setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
		this.shutdownEvent = shutdownEvent;
		return this;
	}

	/**
	 * 配置连接信息 END
	 */

	public static enum ExchangeType {
		DIRECT, FANOUT, TOPIC
	}

}
