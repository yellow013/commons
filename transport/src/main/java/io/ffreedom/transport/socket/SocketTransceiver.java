package io.ffreedom.transport.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.IOUtils;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.queue.disruptor.SPSCQueue;
import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.base.role.BaseTransceiver;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SocketTransceiver extends BaseTransceiver<String> {

	private SocketConfigurator configurator;
	private Callback<byte[]> callback;

	private Socket socket;

	private Writer writer;

	private AtomicBoolean isReceiving = new AtomicBoolean(false);
	private AtomicBoolean isRun = new AtomicBoolean(false);

	/**
	 * @param configurator
	 * @param callback
	 * @param serverSocket
	 */
	public SocketTransceiver(SocketConfigurator configurator, Callback<byte[]> callback) {
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
		return false;
	}

	@Override
	public boolean destroy() {
		this.isReceiving.set(false);
		try {
			if (writer != null) {
				writer.close();
			}
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
		return null;
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
		ThreadUtil.startNewThread(() -> {
			InputStream inputStream = null;
			while (isRun.get()) {
				try {
					inputStream = socket.getInputStream();
					int available = inputStream.available();
					if (available == 0) {
						ThreadUtil.sleep(configurator.getReceiveInterval());
						continue;
					}
					byte[] bytes = new byte[available];
					IOUtils.read(inputStream, bytes);
					callback.accept(bytes);
				} catch (IOException e) {
					e.printStackTrace();
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
		isReceiving.set(true);
	}

	private void processSendQueue(String msg) {
		try {
			if (isRun.get()) {
				if (writer == null) {
					this.writer = new OutputStreamWriter(socket.getOutputStream());
				}
				writer.write(msg);
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			destroy();
		}
	}

	@Override
	protected SCQueue<String> initSendQueue() {
		return new SPSCQueue<>(1024, true, (msg) -> {
			processSendQueue(msg);
		});
	}

}
