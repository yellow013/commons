package io.mercury.common.collections.customize;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.map.ConcurrentMutableMap;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;

@ThreadSafe
public abstract class BaseKeeper<K, V> implements Keeper<K, V> {

	protected ConcurrentMutableMap<K, V> savedMap = MutableMaps.newConcurrentHashMap(Capacity.L08_SIZE_256);

	@Nonnull
	public V acquire(@Nonnull K k) {
		return savedMap.getIfAbsentPutWithKey(k, this::createWithKey);
	}

	@CheckForNull
	public V get(@Nonnull K k) {
		return savedMap.get(k);
	}

	@ProtectedAbstractMethod
	protected abstract V createWithKey(K k);

}
