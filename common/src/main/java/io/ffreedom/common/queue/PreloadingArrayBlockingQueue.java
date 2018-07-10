package io.ffreedom.common.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.logging.log4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.common.queue.base.LoadContainer;
import io.ffreedom.common.queue.base.MCQueue;

@ThreadSafe
public class PreloadingArrayBlockingQueue<T> implements MCQueue<T> {

	private LoadContainer<T>[] containers;

	private volatile int size;
	private volatile int arrayCount;

	private int readOffset;
	private int writeOffset;

	private ReentrantLock lock;

	private Condition notEmpty;
	private Condition notFull;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public PreloadingArrayBlockingQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("size is too big.");
		}
		this.containers = new LoadContainer[size];
		for (int i = 0; i < size; i++) {
			containers[i] = new LoadContainer<>();
		}
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
			logger.error("PreloadingArrayBlockingQueue.enQueue(t) : " + e.getMessage());
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
			logger.error("PreloadingArrayBlockingQueue.deQueue() : " + e.getMessage());
			return null;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		
	}

}
