package io.ffreedom.common.queue.impl.disruptor;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.queue.api.SCQueue;

/**
 * 
 * @author yellow013
 * @creation 2019年5月6日
 * @param <T>
 */
public class MPSCQueue<T> extends SCQueue<T> {

	public MPSCQueue(Processor<T> processor) {
		super(processor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean enqueue(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void startProcessThread() {
		// TODO Auto-generated method stub

	}

}
