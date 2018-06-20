package io.ffreedom.transport.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.ffreedom.common.functional.Callback;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class LocalSocketManager {

	private static Map<String, SocketTransceiver> serverSocketMap = new ConcurrentHashMap<>(8);

	/**
	 * 
	 * @param name
	 * @param port
	 * @param callback
	 * @return
	 */
	public static synchronized SocketTransceiver getSocketTransceiver(String name, int port,
			Callback<byte[]> callback) {
		String socketName = getSocketName(name, port);
		if (serverSocketMap.containsKey(socketName)) {
			return serverSocketMap.get(socketName);
		} else {
			if (port <= 7000 || port >= 8000) {
				throw new RuntimeException("port error.");
			}
			SocketTransceiver transceiver = new SocketTransceiver(SocketConfigurator.builder().setPort(port).build(),
					callback);
			serverSocketMap.put(socketName, transceiver);
			return transceiver;
		}
	}

	/**
	 * Return SocketTransceiver obj or null.
	 * 
	 * @param name
	 * @param port
	 * @return
	 */
	public static SocketTransceiver getSocketTransceiver(String name, int port) {
		if (serverSocketMap.containsKey(name)) {
			return serverSocketMap.get(name);
		} else {
			return null;
		}
	}

	private static String getSocketName(String name, int port) {
		return name + ":" + port;
	}

}
