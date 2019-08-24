package io.ffreedom.common.map;

import java.time.temporal.Temporal;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;

abstract class TemporalRangeMap<K extends Temporal, V, T extends TemporalRangeMap<K, V, T>> {

	protected ToLongFunction<K> conversionFunc;

	private LongRangeMap<V> savedMap = new LongRangeMap<>(128);

	public TemporalRangeMap(ToLongFunction<K> conversionFunc) {
		this.conversionFunc = conversionFunc;
	}

	/**
	 * abstract method
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	abstract public T put(@Nonnull K key, V value);

	/**
	 * general get method
	 * 
	 * @param key
	 * @return
	 */
	public V get(@Nonnull K key) {
		return savedMap.get(conversionFunc.applyAsLong(key));
	}

	/**
	 * general get method
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @return
	 */
	public MutableList<V> get(@Nonnull K startPoint, @Nonnull K endPoint) {
		return savedMap.get(conversionFunc.applyAsLong(startPoint), conversionFunc.applyAsLong(endPoint));
	}

	protected LongRangeMap<V> getSavedMap() {
		return savedMap;
	}

	protected void put(long key, V value) {
		savedMap.put(key, value);
	}

}
