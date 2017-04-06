package org.beam.transport.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.beam.common.charsets.Charsets;

public class ClientTest {

	public static void main(String[] args) {
		for (int i = 0; i < 15000; i++) {
			new Thread(new ClientTest().new TestRunnable(i)).start();
		}
	}

	class TestRunnable implements Runnable {

		private int id;

		public TestRunnable(int id) {
			this.id= id;
		}

		@Override
		public void run() {
			Socket socket = null;
			OutputStream outputStream = null;
			try {
				socket = new Socket("127.0.0.1", 9500);
				outputStream = socket.getOutputStream();
				for (int j = 0; j < 5; j++) {
					Long currentTimeMillis = System.currentTimeMillis();
					String msg = "Thread" + id + "->" + currentTimeMillis.toString();
					outputStream.write(msg.getBytes(Charsets.UTF8));
					outputStream.flush();
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println(id + "->" + e.getMessage());
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
