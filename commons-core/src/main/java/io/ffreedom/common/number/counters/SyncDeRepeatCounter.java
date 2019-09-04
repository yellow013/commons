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
	private volatile int count = 0;
	private final int initCount;

	public SyncDeRepeatCounter() {
		this(0);
	}

	public SyncDeRepeatCounter(int initCount) {
		this.initCount = initCount;
	}

	public synchronized SyncDeRepeatCounter<T> add(T t) {
		deRepeatSet.add(t);
		count = deRepeatSet.size();
		return this;
	}

	public synchronized SyncDeRepeatCounter<T> subtract(T t) {
		deRepeatSet.remove(t);
		count = deRepeatSet.size();
		return this;
	}

	public synchronized SyncDeRepeatCounter<T> clear(T t) {
		deRepeatSet.clear();
		count = 0;
		return this;
	}

	public long count() {
		return initCount + count;
	}

	public static void main(String[] args) {

		SyncDeRepeatCounter<String> deRepeatCounter = new SyncDeRepeatCounter<String>(100);

		System.out.println(deRepeatCounter.add("").add("fsdaf").add("dsfsad").add("").add("aaa").add("aaa").count());

	}

}
