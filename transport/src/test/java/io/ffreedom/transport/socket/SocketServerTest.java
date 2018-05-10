package io.ffreedom.transport.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import io.ffreedom.common.utils.ThreadUtil;
import io.ffreedom.transport.socket.SocketTransceiver;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SocketServerTest {

	private static String host = "127.0.0.1";
	private static int port = 7900;

	public static void main(String[] args) {

		SocketTransceiver transceiver = new SocketTransceiver(
				SocketConfigurator.builder().setHost(host).setPort(port).build(), (bytes) -> {
					System.out.println(new String(bytes));
				});

		try {
			Socket client = new Socket(host, port);
			Writer writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("Hello From Client");
			writer.flush();
			writer.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		transceiver.receive();

		ThreadUtil.sleep(20000);

		transceiver.destroy();

	}

}