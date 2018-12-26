package io.ffreedom.common.cache.heap;

import java.time.Duration;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.common.utils.ThreadUtil;

@NotThreadSafe
public class LazyLoadingExpirationCounter implements Counter<LazyLoadingExpirationCounter> {

	private long value = 0L;

	// TODO 增加强一致性 使用自旋锁
	// private AtomicBoolean spin;

	private MutableLongLongMap timeToTag;
	private MutableLongLongMap tagToDelta;
	private MutableLongList pendingDeleteTime;

	private long expireNanos;

	public LazyLoadingExpirationCounter(Duration expireTime, int capacity) {
		this.expireNanos = expireTime.toNanos();
		this.timeToTag = EclipseCollections.newLongLongHashMap(capacity);
		this.tagToDelta = EclipseCollections.newLongLongHashMap(capacity);
		this.pendingDeleteTime = EclipseCollections.newLongArrayList(capacity / 2);
	}

	private void add(long delta) {
		value += delta;
	}

	@Override
	public LazyLoadingExpirationCounter add(long tag, long delta) {
		if (!tagToDelta.containsKey(tag)) {
			long time = System.nanoTime();
			tagToDelta.put(tag, delta);
			timeToTag.put(time, tag);
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
		timeToTag.forEachKey(time -> checkTime(time, baseTime));
		clear();
		return value;
	}

	private void checkTime(long time, long baseTime) {
		if (time < baseTime)
			pendingDeleteTime.add(time);
	}

	private void clear() {
		for (int i = 0; i < pendingDeleteTime.size(); i++) {
			long time = pendingDeleteTime.get(i);
			long tag = timeToTag.get(time);
			long delta = tagToDelta.get(tag);
			add(-delta);
			timeToTag.remove(time);
			tagToDelta.remove(tag);
		}
		pendingDeleteTime.clear();
	}

	public static void main(String[] args) {
		LazyLoadingExpirationCounter counter = new LazyLoadingExpirationCounter(Duration.ofMillis(10000), 1024);

		for (int i = 0; i < 20; i++) {
			counter.add(i, 10);
			ThreadUtil.sleep(500);
		}

		for (int i = 0; i < 20; i++) {
			System.out.println(counter.getValue());
			ThreadUtil.sleep(2000);
		}

	}

	@Override
	public LazyLoadingExpirationCounter removeHistoryDelta(long tag) {
		long delta = tagToDelta.get(tag);
		tagToDelta.remove(tag);
		value -= delta;
		return this;
	}

}
