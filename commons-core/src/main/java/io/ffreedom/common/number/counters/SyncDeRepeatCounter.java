package io.ffreedom.common.number.counters;

import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collections.MutableSets;

/**
 * 
 * 
 * @author yellow013
 *
 * @param <T>
 */
@ThreadSafe
public final class SyncDeRepeatCounter<T> {

	private MutableSet<T> deRepeatSet = MutableSets.newUnifiedSet(64);
	private volatile int counter = 0;

//	private Lock lock = new ReentrantLock();

	public synchronized SyncDeRepeatCounter<T> add(T t) {
//		try {
//			lock.lock();
		deRepeatSet.add(t);
		counter = deRepeatSet.size();
//		} catch (Exception e) {
//		} finally {
//			lock.unlock();
//		}
		return this;
	}

	public SyncDeRepeatCounter<T> subtract(T t) {
//		try {
//			lock.lock();
		deRepeatSet.remove(t);
		counter = deRepeatSet.size();
//		} catch (Exception e) {
//		} finally {
//			lock.unlock();
//		}
		return this;
	}

	public long getValue() {
		return counter;
	}

	public static void main(String[] args) {

		SyncDeRepeatCounter<String> deRepeatCounter = new SyncDeRepeatCounter<String>();

		System.out.println(deRepeatCounter.add("").add("fsdaf").add("dsfsad").add("").add("aaa").add("aaa").getValue());

	}

}
