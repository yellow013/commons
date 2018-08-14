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
	private String directQueue;
	private BasicProperties msgProperties = MessageProperties.PERSISTENT_BASIC;
	private BuiltinExchangeType exchangeType = BuiltinExchangeType.DIRECT;

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

	public BuiltinExchangeType getExchangeType() {
		return exchangeType;
	}

	public RmqPublisherConfigurator setModeDirect(String directQueue) {
		this.exchangeType = BuiltinExchangeType.DIRECT;
		this.directQueue = directQueue;
		return this;
	}

	public RmqPublisherConfigurator setModeFanout(String exchange, String routingKey, String[] bindQueues) {
		if (bindQueues == null) {
			throw new IllegalArgumentException("Bind queues not nullable.");
		}
		this.exchangeType = BuiltinExchangeType.FANOUT;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.bindQueues = bindQueues;
		return this;
	}

	@Deprecated
	public RmqPublisherConfigurator setModeTopic() {
		this.exchangeType = BuiltinExchangeType.TOPIC;
		return this;
	}

	/**
	 * 配置连接信息 START
	 */

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

}
