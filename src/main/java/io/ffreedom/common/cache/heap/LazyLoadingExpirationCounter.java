package io.ffreedom.common.cache.heap;

import java.time.Duration;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.iterator.MutableLongIterator;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.ffreedom.common.collect.ECollections;

@NotThreadSafe
public class LazyLoadingExpirationCounter implements Counter<LazyLoadingExpirationCounter> {

	private long value = 0L;

	// TODO 增加强一致性 使用自旋锁
	// private AtomicBoolean spin;

	private MutableLongLongMap timeToTag;
	private MutableLongLongMap tagToDelta;
	private MutableLongList effectiveTimes;

	private long expireNanos;

	public LazyLoadingExpirationCounter(Duration expireTime, int capacity) {
		this.expireNanos = expireTime.toNanos();
		this.timeToTag = ECollections.newLongLongHashMap(capacity);
		this.tagToDelta = ECollections.newLongLongHashMap(capacity);
		this.effectiveTimes = ECollections.newLongArrayList(capacity);
	}

	private void add(long delta) {
		value += delta;
	}

	@Override
	public LazyLoadingExpirationCounter add(long tag, long delta) {
		if (!tagToDelta.containsKey(tag)) {
			long time = System.nanoTime();
			effectiveTimes.add(time);
			timeToTag.put(time, tag);
			tagToDelta.put(tag, delta);
			add(delta);
		}
		return this;
	}

	@Override
	public LazyLoadingExpirationCounter subtract(long tag, long delta) {
		return add(tag, -delta);
	}

	@Override
	public long getValue() {
		long baseTime = System.nanoTime() - expireNanos;
		MutableLongIterator effectiveTimeIterator = effectiveTimes.longIterator();
		while (effectiveTimeIterator.hasNext()) {
			long time = effectiveTimeIterator.next();
			if (time < baseTime) {
				clearTime(time);
				effectiveTimeIterator.remove();
			} else
				break;
		}
		return value;
	}

	private void clearTime(long time) {
		long tag = timeToTag.get(time);
		long delta = tagToDelta.get(tag);
		add(-delta);
		timeToTag.remove(time);
		tagToDelta.remove(tag);
	}

	public static void main(String[] args) {

//		LazyLoadingExpirationCounter counter = new LazyLoadingExpirationCounter(Duration.ofMillis(10000), 1024);
//
//		for (int i = 0; i < 20; i++) {
//			counter.add(i, 10);
//			ThreadUtil.sleep(500);
//		}
//
//		for (int i = 0; i < 20; i++) {
//			System.out.println(counter.getValue());
//			ThreadUtil.sleep(2000);
//		}

		MutableLongLongMap map = ECollections.newLongLongHashMap(1024);

		map.put(1, 10);

		System.out.println(-19 + -15);

	}

	@Override
	public LazyLoadingExpirationCounter removeHistoryDelta(long tag) {
		long delta = tagToDelta.get(tag);
		if (delta == 0)
			return this;
		tagToDelta.remove(tag);
		value -= delta;
		return this;
	}

	@Override
	public LazyLoadingExpirationCounter removeHistoryDelta(long tag, long delta) {
		long saveDelta = tagToDelta.get(tag);
		if (saveDelta == 0)
			return this;
		tagToDelta.put(tag, saveDelta - delta);
		value -= delta;
		return this;
	}

}
