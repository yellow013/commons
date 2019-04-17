package io.ffreedom.common.queue.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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

	private AtomicBoolean isRun = new AtomicBoolean(false);

	private AtomicBoolean isClose = new AtomicBoolean(true);

	private String queueName;

	private JCToolsSPSCQueue(String queueName, int queueSize, RunMode mode, TimeUnit timeUnit, long delayTotal,
			Processor<T> processor) {
		super(processor);
		this.queue = new SpscArrayQueue<>(Math.max(queueSize, 64));
		this.queueName = StringUtil.isNullOrEmpty(queueName)
				? JCToolsSPSCQueue.class.getSimpleName() + "-" + Thread.currentThread().getName() + "-"
						+ Thread.currentThread().getId()
				: queueName;
		switch (mode) {
		case Auto:
			start();
			break;
		case Delay:
			ThreadUtil.sleep(timeUnit, delayTotal);
			start();
			break;
		default:
			break;
		}
	}

	public static <T> JCToolsSPSCQueue<T> autoRunQueue(int queueSize, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, queueSize, RunMode.Auto, null, 0L, processor);
	}

	public static <T> JCToolsSPSCQueue<T> autoRunQueue(String queueName, int queueSize, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, queueSize, RunMode.Auto, null, 0L, processor);
	}

	public static <T> JCToolsSPSCQueue<T> manualRunQueue(int queueSize, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, queueSize, RunMode.Manual, null, 0L, processor);
	}

	public static <T> JCToolsSPSCQueue<T> manualRunQueue(String queueName, int queueSize, Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, queueSize, RunMode.Manual, null, 0L, processor);
	}

	public static <T> JCToolsSPSCQueue<T> delayRunQueue(int queueSize, long delay, TimeUnit timeUnit,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(null, queueSize, RunMode.Delay, timeUnit, delay, processor);
	}

	public static <T> JCToolsSPSCQueue<T> delayRunQueue(String queueName, int queueSize, long delay, TimeUnit timeUnit,
			Processor<T> processor) {
		return new JCToolsSPSCQueue<>(queueName, queueSize, RunMode.Delay, timeUnit, delay, processor);
	}

	@Override
	@SpinWaiting
	public boolean enqueue(T t) {
		if (!isClose.get()) {
			logger.error("JCToolsSPSCQueue.enQueue(t) failure, This queue is closed...");
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

		JCToolsSPSCQueue<Integer> queue = JCToolsSPSCQueue.autoRunQueue(6, (value) -> {
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