package io.ffreedom.common.concurrent.queue.impl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.api.MCQueue;
import io.ffreedom.common.concurrent.queue.impl.base.LoadContainer;
import io.ffreedom.common.log.CommonLoggerFactory;

@ThreadSafe
public class PreloadingArrayBlockingQueue<E> implements MCQueue<E> {

	private LoadContainer<E>[] containers;

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
	public boolean enqueue(E e) {
		try {
			lock.lockInterruptibly();
			while (arrayCount.get() == size)
				notFull.await();
			containers[writeOffset].loading(e);
			if (++writeOffset == size)
				writeOffset = 0;
			arrayCount.incrementAndGet();
			notEmpty.signal();
			return true;
		} catch (InterruptedException exception) {
			logger.error("PreloadingArrayBlockingQueue.enQueue(t)", exception);
			return false;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E dequeue() {
		try {
			lock.lockInterruptibly();
			while (arrayCount.get() == 0)
				notEmpty.await();
			E e = containers[readOffset].unloading();
			if (++readOffset == size)
				readOffset = 0;
			arrayCount.decrementAndGet();
			notFull.signal();
			return e;
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
