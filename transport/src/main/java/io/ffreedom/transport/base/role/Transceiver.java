package io.ffreedom.transport.base.role;

public interface Transceiver<T> extends Receiver {

	InnerSender<T> getInnerSender();

	interface InnerSender<T> {
		void send(T msg);
	}

	void startSend();

}
