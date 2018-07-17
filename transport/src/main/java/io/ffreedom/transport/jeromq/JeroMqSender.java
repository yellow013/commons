package io.ffreedom.transport.jeromq;

import javax.annotation.concurrent.NotThreadSafe;

import org.zeromq.ZMQ;

import io.ffreedom.transport.base.role.Sender;
import io.ffreedom.transport.jeromq.config.JeroMqConfigurator;

@NotThreadSafe
public class JeroMqSender implements Sender<byte[]> {

	private ZMQ.Context context;
	private ZMQ.Socket socket;

	private String requesterName;
	
	private JeroMqConfigurator configurator;

	public JeroMqSender(JeroMqConfigurator configurator) {
		if (configurator == null) {
			throw new NullPointerException("configurator is null in JeroMQPublisher init mothed !");
		}
		this.configurator = configurator;
		init();
	}
	
	private void init(){
		this.context = ZMQ.context(configurator.getIoThreads());
		this.socket = context.socket(ZMQ.REQ);
		this.socket.connect(configurator.getHost());
		this.requesterName = "JeroMQ.REQ$" + configurator.getHost();
	}

	@Override
	public void sent(byte[] msg) {
		socket.send(msg);
		socket.recv();
	}

	@Override
	public boolean destroy() {
		socket.close();
		context.term();
		return true;
	}

	@Override
	public String getName() {
		return requesterName;
	}

	public static void main(String[] args) {
		JeroMqConfigurator configurator = JeroMqConfigurator.builder().setIoThreads(1)
				.setHost("tcp://localhost:5551").build();

		JeroMqSender requester = new JeroMqSender(configurator);

		requester.sent("TEST MSG".getBytes());

		requester.destroy();
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
