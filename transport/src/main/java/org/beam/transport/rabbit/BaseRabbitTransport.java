package org.beam.transport.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.beam.common.log.CommonLoggerFactory;
import org.beam.transport.rabbit.config.RabbitConfigurator;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class BaseRabbitTransport {

	// 连接RabbitMQ Server使用的组件
	protected ConnectionFactory connectionFactory;
	protected Connection connection;
	protected Channel channel;

	// 存储配置信息对象
	protected RabbitConfigurator configurator;

	// 停机事件, 在监听到ShutdownSignalException时调用
	protected ShutdownEvent shutdownEvent;

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	private String tag;

	/**
	 * @param tag
	 * @param configurator
	 */
	public BaseRabbitTransport(String tag, RabbitConfigurator configurator) {
		if (configurator == null) {
			throw new NullPointerException(tag + ":configurator is null.");
		}
		this.tag = tag;
		this.configurator = configurator;
		this.shutdownEvent = configurator.shutdownEvent();
	}

	protected void createConnection() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
			connectionFactory.setHost(configurator.host());
			connectionFactory.setPort(configurator.port());
			connectionFactory.setUsername(configurator.username());
			connectionFactory.setPassword(configurator.password());
			connectionFactory.setAutomaticRecoveryEnabled(configurator.automaticRecovery());
			connectionFactory.setNetworkRecoveryInterval(configurator.recoveryInterval());
		}
		try {
			connection = connectionFactory.newConnection();
			logger.info(
					"newConnection() successful, tag : " + tag + ", connection hash code : " + connection.hashCode());
			connection.addShutdownListener((shutdownSignalException) -> {
				// 输出错误信息到控制台
				logger.info("ShutdownListener -> " + shutdownSignalException.getMessage());
				// 如果回调函数不为null, 则执行此函数
				if (shutdownEvent != null) {
					shutdownEvent.shutdownHandle(shutdownSignalException);
				}
			});
			channel = connection.createChannel();
			logger.info("createChannel() successful, channel number is : " + channel.getChannelNumber());
			channel.queueDeclare(configurator.queue(), configurator.durable(), configurator.exclusive(),
					configurator.autoDelete(), null);
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		}
	}

	public boolean isConnected() {
		return (connection != null && connection.isOpen()) && (channel != null && channel.isOpen());
	}

	protected void closeConnection() {
		try {
			if (channel != null && channel.isOpen()) {
				channel.close();
			}
			if (connection != null && connection.isOpen()) {
				connection.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		}
	}

}
