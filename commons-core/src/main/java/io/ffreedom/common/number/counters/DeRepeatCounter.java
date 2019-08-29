package io.ffreedom.common.number.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collections.MutableSets;

@ThreadSafe
public final class DeRepeatCounter<T> {

	private MutableSet<T> savedSet = MutableSets.newUnifiedSet(64);
	private volatile int counter = 0;

	private Lock lock = new ReentrantLock();

	public synchronized DeRepeatCounter<T> add(T t) {
		try {
			lock.lock();
			savedSet.add(t);
			counter = savedSet.size();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
		return this;
	}

	public DeRepeatCounter<T> subtract(T t) {
		try {
			lock.lock();
			savedSet.remove(t);
			counter = savedSet.size();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
		return this;
	}

	public long getValue() {
		return counter;
	}

	public static void main(String[] args) {

		DeRepeatCounter<String> deRepeatCounter = new DeRepeatCounter<String>();

		System.out.println(deRepeatCounter.add("").add("fsdaf").add("dsfsad").add("").add("aaa").add("aaa").getValue());

	}

}
