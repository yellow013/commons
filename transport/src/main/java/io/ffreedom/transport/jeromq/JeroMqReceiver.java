package io.ffreedom.transport.jeromq;

import org.zeromq.ZMQ;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Receiver;
import io.ffreedom.transport.jeromq.config.JeroMqConfigurator;

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
		this.context = ZMQ.context(configurator.getIoThreads());
		this.receiver = context.socket(ZMQ.REP);
		this.receiver.bind(configurator.getHost());
		this.receiverName = "JeroMQ.REP$" + configurator.getHost();
	}

	@Override
	public void receive() {
		while (isRun) {
			byte[] msgBytes = receiver.recv();
			receiver.send(new byte[0]);
			callback.accept(msgBytes);
		}
		return;
	}

	@Override
	public boolean destroy() {
		this.isRun = false;
		receiver.close();
		context.term();
		return true;
	}

	@Override
	public String getName() {
		return receiverName;
	}

	public static void main(String[] args) {

		JeroMqConfigurator configurator = JeroMqConfigurator.builder().setIoThreads(1).setHost("tcp://*:5551").build();

		JeroMqReceiver receiver = new JeroMqReceiver(configurator,
				(byte[] byteMsg) -> System.out.println(new String(byteMsg)));

		ThreadUtil.startNewThread(() -> receiver.receive());

		ThreadUtil.sleep(15000);

		receiver.destroy();

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
