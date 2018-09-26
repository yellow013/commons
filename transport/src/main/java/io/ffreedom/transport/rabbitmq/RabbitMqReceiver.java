package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Receiver;
import io.ffreedom.transport.rabbitmq.config.RmqReceiverConfigurator;

public class RabbitMqReceiver extends BaseRabbitMqTransport implements Receiver {

	// 接收消息时使用的回调函数
	private Callback<byte[]> callback;

	// 绑定的Exchange
	// 暂时没有使用
	@SuppressWarnings("unused")
	private String exchange;
	// 连接的Queue
	private String receiveQueue;

	// 消息无法处理是发送到的错误队列
	private String errorMsgToExchange;

	// 自动ACK
	private boolean isAutoAck;
	// 最大自动重试次数
	private int maxAckTotal;
	private String receiverName;

	/**
	 * 
	 * @param configurator
	 * @param callback
	 */
	public RabbitMqReceiver(String tag, RmqReceiverConfigurator configurator, Callback<byte[]> callback) {
		super(tag, configurator);
		this.callback = callback;
		this.exchange = configurator.getExchange();
		this.receiveQueue = configurator.getReceiveQueue();
		this.isAutoAck = configurator.isAutoAck();
		this.maxAckTotal = configurator.getMaxAckTotal();
		this.errorMsgToExchange = configurator.getErrorMsgToExchange();
		createConnection();
		init();
	}

	private void init() {
		this.receiverName = "Receiver->" + configurator.getHost() + ":" + configurator.getPort() + "$" + receiveQueue;
		try {
			channel.queueDeclare(receiveQueue, configurator.isDurable(), configurator.isExclusive(),
					configurator.isAutoDelete(), null);
		} catch (IOException e) {
			logger.error("IOException ->" + e.getMessage());
			logger.error("throws IOException -> ", e);
			logger.error("call destroy() method.");
			destroy();
		}
	}

	@Override
	public void receive() {
		try {
			// param1: queue
			// param2: autoAck
			// param3: consumeCallback
			channel.basicConsume(receiveQueue, isAutoAck, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					try {
						callback.accept(body);
					} catch (Exception e) {
						logger.error("callback#accept -> " + e.getMessage());
						logger.error("throws Exception is -> " + e.getClass().getSimpleName(), e);
						if (errorMsgToExchange != null) {
							// message to errorMsgExchange
							logger.info("Msg sent to errorMsgExchange : " + new String(body, Charsets.UTF8));
							channel.basicPublish(errorMsgToExchange, "", null, body);
						} else {
							// 退回消息->关闭连接
							logger.info("Reject Msg : " + new String(body, Charsets.UTF8));
							channel.basicReject(envelope.getDeliveryTag(), true);
							destroy();
						}
					}
					if (!isAutoAck) {
						try {
							int ack = 0;
							while (!isConnected()) {
								logger.error("isConnected() == false, ack " + (++ack));
								destroy();
								createConnection();
								ThreadUtil.sleep(configurator.getRecoveryInterval());
								if (ack == maxAckTotal) {
									logger.error("channel#basicAck() -> failure...");
									break;
								}
							}
							channel.basicAck(envelope.getDeliveryTag(), false);
						} catch (IOException e) {
							logger.error("channel#basicAck() -> " + e.getMessage());
							logger.error("throws IOException -> ", e);
						}
					}
				}
			});
		} catch (IOException e) {
			logger.error("channel#basicConsume() -> " + e.getMessage());
			logger.error("throws IOException -> ", e);
		}
	}

	@Override
	public boolean destroy() {
		logger.info("call method -> RabbitMqReceiver.destroy()");
		closeConnection();
		return true;
	}

	@Override
	public String getName() {
		return receiverName;
	}

	public static void main(String[] args) {
		RabbitMqReceiver receiver = new RabbitMqReceiver("", RmqReceiverConfigurator.configuration()
				.setConnectionParam("", 0).setUserParam("", "").setReceiveQueue("").setAutomaticRecovery(true),
				(byte[] msg) -> {
					System.out.println(new String(msg, Charsets.UTF8));
				});

		receiver.receive();

	}

}
