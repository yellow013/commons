package org.beam.transport.jeromq;

import org.beam.common.callback.Callback;
import org.beam.transport.base.role.Subscriber;
import org.beam.transport.jeromq.config.JeroMqConfigurator;
import org.zeromq.ZMQ;

public class JeroMqSubscriber implements Subscriber {

	private ZMQ.Context context;
	private ZMQ.Socket subscriber;

	private String subscriberName;

	private Callback<byte[]> callback;
	private JeroMqConfigurator configurator;

	private volatile boolean isRun = true;

	public JeroMqSubscriber(JeroMqConfigurator configurator, Callback<byte[]> callback) {
		if (configurator == null) {
			throw new NullPointerException("configurator is null in JeroMQSubscriber init mothed !");
		}
		this.configurator = configurator;
		this.callback = callback;
		init();
	}

	private void init() {
		this.context = ZMQ.context(configurator.ioThreads());
		this.subscriber = context.socket(ZMQ.SUB);
		this.subscriber.connect(configurator.host());
		this.subscriber.subscribe(configurator.topic().getBytes());
		this.subscriberName = "JeroMQ.SUB$" + configurator.host() + "::" + configurator.topic();
	}

	@Override
	public void subscribe() {
		while (isRun) {
			subscriber.recv();
			byte[] msgBytes = subscriber.recv();
			callback.onEvent(msgBytes);
		}
	}

	@Override
	public void destroy() {
		this.isRun = false;
		subscriber.close();
		context.term();
	}

	@Override
	public String getSubscriberName() {
		return subscriberName;
	}

	public static void main(String[] args) {
		JeroMqConfigurator configurator = JeroMqConfigurator.builder().setHost("tcp://*:5555").setIoThreads(1)
				.setTopic("").build();

		JeroMqSubscriber jeroMQSubscriber = new JeroMqSubscriber(configurator,
				(byte[] byteMsg) -> System.out.println(new String(byteMsg)));

		jeroMQSubscriber.subscribe();
	}

	@Override
	public boolean isConnected() {
		return false;
	}

}
