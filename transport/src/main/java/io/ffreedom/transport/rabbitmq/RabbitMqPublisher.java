package io.ffreedom.transport.rabbitmq;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbitmq.config.RabbitMqConfigurator;

public class RabbitMqPublisher extends BaseRabbitMqTransport implements Publisher<byte[]> {

	// 发布消息使用的routingKey
	private String routingKey;

	// 发布消息使用的exchange
	private String exchange;

	// 发布消息使用的参数
	private BasicProperties msgProperties;

	private String publisherName;

	/**
	 * 
	 * @param configurator
	 */
	public RabbitMqPublisher(String tag, RabbitMqConfigurator configurator) {
		super(tag, configurator);
		this.exchange = configurator.getExchange();
		this.routingKey = StringUtil.isNullOrEmpty(configurator.getRoutingKey()) ? configurator.getReceiveQueue()
				: configurator.getRoutingKey();
		this.msgProperties = configurator.getMsgProperties();
		this.publisherName = "Pub->" + configurator.getHost() + ":" + configurator.getPort() + "$" + routingKey;
		createConnection();
		init();
	}

	private void init() {
		try {
			List<String> queues = Arrays.asList(configurator.getQueues());
			for (String queue : queues) {
				channel.queueDeclare(queue, configurator.isDurable(), configurator.isExclusive(),
						configurator.isAutoDelete(), null);
			}
			if (StringUtil.isNullOrEmpty(exchange)) {
				channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
				for (String queue : queues) {
					channel.queueBind(queue, exchange, routingKey);
				}
			}
		} catch (IOException e) {
			logger.error("IOException ->" + e.getMessage());
			logger.error(e.getStackTrace());
			destroy();
			logger.error("call destroy() method.");
		}
	}

	@Override
	public void publish(byte[] msg) {
		publish(routingKey, msg);
	}

	@Override
	public void publish(String target, byte[] msg) {
		try {
			// 记录重试次数
			int retry = 0;
			// 调用isConnected()检查channel和connection是否打开, 如果没有打开, 先销毁连接, 再重新创建连接.
			while (!isConnected()) {
				logger.error("isConnected() == false, retry " + (++retry));
				destroy();
				createConnection();
				ThreadUtil.sleep(configurator.getRecoveryInterval());
			}
			// param1: exchange
			// param2: routingKey
			// param3: properties
			// param4: msgBody

			channel.basicPublish(exchange, target, msgProperties, msg);
		} catch (IOException e) {
			logger.error("Channel#basicPublish -> " + e.getMessage());
			logger.error(e.getStackTrace());
			destroy();
		}
	}

	class A extends BasicProperties {

	}

	@Override
	public boolean destroy() {
		logger.info("call method -> RabbitPublisher.destroy()");
		closeConnection();
		return true;
	}

	@Override
	public String getName() {
		return publisherName;
	}

	public static void main(String[] args) {

		RabbitMqConfigurator configurator = RabbitMqConfigurator.publisherBuilder().setHost("192.168.1.152")
				.setPort(5672).setUsername("thadmq").setPassword("root").setQueues("hello").setAutomaticRecovery(true)
				.build();

		RabbitMqPublisher publisher = new RabbitMqPublisher("TEST_PUB", configurator);

		ThreadUtil.startNewThread(() -> {
			int count = 0;
			while (true) {
				ThreadUtil.sleep(5000);
				publisher.publish(String.valueOf(++count).getBytes(Charsets.UTF8));
				System.out.println(count);
			}
		});

	}

}
