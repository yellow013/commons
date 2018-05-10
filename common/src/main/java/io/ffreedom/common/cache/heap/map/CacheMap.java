package io.ffreedom.common.cache.heap.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMap<K, V> {

	private Map<K, Boolean> availableMap = new ConcurrentHashMap<>();
	private Map<K, V> valueMap = new ConcurrentHashMap<>();

	private CacheMapRefresher<K, V> refresher;

	public CacheMap(CacheMapRefresher<K, V> refresher) {
		if (refresher == null) {
			throw new NullPointerException("CacheMapRefresher is null...");
		}
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
		if (availableMap.containsKey(key)) {
			return availableMap.get(key);
		} else {
			return false;
		}
	}

	public void setUnavailable(K key) {
		availableMap.put(key, false);
	}

}
