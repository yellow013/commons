package org.beam.transport.jeromq;

import org.beam.common.callback.Callback;
import org.beam.transport.base.role.Receiver;
import org.beam.transport.jeromq.config.JeroMqConfigurator;
import org.zeromq.ZMQ;

public class JeroMqReceiver implements Receiver {

	private ZMQ.Context context;
	private ZMQ.Socket receiver;
	
	private String receiverName;

	private Callback<byte[]> callback;
	private JeroMqConfigurator configurator;

	private volatile boolean isRun = true;

	public JeroMqReceiver(JeroMqConfigurator configurator, Callback<byte[]> callback) {
		if (configurator == null) {
			throw new NullPointerException("configurator is null in JeroMQReceiver init mothed !");
		}
		this.configurator = configurator;
		this.callback = callback;
		init();
	}

	private void init() {
		this.context = ZMQ.context(configurator.ioThreads());
		this.receiver = context.socket(ZMQ.REP);
		this.receiver.bind(configurator.host());
		this.receiverName = "JeroMQ.REP$" + configurator.host();
	}

	@Override
	public void receive() {
		while (isRun) {
			byte[] msgBytes = receiver.recv();
			receiver.send(new byte[0]);
			callback.onEvent(msgBytes);
		}
		return;
	}

	@Override
	public void destroy() {
		this.isRun = false;
		receiver.close();
		context.term();
	}

	@Override
	public String getReceiverName() {
		return receiverName;
	}

	public static void main(String[] args) {

		JeroMqConfigurator configurator = JeroMqConfigurator.builder().setIoThreads(1).setHost("tcp://*:5551").build();

		JeroMqReceiver receiver = new JeroMqReceiver(configurator,
				(byte[] byteMsg) -> System.out.println(new String(byteMsg)));

		new Thread(() -> receiver.receive()).start();

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		receiver.destroy();

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
