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

	private ArrayBlockingQueue<T> queue;

	private Logger logger = CommonLoggerFactory.getLogger(ArrayBlockingMPSCQueue.class);

	private AtomicBoolean isRun = new AtomicBoolean(false);

	private AtomicBoolean isClose = new AtomicBoolean(true);

	private String queueName;

	private ArrayBlockingMPSCQueue(String queueName, int queueSize, RunMode mode, Processor<T> processor) {
		this(queueName, queueSize, mode, 0L, processor);
	}

	private ArrayBlockingMPSCQueue(String queueName, int queueSize, RunMode mode, long delayMillis,
			Processor<T> processor) {
		super(processor);
		this.queue = new ArrayBlockingQueue<>(Math.max(queueSize, 64));
		this.queueName = StringUtil.isNullOrEmpty(queueName)
				? ArrayBlockingMPSCQueue.class.getSimpleName() + "-" + Thread.currentThread().getName() + "-"
						+ Thread.currentThread().getId()
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

	public static <T> ArrayBlockingMPSCQueue<T> autoRunQueue(Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Auto, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoRunQueue(int queueSize, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Auto, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> autoRunQueue(String queueName, int queueSize, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Auto, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualRunQueue(Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Manual, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualRunQueue(int queueSize, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Manual, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> manualRunQueue(String queueName, int queueSize,
			Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Manual, processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayRunQueue(long delay, TimeUnit timeUnit, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, 64, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayRunQueue(int queueSize, long delay, TimeUnit timeUnit,
			Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(null, queueSize, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	public static <T> ArrayBlockingMPSCQueue<T> delayRunQueue(String queueName, int queueSize, long delay,
			TimeUnit timeUnit, Processor<T> processor) {
		return new ArrayBlockingMPSCQueue<>(queueName, queueSize, RunMode.Delay, timeUnit.toMillis(delay), processor);
	}

	@Override
	@SpinWaiting
	public boolean enqueue(T t) {
		if (!isClose.get()) {
			logger.error("ArrayBlockingMPSCQueue.enQueue(t) failure, This queue is closed...");
			return false;
		}
		while (!queue.offer(t))
			;
		return true;
	}

	@Override
	public String getQueueName() {
		return queueName;
	}

	public void start() {
		if (!isRun.compareAndSet(false, true)) {
			logger.error("Error call ->  This queue is started.");
			return;
		}
		ThreadUtil.startNewThread(() -> {
			try {
				while (isRun.get() || !queue.isEmpty()) {
					@SpinWaiting
					T t = queue.poll();
					if (t != null)
						processor.process(t);
				}
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

		ArrayBlockingMPSCQueue<Integer> queue = ArrayBlockingMPSCQueue.autoRunQueue(100, (value) -> {
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
