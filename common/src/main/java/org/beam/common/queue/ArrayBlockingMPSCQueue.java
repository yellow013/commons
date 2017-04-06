package org.beam.common.queue;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.Logger;
import org.beam.common.log.CommonLoggerFactory;
import org.beam.common.queue.base.QueueProcessor;
import org.beam.common.queue.base.SCQueue;

public class ArrayBlockingMPSCQueue<T> implements SCQueue<T> {

	private ArrayBlockingQueue<T> queue;
	private QueueProcessor<T> processor;

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	public ArrayBlockingMPSCQueue(int queueSize, QueueProcessor<T> processor) {
		this.queue = new ArrayBlockingQueue<>(queueSize);
		this.processor = processor;
	}

	public boolean enQueue(T t) {
		try {
			queue.put(t);
			return true;
		} catch (InterruptedException e) {
			logger.error("ArrayBlockingMPSCQueue.enQueue(t) : " + e.getMessage());
			return false;
		}
	}

	public void startProcess() {
		new Thread(() -> {
			try {
				for (;;) {
					T t = queue.take();
					processor.process(t);
				}
			} catch (InterruptedException e) {
				logger.error("ArrayBlockingMPSCQueue.process : " + e.getMessage());
			}
		}).start();
	}

}
