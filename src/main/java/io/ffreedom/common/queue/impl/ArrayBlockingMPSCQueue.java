package io.ffreedom.common.queue.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.mark.SpinWaiting;
import io.ffreedom.common.queue.api.SCQueue;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;

public class ArrayBlockingMPSCQueue<T> extends SCQueue<T> {

	private ArrayBlockingQueue<T> innerQueue;

	private Logger logger = CommonLoggerFactory.getLogger(ArrayBlockingMPSCQueue.class);

	private AtomicBoolean isRun = new AtomicBoolean(false);

	private AtomicBoolean isClose = new AtomicBoolean(true);

	private String queueName;

	private ArrayBlockingMPSCQueue(String queueName, int capacity, RunMode mode, long delayMillis,
			Processor<T> processor) {
		super(processor);
		this.innerQueue = new ArrayBlockingQueue<>(Math.max(capacity, 64));
		this.queueName = StringUtil.isNullOrEmpty(queueName)
				? ArrayBlockingMPSCQueue.class.getSimpleName() + "-" + Thread.currentThread().getName()
				: queueName;
		switch (mode) {
		case Auto:
			start();
			break;
		case Delay:
			ThreadUtil.sleep(delayMillis);
			start();
			break;
		default:
			break;
		}
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoStartQueue(Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Auto, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoStartQueue(int capacity, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Auto, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoStartQueue(String queueName, int capacity, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Auto, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualStartQueue(Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Manual, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualStartQueue(int capacity, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Manual, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualStartQueue(String queueName, int capacity,
			Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Manual, 0L, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayStartQueue(long delay, TimeUnit timeUnit, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayStartQueue(int capacity, long delay, TimeUnit timeUnit,
			Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayStartQueue(String queueName, int capacity, long delay,
			TimeUnit timeUnit, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	@Override
	@SpinWaiting
	public boolean enqueue(T t) {
		if (!isClose.get()) {
			logger.error("ArrayBlockingMPSCQueue.enQueue(t) failure, This queue is closed...");
			return false;
		}
		try {
			while (!innerQueue.offer(t, 500, TimeUnit.MILLISECONDS))
				;
			return true;
		} catch (InterruptedException e) {
			logger.error("innerQueue.offer(t, 500, TimeUnit.MILLISECONDS) throws InterruptedException!");
			return false;
		}
	}

	@Override
	public String getQueueName() {
		return queueName;
	}

	@Override
	public void startProcessThread() {
		if (!isRun.compareAndSet(false, true)) {
			logger.error("Error call ->  This queue is started.");
			return;
		}
		ThreadUtil.startNewThread(() -> {
			try {
				while (isRun.get() || !innerQueue.isEmpty()) {
					@SpinWaiting
					T t = innerQueue.poll(500, TimeUnit.MILLISECONDS);
					if (t != null)
						processor.process(t);
				}
			} catch (InterruptedException e) {
				logger.error("innerQueue.poll(500, TimeUnit.MILLISECONDS) throws InterruptedException!");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, queueName);
	}

	@Override
	public void stop() {
		this.isRun.set(false);
		this.isClose.set(true);
	}

	public static void main(String[] args) {

		ArrayBlockingMPSCQueue<Integer> queue = ArrayBlockingMPSCQueue.autoStartQueue(100, (value) -> {
			System.out.println(value);
			ThreadUtil.sleep(500);
		});

		int i = 0;

		System.out.println(queue.getQueueName());
		for (;;) {
			queue.enqueue(++i);
			System.out.println("enqueue ->" + i);
		}

	}

}
