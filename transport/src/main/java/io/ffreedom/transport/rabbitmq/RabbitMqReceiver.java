package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.log.ErrorLogger;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.transport.base.role.Receiver;
import io.ffreedom.transport.rabbitmq.config.RmqReceiverConfigurator;

public class RabbitMqReceiver extends BaseRabbitMqTransport implements Receiver {

	// 接收消息时使用的回调函数
	private volatile Callback<byte[]> callback;

	// 绑定的Exchange
	// 暂时没有使用
	@SuppressWarnings("unused")
	private String exchange;
	// 连接的Queue
	private String receiveQueue;

	// 消息无法处理时发送到的错误队列
	private String errorMsgToExchange;

	// 自动ACK
	private boolean isAutoAck;
	// 一次ACK多条
	private boolean isMultipleAck;
	// 最大自动重试次数
	private int maxAckTotal;
	private int maxAckReconnection;
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
		this.isMultipleAck = configurator.isMultipleAck();
		this.maxAckTotal = configurator.getMaxAckTotal();
		this.maxAckReconnection = configurator.getMaxAckReconnection();
		this.errorMsgToExchange = configurator.getErrorMsgToExchange();
		createConnection();
		init();
	}

	/**
	 * 
	 * @param configurator
	 * @param callback
	 */
	@Deprecated
	public RabbitMqReceiver(String tag, RmqReceiverConfigurator configurator) {
		this(tag, configurator, null);
	}

	private void init() {
		this.receiverName = "Receiver->" + configurator.getHost() + ":" + configurator.getPort() + "$" + receiveQueue;
		try {
			channel.queueDeclare(receiveQueue, configurator.isDurable(), configurator.isExclusive(),
					configurator.isAutoDelete(), null);
		} catch (IOException e) {
			ErrorLogger.error(logger, e,
					"Method channel.queueDeclare(queue==[{}], durable==[{]}, exclusive==[{}], autoDelete==[{}], arguments==null) IOException message -> {}",
					receiveQueue, configurator.isDurable(), configurator.isExclusive(), configurator.isAutoDelete(),
					e.getMessage());
			destroy();
		}
	}

	@Override
	public void receive() {
		try {
//			channel.basicConsume(receiveQueue, isAutoAck, tag, (consumerTag, msg) -> {
//				Envelope envelope = msg.getEnvelope();
//			}, (consumerTag) -> {
//			}, (consumerTag, shutdownException) -> {
//			});

			// param1: queue
			// param2: autoAck
			// param3: consumeCallback
			channel.basicConsume(receiveQueue, isAutoAck, tag, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					try {
						logger.debug("Message handle start.");
						logger.debug(
								"Callback handleDelivery(consumerTag==[{}], envelope.getDeliveryTag==[{}] body.length==[{}])",
								consumerTag, envelope.getDeliveryTag(), body.length);
						callback.accept(body);
						logger.debug("Callback handleDelivery() end.");
					} catch (Exception e) {
						ErrorLogger.error(logger, e, "Call method callback.accept(body) throw Exception -> {}",
								e.getMessage());
						if (StringUtil.notNullAndEmpty(errorMsgToExchange)) {
							// Sent message to error dump queue.
							logger.info("Exception handling -> Msg [{}] sent to ErrorMsgExchange.",
									new String(body, Charsets.UTF8));
							channel.basicPublish(errorMsgToExchange, "", null, body);
							logger.info("Exception handling -> Sent to ErrorMsgExchange finished.");
						} else {
							// Reject message and close connection.
							logger.info("Exception handling -> Reject Msg [{}]", new String(body, Charsets.UTF8));
							channel.basicReject(envelope.getDeliveryTag(), true);
							logger.info("Exception handling -> Reject Msg finished.");
							destroy();
						}
					}
					if (!isAutoAck) {
						if (ack(envelope.getDeliveryTag())) {
							logger.debug("Message handle end.");
						} else {
							logger.info("Call method ack(envelope.getDeliveryTag()==[{}]) failure. Reject message.");
							channel.basicReject(envelope.getDeliveryTag(), true);
						}
					}
				}
			});
		} catch (IOException e) {
			ErrorLogger.error(logger, e, "Call method channel.basicConsume() IOException message -> {}",
					e.getMessage());
		}
	}

	private boolean ack(long deliveryTag) {
		return ack0(deliveryTag, 0);
	}

	private boolean ack0(long deliveryTag, int retry) {
		if (retry == maxAckTotal) {
			ErrorLogger.error(logger, "Has been retry ack {}, Quit ack.", maxAckTotal);
			return false;
		}
		logger.debug("Has been retry ack {}, Do next ack.", retry);
		try {
			int reconnectionCount = 0;
			while (!isConnected()) {
				logger.debug("Detect connection isConnected() == false, Reconnection count {}.", (++reconnectionCount));
				closeAndReconnection();
				if (reconnectionCount == maxAckReconnection) {
					logger.debug("Reconnection count -> {}, Quit current ack.", reconnectionCount);
					break;
				}
			}
			if (isConnected()) {
				logger.debug("Last detect connection isConnected() == true, Reconnection count {}", reconnectionCount);
				channel.basicAck(deliveryTag, isMultipleAck);
				logger.debug("Method channel.basicAck() finished.");
				return true;
			} else {
				logger.error("Last detect connection isConnected() == false, Reconnection count {}", reconnectionCount);
				logger.error("Unable to call method channel.basicAck()");
				return ack0(deliveryTag, retry);
			}
		} catch (IOException e) {
			ErrorLogger.error(logger, e,
					"Call method channel.basicAck(deliveryTag==[{}], multiple==[false]) throw IOException -> {}",
					deliveryTag, e.getMessage());
			return ack0(deliveryTag, ++retry);
		}

	}

	@Override
	public boolean destroy() {
		logger.info("Call method RabbitMqReceiver.destroy()");
		closeConnection();
		return true;
	}

	@Override
	public String getName() {
		return receiverName;
	}

	@Deprecated
	public boolean initCallback(Callback<byte[]> callback) {
		if (this.callback != null)
			return false;
		this.callback = callback;
		return true;
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
