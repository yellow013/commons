package io.ffreedom.transport.jeromq;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import io.ffreedom.common.utils.ThreadUtil;

public class JeroMQPubSubTest {

	public static void main(String[] args) {

		JeroMQPubSubTest thisObj = new JeroMQPubSubTest();

		Publisher publisher = thisObj.new Publisher();
		Subscriber subscriber = thisObj.new Subscriber("TEST");

		ThreadUtil.startNewThread(() -> subscriber.recv());

		ThreadUtil.sleep(2000);

		publisher.sendMsg("TEST", "this is test msg.");
		publisher.sendMsg("TEST", "end.");

		publisher.destroy();
		subscriber.destroy();

	}

	class Publisher {

		Context context;
		Socket publisher;

		public Publisher() {
			context = ZMQ.context(1);
			publisher = context.socket(ZMQ.PUB);
			publisher.bind("tcp://*:5557");
		}

		public void sendMsg(String topic, String msg) {

			ThreadUtil.sleep(2000);
			publisher.sendMore("A");
			publisher.send(msg.getBytes(), ZMQ.NOBLOCK);

		}

		public boolean destroy() {
			publisher.close();
			context.term();
			return true;
		}
	}

	class Subscriber {

		private Context context;
		private Socket subscriber;

		public Subscriber(String topic) {
			context = ZMQ.context(1);
			subscriber = context.socket(ZMQ.SUB);
			subscriber.connect("tcp://*:5557");
			subscriber.subscribe(topic.getBytes());
		}

		public void recv() {
			// subscriber.subscribe("".getBytes());
			while (true) {
				byte[] recvBytes = subscriber.recv();
				String recvStr = new String(recvBytes);
				System.out.println("Received is : " + recvStr);
				if (recvStr.equals("end.")) {
					break;
				}
			}
		}

		public void destroy() {
			subscriber.close();
			context.term();
		}

	}

}
