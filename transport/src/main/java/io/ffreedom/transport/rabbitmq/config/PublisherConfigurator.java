package io.ffreedom.transport.rabbitmq.config;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.MessageProperties;

import io.ffreedom.common.functional.ShutdownEvent;

public class PublisherConfigurator extends ConnectionConfigurator<PublisherConfigurator> {

	/**
	 * 发布者参数
	 */
	private String exchange = "";
	private String routingKey = "";
	private String[] bindQueues = null;
	private String directQueue;
	private BasicProperties msgProperties = MessageProperties.PERSISTENT_BASIC;
	private BuiltinExchangeType exchangeType = BuiltinExchangeType.DIRECT;

	private PublisherConfigurator() {
		super("RabbitMqPublisherConfigurator");
	}

	public static PublisherConfigurator configuration() {
		return new PublisherConfigurator();
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

	public PublisherConfigurator setMsgProperties(BasicProperties msgProperties) {
		this.msgProperties = msgProperties;
		return this;
	}

	public BuiltinExchangeType getExchangeType() {
		return exchangeType;
	}

	public PublisherConfigurator setDirectMode(String directQueue) {
		this.exchangeType = BuiltinExchangeType.DIRECT;
		this.directQueue = directQueue;
		return this;
	}

	public PublisherConfigurator setFanoutMode(String exchange, String routingKey, String... bindQueues) {
		this.exchangeType = BuiltinExchangeType.FANOUT;
		this.exchange = exchange;
		this.routingKey = routingKey;
		this.bindQueues = bindQueues;
		return this;
	}

	public PublisherConfigurator setTopicMode() {
		this.exchangeType = BuiltinExchangeType.TOPIC;
		return this;
	}

	/**
	 * 配置连接信息 START
	 */

	public PublisherConfigurator setHost(String host) {
		this.host = host;
		return this;
	}

	public PublisherConfigurator setPort(int port) {
		this.port = port;
		return this;
	}

	public PublisherConfigurator setUsername(String username) {
		this.username = username;
		return this;
	}

	public PublisherConfigurator setPassword(String password) {
		this.password = password;
		return this;
	}

	public PublisherConfigurator setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	public PublisherConfigurator setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	public PublisherConfigurator setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
		return this;
	}

	public PublisherConfigurator setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	public PublisherConfigurator setAutomaticRecovery(boolean automaticRecovery) {
		this.automaticRecovery = automaticRecovery;
		return this;
	}

	public PublisherConfigurator setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
		return this;
	}

	public PublisherConfigurator setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
		return this;
	}

	public PublisherConfigurator setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
		return this;
	}

	public PublisherConfigurator setRequestedHeartbeat(int requestedHeartbeat) {
		this.requestedHeartbeat = requestedHeartbeat;
		return this;
	}

	public PublisherConfigurator setShutdownEvent(ShutdownEvent<Exception> shutdownEvent) {
		this.shutdownEvent = shutdownEvent;
		return this;
	}

	public PublisherConfigurator setConfiguratorName(String configuratorName) {
		this.configuratorName = configuratorName;
		return this;
	}
	/**
	 * 配置连接信息 END
	 */

}
