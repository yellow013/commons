package io.ffreedom.common.queue.base;

import io.ffreedom.common.functional.Processor;

/**
 * 
 * 
 * @author Phoneix
 *
 * @param <T> Single Consumer Queue
 */
public abstract class SCQueue<T> implements Queue<T> {

	protected Processor<T> processor;

	public SCQueue(Processor<T> processor) {
		if (processor == null) {
			throw new IllegalArgumentException("processor is null...");
		}
		this.processor = processor;
	}

	public abstract void start();

	public abstract void stop();


}
