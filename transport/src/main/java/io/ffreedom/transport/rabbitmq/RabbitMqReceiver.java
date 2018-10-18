package io.ffreedom.transport.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.log.UseLogger;
import io.ffreedom.common.utils.StringUtil;
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
			UseLogger.error(logger, e,
					"Method channel.queueDeclare(queue==[{}], durable==[{]}, exclusive==[{}], autoDelete==[{}], arguments==null) IOException message -> {}",
					receiveQueue, configurator.isDurable(), configurator.isExclusive(), configurator.isAutoDelete(),
					e.getMessage());
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
						UseLogger.error(logger, e, "Call method callback.accept(body) throw Exception -> {}",
								e.getMessage());
						if (StringUtil.isNullOrEmpty(errorMsgToExchange)) {
							// message to errorMsgExchange
							logger.info("Exception handling -> Msg [{}] sent to ErrorMsgExchange!",
									new String(body, Charsets.UTF8));
							channel.basicPublish(errorMsgToExchange, "", null, body);
							logger.info("Exception handling -> Sent to ErrorMsgExchange finished.");
						} else {
							// 退回消息->关闭连接
							logger.info("Exception handling -> Reject Msg [{}]", new String(body, Charsets.UTF8));
							channel.basicReject(envelope.getDeliveryTag(), true);
							logger.info("Exception handling -> Reject Msg finished.");
							destroy();
						}
					}
					if (!isAutoAck) {
						try {
							int ack = 0;
							while (!isConnected()) {
								logger.error("Detect connection isConnected() == false, ack {}", (++ack));
								destroy();
								ThreadUtil.sleep(configurator.getRecoveryInterval());
								createConnection();
								if (ack == maxAckTotal) {
									logger.error("Retry createConnection count -> {}, Quit ack.", ack);
									break;
								}
							}
							if (isConnected()) {
								logger.error("Last detect connection isConnected() == true, ack {}", ack);
								channel.basicAck(envelope.getDeliveryTag(), false);
								logger.info("Method channel.basicAck() finished.");
							} else {
								logger.error("Last detect connection isConnected() == false, ack {}", ack);
								logger.error("Unable to call method channel.basicAck()");
							}
						} catch (IOException e) {
							UseLogger.error(logger, e,
									"Call method channel.basicAck(deliveryTag==[{}], multiple==[false]) throw IOException -> {}",
									envelope.getDeliveryTag(), e.getMessage());
						}
					}
				}
			});
		} catch (IOException e) {
			UseLogger.error(logger, e, "Call method channel.basicConsume() IOException message -> {}", e.getMessage());
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

	public static void main(String[] args) {
		RabbitMqReceiver receiver = new RabbitMqReceiver("", RmqReceiverConfigurator.configuration()
				.setConnectionParam("", 0).setUserParam("", "").setReceiveQueue("").setAutomaticRecovery(true),
				(byte[] msg) -> {
					System.out.println(new String(msg, Charsets.UTF8));
				});

		receiver.receive();

	}

}
