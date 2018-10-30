package io.ffreedom.transport.jeromq;

import java.util.concurrent.atomic.AtomicBoolean;

import org.zeromq.ZMQ;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Callback;
import io.ffreedom.transport.base.role.Subscriber;
import io.ffreedom.transport.jeromq.config.JeroMqConfigurator;

public class JeroMqSubscriber implements Subscriber {

	private ZMQ.Context context;
	private ZMQ.Socket subscriber;

	private String subscriberName;

	private Callback<byte[]> callback;
	private JeroMqConfigurator configurator;

	private AtomicBoolean isRun = new AtomicBoolean(true);

	public JeroMqSubscriber(JeroMqConfigurator configurator, Callback<byte[]> callback) {
		if (configurator == null) {
			throw new NullPointerException("configurator is null in JeroMQSubscriber init mothed !");
		}
		this.configurator = configurator;
		this.callback = callback;
		init();
	}

	private void init() {
		this.context = ZMQ.context(configurator.getIoThreads());
		this.subscriber = context.socket(ZMQ.SUB);
		this.subscriber.connect(configurator.getHost());
		this.subscriber.subscribe(configurator.getTopic().getBytes());
		this.subscriberName = "JeroMQ.SUB$" + configurator.getHost() + "::" + configurator.getTopic();
	}

	@Override
	public void subscribe() {
		while (isRun.get()) {
			subscriber.recv();
			byte[] msgBytes = subscriber.recv();
			callback.accept(msgBytes);
		}
	}

	@Override
	public boolean destroy() {
		this.isRun.set(false);
		subscriber.close();
		context.term();
		return true;
	}

	@Override
	public String getName() {
		return subscriberName;
	}

	public static void main(String[] args) {

		JeroMqSubscriber jeroMQSubscriber = new JeroMqSubscriber(
				JeroMqConfigurator.builder().setHost("").setIoThreads(2).setTopic("").build(),
				(byte[] byteMsg) -> System.out.println(new String(byteMsg, Charsets.UTF8)));

		jeroMQSubscriber.subscribe();
	}

	@Override
	public boolean isConnected() {
		return false;
	}

}
