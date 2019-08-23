package io.ffreedom.common.map;

import java.time.temporal.Temporal;

import org.eclipse.collections.api.list.MutableList;

abstract class TemporalRangeMap<K extends Temporal, V, T extends TemporalRangeMap<K, V, T>> {

	protected LongRangeMap<V> savedMap = new LongRangeMap<>(128);

	abstract public T put(K key, V value);

	abstract public V get(K key);

	abstract public MutableList<V> get(K startPoint, K endPoint);

	protected void put(long key, V value) {
		savedMap.put(key, value);
	}

	protected V get(long key) {
		return savedMap.get(key);
	}

	protected MutableList<V> get(long startPoint, long endPoint) {
		return savedMap.get(startPoint, endPoint);
	}

}
