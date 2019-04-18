package io.ffreedom.common.queue.impl;

import java.util.concurrent.TimeUnit;

import org.jctools.queues.SpscArrayQueue;
import org.slf4j.Logger;

import io.ffreedom.common.functional.Processor;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.mark.SpinWaiting;
import io.ffreedom.common.queue.api.SCQueue;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.common.utils.ThreadUtil;

public class JCToolsSPSCQueue<T> extends SCQueue<T> {

	private SpscArrayQueue<T> queue;

	private Logger logger = CommonLoggerFactory.getLogger(JCToolsSPSCQueue.class);

	private String queueName;

	private WaitingStrategy strategy;

	public static enum WaitingStrategy {

		SpinWaiting, SleepWaiting,

	}

	private JCToolsSPSCQueue(String queueName, int capacity, RunMode mode, long delayMillis, WaitingStrategy strategy,
			Processor<T> processor) {
		super(processor);
		this.queue = new SpscArrayQueue<>(Math.max(capacity, 64));
		this.queueName = StringUtil.isNullOrEmpty(queueName)
				? JCToolsSPSCQueue.class.getSimpleName() + "-" + Thread.currentThread().getName()
				: queueName;
		this.strategy = strategy;
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

	public static <T> JCToolsSPSCQueue<T> autoStartQueue(WaitingStrategy strategy, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Auto, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> autoStartQueue(int capacity, WaitingStrategy strategy,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Auto, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> autoStartQueue(String queueName, int capacity, WaitingStrategy strategy,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Auto, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> manualStartQueue(WaitingStrategy strategy, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Manual, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> manualStartQueue(int capacity, WaitingStrategy strategy,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Manual, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> manualStartQueue(String queueName, int capacity, WaitingStrategy strategy,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Manual, 0L, strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> delayStartQueue(long delay, TimeUnit timeUnit, WaitingStrategy strategy,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, 64, RunMode.Delay, timeUnit.toMillis(delay), strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> delayStartQueue(int capacity, long delay, TimeUnit timeUnit,
			WaitingStrategy strategy, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, capacity, RunMode.Delay, timeUnit.toMillis(delay), strategy, processor);
	}

	public static <T> JCToolsSPSCQueue<T> delayStartQueue(String queueName, int capacity, long delay,
			TimeUnit timeUnit, WaitingStrategy strategy, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, capacity, RunMode.Delay, timeUnit.toMillis(delay), strategy,
				processor);
	}

	private void waiting() {
		switch (strategy) {
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
	public boolean enqueue(T t) {
		if (!isClose.get()) {
			logger.error("JCToolsSPSCQueue.enQueue(t) failure, This queue is closed...");
			return false;
		}
		while (!queue.offer(t))
			waiting();
		return true;
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
				while (isRun.get() || !queue.isEmpty()) {
					@SpinWaiting
					T t = queue.poll();
					if (t != null)
						processor.process(t);
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