package org.beam.transport.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalServerSocketManager {

	private static LocalServerSocketManager instance;
	static {
		instance = new LocalServerSocketManager();
	}

	public static LocalServerSocketManager instance() {
		return instance;
	}

	private Map<String, ServerSocket> serverSocketMap;

	private LocalServerSocketManager() {
		this.serverSocketMap = new ConcurrentHashMap<>(8);
	}

	public ServerSocket getServerSocket(String name, int port) {
		if (serverSocketMap.containsKey(name)) {
			return serverSocketMap.get(name);
		} else {
			if (port == 0 || port < 8000) {
				throw new RuntimeException("port error.");
			}
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				serverSocketMap.put(name, serverSocket);
				return serverSocket;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static void main(String[] args) {

	}

}
