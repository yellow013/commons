package io.ffreedom.common.concurrent.queue.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;

import io.ffreedom.common.annotations.thread.SpinWaiting;
import io.ffreedom.common.concurrent.queue.api.SCQueue;
import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;

public class ArrayBlockingMPSCQueue<E> extends SCQueue<E> {

	private ArrayBlockingQueue<E> innerQueue;

	private Logger logger = CommonLoggerFactory.getLogger(ArrayBlockingMPSCQueue.class);

	private AtomicBoolean isRun = new AtomicBoolean(false);

	private AtomicBoolean isClose = new AtomicBoolean(true);

	private ArrayBlockingMPSCQueue(String queueName, int capacity, RunMode mode, long delayMillis,
			Processor<E> processor) {
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

	public static <E> ArrayBlockingMPSCQueue<E> autoStartQueue(Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Auto, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> autoStartQueue(int capacity, Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Auto, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> autoStartQueue(String queueName, int capacity, Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Auto, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> manualStartQueue(Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Manual, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> manualStartQueue(int capacity, Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Manual, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> manualStartQueue(String queueName, int capacity,
			Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Manual, 0L, processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> delayStartQueue(long delay, TimeUnit timeUnit, Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> delayStartQueue(int capacity, long delay, TimeUnit timeUnit,
			Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(null, capacity, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <E> ArrayBlockingMPSCQueue<E> delayStartQueue(String queueName, int capacity, long delay,
			TimeUnit timeUnit, Processor<E> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, capacity, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	@Override
	@SpinWaiting
	public boolean enqueue(E e) {
		if (!isClose.get()) {
			logger.error("enqueue(t) failure, This queue is closed...");
			return false;
		}
		try {
			while (!innerQueue.offer(e, 500, TimeUnit.MILLISECONDS))
				;
			return true;
		} catch (InterruptedException exception) {
			logger.error("innerQueue.offer(t, 500, TimeUnit.MILLISECONDS) throws InterruptedException!", exception);
			return false;
		}
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
					E e = innerQueue.poll(500, TimeUnit.MILLISECONDS);
					if (e != null)
						processor.process(e);
				}
			} catch (InterruptedException e) {
				logger.error("innerQueue.poll(500, TimeUnit.MILLISECONDS) throws InterruptedException!", e);
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
