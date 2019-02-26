package io.ffreedom.common.queue.api;

import io.ffreedom.common.functional.Processor;

/**
 * @author yellow013
 *
 * @param <T> Single Consumer Queue
 */
public abstract class SCQueue<T> implements Queue<T> {

	protected Processor<T> processor;

	public SCQueue(Processor<T> processor) {
		if (processor == null)
			throw new IllegalArgumentException("processor is null...");
		this.processor = processor;
	}

	public abstract void start();

	public abstract void stop();

}
