package io.ffreedom.transport.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.Receiver;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SocketReceiver implements Receiver {

	private SocketConfigurator configurator;
	private Callback<byte[]> callback;

	private Socket socket;

	private AtomicBoolean isReceiving = new AtomicBoolean(false);
	private AtomicBoolean isRun = new AtomicBoolean(false);

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param configurator
	 * @param callback
	 * @param serverSocket
	 */
	public SocketReceiver(SocketConfigurator configurator, Callback<byte[]> callback) {
		super();
		if (configurator == null || callback == null) {
			throw new IllegalArgumentException("configurator or callback is null for init ");
		}
		this.configurator = configurator;
		this.callback = callback;
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
		return "SocketReceiver -> " + socket.hashCode();
	}

	@Override
	public void receive() {
		if (!isRun.get()) {
			isRun.set(true);
		}
		if (!isReceiving.get()) {
			startReceiveThread();
		}
	}

	private synchronized void startReceiveThread() {
		if (isReceiving.get()) {
			return;
		}
		isReceiving.set(true);
		ThreadUtil.startNewThread(() -> {
			InputStream inputStream = null;
			try {
				inputStream = socket.getInputStream();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			while (isRun.get()) {
				try {
					int available = inputStream.available();
					if (available == 0) {
						ThreadUtil.sleep(configurator.getReceiveInterval());
						continue;
					}
					byte[] bytes = new byte[available];
					IOUtils.read(inputStream, bytes);
					callback.accept(bytes);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					try {
						inputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					destroy();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {

		SocketConfigurator configurator = SocketConfigurator.builder().setHost("192.168.1.138").setPort(7901).build();

		SocketReceiver receiver = new SocketReceiver(configurator, bytes -> {
			System.out.println(new String(bytes));
		});

		receiver.receive();

	}

}
