package io.ffreedom.transport.jeromq;

import org.zeromq.ZMQ;

import io.ffreedom.common.annotation.thread.ThreadUnsafe;
import io.ffreedom.transport.base.role.Sender;
import io.ffreedom.transport.jeromq.config.JeroMqConfigurator;

public class JeroMqSender implements Sender<byte[]> {

	private ZMQ.Context context;
	private ZMQ.Socket requester;

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
		this.requester = context.socket(ZMQ.REQ);
		this.requester.connect(configurator.getHost());
		this.requesterName = "JeroMQ.REQ$" + configurator.getHost();
	}

	@ThreadUnsafe
	@Override
	public void send(byte[] msg) {
		requester.send(msg);
		requester.recv();
	}

	@Override
	public void destroy() {
		requester.close();
		context.term();
	}

	@Override
	public String getName() {
		return requesterName;
	}

	public static void main(String[] args) {
		JeroMqConfigurator configurator = JeroMqConfigurator.builder().setIoThreads(1)
				.setHost("tcp://localhost:5551").build();

		JeroMqSender requester = new JeroMqSender(configurator);

		requester.send("TEST MSG".getBytes());

		requester.destroy();
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}
