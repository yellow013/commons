package io.ffreedom.common.concurrent.queue.impl;

import java.util.concurrent.TimeUnit;

import org.jctools.queues.SpscArrayQueue;
import org.slf4j.Logger;

import io.ffreedom.common.annotations.thread.SpinWaiting;
import io.ffreedom.common.concurrent.queue.api.SCQueue;
import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;

public class JCToolsSPSCQueue<E> extends SCQueue<E> {

	private SpscArrayQueue<E> queue;

	private Logger logger = CommonLoggerFactory.getLogger(JCToolsSPSCQueue.class);

	private WaitingStrategy waitingStrategy;

	public static enum WaitingStrategy {
		SpinWaiting, SleepWaiting,
	}

	private JCToolsSPSCQueue(String queueName, int capacity, RunMode mode, long delayMillis,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		super(processor);
		this.queue = new SpscArrayQueue<>(Math.max(capacity, 64));
		super.queueName = StringUtil.isNullOrEmpty(queueName)
				? JCToolsSPSCQueue.class.getSimpleName() + "-" + Thread.currentThread().getName()
				: queueName;
		this.waitingStrategy = waitingStrategy;
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

	public static <E> JCToolsSPSCQueue<E> autoStartQueue(WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Auto, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> autoStartQueue(int capacity, WaitingStrategy waitingStrategy,
			Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Auto, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> autoStartQueue(String queueName, int capacity,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Auto, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> manualStartQueue(WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Manual, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> manualStartQueue(int capacity, WaitingStrategy waitingStrategy,
			Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Manual, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> manualStartQueue(String queueName, int capacity,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Manual, 0L, waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> delayStartQueue(long delay, TimeUnit timeUnit,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Delay, timeUnit.toMillis(delay), waitingStrategy, processor);
	}

	public static <E> JCToolsSPSCQueue<E> delayStartQueue(int capacity, long delay, TimeUnit timeUnit,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Delay, timeUnit.toMillis(delay), waitingStrategy,
				processor);
	}

	public static <E> JCToolsSPSCQueue<E> delayStartQueue(String queueName, int capacity, long delay, TimeUnit timeUnit,
			WaitingStrategy waitingStrategy, Processor<E> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Delay, timeUnit.toMillis(delay), waitingStrategy,
				processor);
	}

	private void waiting() {
		switch (waitingStrategy) {
		case SpinWaiting:
			break;
		case SleepWaiting:
			ThreadUtil.sleep(50);
			break;
		default:
			break;
		}
	}

	@Override
	@SpinWaiting
	public boolean enqueue(E e) {
		if (!isClose.get()) {
			logger.error("enqueue(t) failure, This queue is closed...");
			return false;
		}
		while (!queue.offer(e))
			waiting();
		return true;
	}

	@Override
	public void startProcessThread() {
		if (!isRun.compareAndSet(false, true)) {
			logger.error("Error call -> This queue is started.");
			return;
		}
		ThreadUtil.startNewMaxPriorityThread(() -> {
			try {
				while (isRun.get() || !queue.isEmpty()) {
					@SpinWaiting
					E e = queue.poll();
					if (e != null)
						processor.process(e);
					else
						waiting();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}, queueName);
	}

	public static void main(String[] args) {

		JCToolsSPSCQueue<Integer> queue = JCToolsSPSCQueue.autoStartQueue(6, WaitingStrategy.SleepWaiting, (value) -> {
			System.out.println(value);
			ThreadUtil.sleep(500);
		});

		int i = 0;

		System.out.println(queue.getQueueName());
		for (;;) {
			queue.enqueue(++i);
			System.out.println("enqueue ->" + i);
			System.out.println("size -> " + queue.queue.size());
			System.out.println("capacity -> " + queue.queue.capacity());
		}

	}

}