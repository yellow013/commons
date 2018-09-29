package io.ffreedom.common.queue.base;

public abstract class SCQueue<T> implements Queue<T> {

	protected QueueProcessor<T> processor;

	public SCQueue(QueueProcessor<T> processor) {
		if (processor == null) {
			throw new IllegalArgumentException("processor is null...");
		}
		this.processor = processor;
	}

	public abstract void start();

	public abstract void stop();

	public enum RunMode {
		Auto, Manual, Delay
	}

}
