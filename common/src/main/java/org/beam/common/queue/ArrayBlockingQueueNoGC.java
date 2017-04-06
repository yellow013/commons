package org.beam.common.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Logger;
import org.beam.common.log.CommonLoggerFactory;
import org.beam.common.queue.base.LoadContainer;
import org.beam.common.queue.base.MCQueue;

public class ArrayBlockingQueueNoGC<T> implements MCQueue<T> {

	private LoadContainer<T>[] containers;

	private int size;

	private int arrayCount;

	private int readOffset;
	private int writeOffset;

	private ReentrantLock lock;

	private Condition notEmpty;
	private Condition notFull;

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public ArrayBlockingQueueNoGC(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("size is too big.");
		}
		this.containers = new LoadContainer[size];
		this.size = size;
		this.lock = new ReentrantLock();
		this.notEmpty = lock.newCondition();
		this.notFull = lock.newCondition();
	}

	@Override
	public boolean enQueue(T t) {
		try {
			lock.lockInterruptibly();
			while (arrayCount == size) {
				notFull.await();
			}
			containers[writeOffset].loading(t);
			if (++writeOffset == size) {
				writeOffset = 0;
			}
			arrayCount++;
			notEmpty.signal();
			return true;
		} catch (InterruptedException e) {
			logger.error("ArrayBlockingQueueNoGC.enQueue(t) : " + e.getMessage());
			return false;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public T deQueue() {
		try {
			lock.lockInterruptibly();
			while (arrayCount == 0) {
				notEmpty.await();
			}
			T t = containers[readOffset].unloading();
			if (++readOffset == size) {
				readOffset = 0;
			}
			arrayCount--;
			notFull.signal();
			return t;
		} catch (InterruptedException e) {
			logger.error("ArrayBlockingQueueNoGC.deQueue() : " + e.getMessage());
			return null;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

	}

}
