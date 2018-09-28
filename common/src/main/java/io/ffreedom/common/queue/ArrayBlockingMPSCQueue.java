package io.ffreedom.common.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.base.QueueProcessor;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

public class ArrayBlockingMPSCQueue<T> extends SCQueue<T> {

	private ArrayBlockingQueue<T> queue;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicBoolean isRun = new AtomicBoolean(false);
	
	private AtomicBoolean isClose = new AtomicBoolean(true);

	public ArrayBlockingMPSCQueue(int queueSize, boolean autoRun, QueueProcessor<T> processor) {
		super(processor);
		this.queue = new ArrayBlockingQueue<>(queueSize);
		if (autoRun) {
			start();
		}
	}

	public boolean enQueue(T t) {
		try {
			if(!isClose.get()) {
				logger.error("ArrayBlockingMPSCQueue.enQueue(t) failure, This queue is closed...");
				return false;
			}
			queue.put(t);
			return true;
		} catch (InterruptedException e) {
			logger.error("ArrayBlockingMPSCQueue.enQueue(t) : " + e.getMessage());
			return false;
		}
	}

	public void start() {
		if (isRun.compareAndSet(false, true)) {
			logger.error("error call : queue is started.");
			return;
		}
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
		this.isClose.set(true);
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

}
