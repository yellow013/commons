package io.ffreedom.common.cache.map;

@FunctionalInterface
public interface CacheRefresher<K, V> {

	V refresh(K key);

}
