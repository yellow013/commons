package io.ffreedom.common.queue.api;

import java.util.concurrent.atomic.AtomicBoolean;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.number.RandomNumbers;

/**
 * @author yellow013
 *
 * @param <T> Single Consumer Queue
 */
public abstract class SCQueue<T> implements Queue<T> {

	protected Processor<T> processor;

	protected AtomicBoolean isRun = new AtomicBoolean(false);

	protected AtomicBoolean isClose = new AtomicBoolean(true);

	protected String queueName = "SCQueue-" + Integer.toString(RandomNumbers.randomUnsignedInt());

	public SCQueue(Processor<T> processor) {
		if (processor == null)
			throw new IllegalArgumentException("processor is null...");
		this.processor = processor;
	}

	protected abstract void startProcessThread();

	public void start() {
		startProcessThread();
	}

	public void stop() {
		this.isRun.set(false);
		this.isClose.set(true);
	}

	@Override
	public String getQueueName() {
		// TODO Auto-generated method stub
		return null;
	}

}
