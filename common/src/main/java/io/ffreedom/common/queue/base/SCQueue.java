package io.ffreedom.common.queue.base;

public interface SCQueue<T> extends Queue<T> {

	void start();

	void stop();
	
	SCQueue<T> setProcessor(QueueProcessor<T> processor);

	public enum RunMode {
		Auto, Manual, Delay
	}

}
