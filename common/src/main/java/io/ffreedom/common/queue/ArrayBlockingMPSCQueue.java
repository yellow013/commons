package io.ffreedom.common.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.base.QueueProcessor;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

public class ArrayBlockingMPSCQueue<T> implements SCQueue<T> {

	private ArrayBlockingQueue<T> queue;
	private QueueProcessor<T> processor;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicBoolean isRun = new AtomicBoolean(false);

	public ArrayBlockingMPSCQueue(int queueSize, boolean autoRun, QueueProcessor<T> processor) {
		super();
		this.queue = new ArrayBlockingQueue<>(queueSize);
		this.processor = processor;
		if (autoRun) {
			start();
		}
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

	public void start() {
		if (isRun.get()) {
			logger.error("error call : queue is started.");
			return;
		}
		isRun.set(true);
		ThreadUtil.startNewThread(() -> {
			try {
				while (isRun.get() || !queue.isEmpty()) {
					T t = queue.poll(5, TimeUnit.SECONDS);
					if (t != null) {
						processor.process(t);
					}
				}
			} catch (InterruptedException e) {
				logger.error("ArrayBlockingMPSCQueue.poll : " + e.getMessage());
			}
		});
	}

	@Override
	public void stop() {
		this.isRun.set(false);
	}

	public static void main(String[] args) {

		ArrayBlockingMPSCQueue<Integer> queue = new ArrayBlockingMPSCQueue<Integer>(100, true, (value) -> {
			System.out.println(value);
		});

		int i=0;
		
		for (;;) {
			if(i == 100000) {
				queue.stop();
			}
			queue.enQueue(++i);
		}

	}

	@Override
	public SCQueue<T> setProcessor(QueueProcessor<T> processor) {
		// TODO Auto-generated method stub
		return null;
	}

}
