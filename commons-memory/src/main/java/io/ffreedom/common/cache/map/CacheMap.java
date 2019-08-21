package io.ffreedom.common.cache.map;

import java.util.concurrent.ConcurrentMap;

import javax.annotation.concurrent.ThreadSafe;

import org.jctools.maps.NonBlockingHashMap;

@ThreadSafe
public final class CacheMap<K, V> {

	private ConcurrentMap<K, Boolean> availableMap = new NonBlockingHashMap<>();
	private ConcurrentMap<K, V> valueMap = new NonBlockingHashMap<>();

	private CacheRefresher<K, V> refresher;

	public CacheMap(CacheRefresher<K, V> refresher) {
		if (refresher == null)
			throw new IllegalArgumentException("CacheMapRefresher is can't null...");
		this.refresher = refresher;
	}

	public CacheMap<K, V> put(K key, V value) {
		valueMap.put(key, value);
		availableMap.put(key, true);
		return this;
	}

	public V get(K key) {
		if (isAvailable(key)) {
			return valueMap.get(key);
		} else {
			V value = refresher.refresh(key);
			if (value == null) {
				return null;
			}
			return put(key, value).get(key);
		}
	}

	private boolean isAvailable(K key) {
		Boolean available = availableMap.get(key);
		return available == null ? false : available.booleanValue();
	}

	public CacheMap<K, V> setUnavailable(K key) {
		availableMap.put(key, false);
		return this;
	}

}
