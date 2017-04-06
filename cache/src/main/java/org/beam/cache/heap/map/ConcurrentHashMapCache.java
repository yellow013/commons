package org.beam.cache.heap.map;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapCache<K, V> {

	private Map<K, V> map = new ConcurrentHashMap<>();

	public void put(K key, V value) {
		map.put(key, value);
	}

	public V get(K key) {
		return map.get(key);
	}

	public Collection<V> getAll() {
		return map.values();
	}

}
