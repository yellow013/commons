package io.ffreedom.transport.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.ArrayBlockingMPSCQueue;
import io.ffreedom.transport.base.role.Sender;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SocketSender implements Sender<byte[]> {

	private SocketConfigurator configurator;

	private Socket socket;

	private AtomicBoolean isRun = new AtomicBoolean(true);

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public SocketSender(SocketConfigurator configurator) {
		super();
		if (configurator == null) {
			throw new IllegalArgumentException("configurator or callback is null for init ");
		}
		this.configurator = configurator;
		init();
	}

	private void init() {
		try {
			this.socket = new Socket(configurator.getHost(), configurator.getPort());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean isConnected() {
		return socket == null ? false : socket.isConnected();
	}

	@Override
	public boolean destroy() {
		this.isRun.set(false);
		try {
			outputStream.close();
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String getName() {
		return "SocketSender -> " + socket.hashCode();
	}

	@Override
	public void sent(byte[] msg) {
		innerQueue.enQueue(msg);
	}

	private DataOutputStream outputStream;

	private void processSendQueue(byte[] msg) {
		try {
			if (isRun.get()) {
				if (outputStream == null) {
					outputStream = new DataOutputStream(socket.getOutputStream());
				}
				outputStream.write(msg);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			destroy();
		}
	}

	private ArrayBlockingMPSCQueue<byte[]> innerQueue = ArrayBlockingMPSCQueue.autoRunQueue(1024, bytes -> {
		processSendQueue(bytes);
	});

	public static void main(String[] args) {

		SocketConfigurator configurator = SocketConfigurator.builder().setHost("192.168.1.138").setPort(7901).build();

		SocketSender sender = new SocketSender(configurator);

		sender.sent("hello".getBytes());

	}

}
