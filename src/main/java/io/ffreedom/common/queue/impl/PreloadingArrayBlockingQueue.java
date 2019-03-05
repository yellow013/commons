package io.ffreedom.common.queue.impl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.queue.api.MCQueue;
import io.ffreedom.common.queue.impl.base.LoadContainer;

@ThreadSafe
public class PreloadingArrayBlockingQueue<T> implements MCQueue<T> {

	private LoadContainer<T>[] containers;

	private final int size;
	private volatile AtomicInteger arrayCount = new AtomicInteger();

	private int readOffset;
	private int writeOffset;

	private ReentrantLock lock;

	private Condition notEmpty;
	private Condition notFull;

	private Logger logger = CommonLoggerFactory.getLogger(PreloadingArrayBlockingQueue.class);

	@SuppressWarnings("unchecked")
	public PreloadingArrayBlockingQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("size is too big.");
		}
		this.containers = new LoadContainer[size];
		for (int i = 0; i < size; i++)
			containers[i] = new LoadContainer<>();
		this.size = size;
		this.lock = new ReentrantLock();
		this.notEmpty = lock.newCondition();
		this.notFull = lock.newCondition();
	}

	@Override
	public boolean enqueue(T t) {
		try {
			lock.lockInterruptibly();
			while (arrayCount.get() == size)
				notFull.await();
			containers[writeOffset].loading(t);
			if (++writeOffset == size)
				writeOffset = 0;
			arrayCount.incrementAndGet();
			notEmpty.signal();
			return true;
		} catch (InterruptedException e) {
			logger.error("PreloadingArrayBlockingQueue.enQueue(t) : " + e.getMessage());
			return false;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public T dequeue() {
		try {
			lock.lockInterruptibly();
			while (arrayCount.get() == 0)
				notEmpty.await();
			T t = containers[readOffset].unloading();
			if (++readOffset == size)
				readOffset = 0;
			arrayCount.decrementAndGet();
			notFull.signal();
			return t;
		} catch (InterruptedException e) {
			logger.error("PreloadingArrayBlockingQueue.deQueue() : " + e.getMessage());
			return null;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

	}

	@Override
	public String getQueueName() {
		// TODO Auto-generated method stub
		return null;
	}

}
