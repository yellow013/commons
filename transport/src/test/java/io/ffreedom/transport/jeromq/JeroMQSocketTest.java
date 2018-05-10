package io.ffreedom.transport.jeromq;

import org.junit.Test;
import org.zeromq.ZMQ;

import io.ffreedom.common.utils.ThreadUtil;

public class JeroMQSocketTest implements Runnable {

	@Test
	public void jeroMQTest() {

		// start JeroMQ server
		Thread thread = ThreadUtil.startNewThread(new JeroMQSocketTest());

		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to server
		System.out.println("Test client start...");

		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://localhost:5555");

		String request = "This is test message";
		System.out.println("Client sending test message.");

		socket.send(request.getBytes());

		byte[] reply = socket.recv(0);

		System.out.println("Client received: " + new String(reply));

		socket.close();
		context.term();

		thread.interrupt();

	}

	@Override
	public void run() {

		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to clients
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://*:5555");

		System.out.println("Test server start and waiting message...");

		// Wait for next request from the client
		byte[] recBytes = socket.recv(0);
		String recStr = new String(recBytes);
		System.out.println("Server received: " + recStr);

		// Do some 'work'
		ThreadUtil.sleep(1000);

		// Send reply back to client
		String reply = "You send message is '" + recStr + "'.";
		socket.send(reply.getBytes(), 0);

		socket.close();
		context.term();

	}

}
