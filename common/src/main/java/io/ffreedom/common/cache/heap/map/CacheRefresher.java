package io.ffreedom.common.cache.heap.map;

@FunctionalInterface
public interface CacheRefresher<K, V> {

	V refresh(K key);

}
