package org.beam.cache.heap.map;

public interface CacheMapRefresher<K, V> {

	V refresh(K key);

}
