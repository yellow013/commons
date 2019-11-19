package io.ffreedom.common.collections.customize;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.map.ConcurrentMutableMap;

import io.ffreedom.common.collections.InitialCapacity;
import io.ffreedom.common.collections.MutableMaps;

@ThreadSafe
public abstract class Keeper<K, V> {

	protected ConcurrentMutableMap<K, V> savedMap = MutableMaps.newConcurrentHashMap(InitialCapacity.L06_Size_64);

	@Nonnull
	public V acquire(@Nonnull K k) {
		return savedMap.getIfAbsentPutWithKey(k, this::createWith);
	}

	protected abstract V createWith(K k);

}
