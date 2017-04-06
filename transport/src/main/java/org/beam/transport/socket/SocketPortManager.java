package org.beam.transport.socket;

import org.beam.common.annotation.mark.CodeScalable;

public class SocketPortManager {

	private static SocketPortManager instance;

	static {
		instance = new SocketPortManager();
	}

	public static SocketPortManager instance() {
		return instance;
	}

	private int[] ports = new int[8];

	private boolean[] isUsed = new boolean[ports.length];

	private SocketPortManager() {
		for (int i = 0; i < ports.length; i++) {
			ports[i] = i + 9500;
		}
	}

	@CodeScalable
	public synchronized int getSocketPort() {
		for (int i = 0; i < isUsed.length; i++) {
			if (!isUsed[i]) {
				isUsed[i] = true;
				return ports[i];
			}
		}
		throw new RuntimeException("No ports available!");
	}

	public static void main(String[] args) {
	}

}
