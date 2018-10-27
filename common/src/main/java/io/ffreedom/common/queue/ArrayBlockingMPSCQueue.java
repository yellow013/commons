package io.ffreedom.common.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.base.QueueProcessor;
import io.ffreedom.common.queue.base.SCQueue;
import io.ffreedom.common.utils.ThreadUtil;

public class ArrayBlockingMPSCQueue<T> extends SCQueue<T> {

	private ArrayBlockingQueue<T> queue;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicBoolean isRun = new AtomicBoolean(false);

	private AtomicBoolean isClose = new AtomicBoolean(true);

	private String queueName;

	public ArrayBlockingMPSCQueue(String queueName, int queueSize, RunMode mode, TimeUnit timeUnit, long delayTota,
			QueueProcessor<T> processor) {
		super(processor);
		this.queue = new ArrayBlockingQueue<>(queueSize);
		this.queueName = queueName;
		switch (mode) {
		case Auto:
			start();
			break;
		case Delay:
			ThreadUtil.sleep(delayTota, timeUnit);
			start();
			break;
		default:
			break;
		}
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoRunQueue(int queueSize, QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Auto, null, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoRunQueue(String queueName, int queueSize,
			QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Auto, null, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualRunQueue(int queueSize, QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Manual, null, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualRunQueue(String queueName, int queueSize,
			QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Manual, null, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayRunQueue(int queueSize, TimeUnit timeUnit, long delayTota,
			QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Manual, timeUnit, delayTota, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayRunQueue(String queueName, int queueSize, TimeUnit timeUnit,
			long delayTota, QueueProcessor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Manual, timeUnit, delayTota, processor);
	}

	private enum RunMode {
		Auto, Manual, Delay
	}

	public boolean enQueue(T t) {
		try {
			if (!isClose.get()) {
				logger.error("ArrayBlockingMPSCQueue.enQueue(t) failure, This queue is closed...");
				return false;
			}
			queue.put(t);
			return true;
		} catch (InterruptedException e) {
			logger.error("queue.put(t) throw InterruptedException : {}", e.getMessage());
			return false;
		}
	}

	public void start() {
		if (!isRun.compareAndSet(false, true)) {
			logger.error("Error call ->  This queue is started.");
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
				logger.error("queue.poll(5, TimeUnit.SECONDS) : " + e.getMessage());
			}
		}, queueName == null ? String.valueOf(this.hashCode()) : queueName);
	}

	@Override
	public void stop() {
		this.isRun.set(false);
		this.isClose.set(true);
	}

	public static void main(String[] args) {

		ArrayBlockingMPSCQueue<Integer> queue = ArrayBlockingMPSCQueue.autoRunQueue(100, (value) -> {
			System.out.println(value);
		});

		int i = 0;

		for (;;) {
			if (i == 1000) {
				queue.stop();
			}
			queue.enQueue(++i);
		}

	}

}
