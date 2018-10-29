package io.ffreedom.transport.jeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import io.ffreedom.common.functional.MsgPipeline;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Receiver;
import io.ffreedom.transport.jeromq.config.JeroMqConfigurator;

public class JeroMqPipeline implements Receiver {

	private ZContext context;
	private Socket socket;

	private String receiverName;

	private MsgPipeline<byte[], byte[]> pipeline;
	private JeroMqConfigurator configurator;

	private volatile boolean isRun = true;

	public JeroMqPipeline(JeroMqConfigurator configurator, MsgPipeline<byte[], byte[]> pipeline) {
		if (configurator == null) {
			throw new NullPointerException("configurator is null in JeroMQReceiver init mothed !");
		}
		this.configurator = configurator;
		this.pipeline = pipeline;
		init();
	}

	private void init() {
		this.context = new ZContext(configurator.getIoThreads());
		this.socket = context.createSocket(ZMQ.REP);
		this.socket.bind(configurator.getHost());
		this.receiverName = "JeroMQ.REP$" + configurator.getHost();
	}

	@Override
	public void receive() {
		while (isRun) {
			byte[] recvBytes = socket.recv();
			byte[] bytes = pipeline.stream(recvBytes);
			if (bytes == null) {
				bytes = new byte[0];
			}
			socket.send(bytes);
		}
		return;
	}

	@Override
	public boolean destroy() {
		this.isRun = false;
		ThreadUtil.sleep(50);
		socket.close();
		context.close();
		return true;
	}

	@Override
	public String getName() {
		return receiverName;
	}

	public static void main(String[] args) {

		JeroMqPipeline receiver = new JeroMqPipeline(
				JeroMqConfigurator.builder().setIoThreads(10).setHost("tcp://*:5551").build(), (byte[] byteMsg) -> {
					System.out.println(new String(byteMsg));
					return null;
				});

		ThreadUtil.startNewThread(() -> receiver.receive());

		ThreadUtil.sleep(15000);

		receiver.destroy();

	}

	@Override
	public boolean isConnected() {
		return !context.isClosed();
	}

}
