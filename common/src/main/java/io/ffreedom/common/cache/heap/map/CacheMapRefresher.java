package io.ffreedom.common.cache.heap.map;

@FunctionalInterface
public interface CacheMapRefresher<K, V> {

	V refresh(K key);

}
