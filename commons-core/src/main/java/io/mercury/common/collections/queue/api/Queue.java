package io.mercury.common.collections.queue.api;

public interface Queue<E> {

	boolean enqueue(E e);

	default E poll() {
		throw new UnsupportedOperationException("Interface [Queue] defined method [poll()] is not implement");
	}

	String name();

	public static enum WaitingStrategy {
		SpinWaiting, SleepWaiting,
	}

}