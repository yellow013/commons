package io.ffreedom.common.map;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.jctools.maps.NonBlockingHashMap;

@ThreadSafe
public final class CacheMap<K, V> {

	private ConcurrentMap<K, Saved> valueMap = new NonBlockingHashMap<>();

	private Function<K, V> refresher;

	private class Saved {

		private volatile boolean isAvailable;
		private volatile V value;

		public Saved(boolean isAvailable, V value) {
			super();
			this.isAvailable = isAvailable;
			this.value = value;
		}
	}

	public CacheMap(Function<K, V> refresher) {
		if (refresher == null)
			throw new IllegalArgumentException("refresher is can't null...");
		this.refresher = refresher;
	}

	public CacheMap<K, V> put(@Nonnull K key, @Nonnull V value) {
		valueMap.put(key, new Saved(true, value));
		return this;
	}

	public Optional<V> get(@Nonnull K key) {
		Saved saved = valueMap.get(key);
		if (saved == null || !saved.isAvailable) {
			V refreshed = refresher.apply(key);
			return refreshed == null ? Optional.empty() : put(key, refreshed).get(key);
		} else
			return saved.isAvailable ? Optional.of(saved.value) : get(key);
	}

	public CacheMap<K, V> setUnavailable(@Nonnull K key) {
		Saved saved = valueMap.get(key);
		if (saved != null)
			saved.isAvailable = false;
		return this;
	}

	public CacheMap<K, V> delete(@Nonnull K key) {
		valueMap.remove(key);
		return this;
	}

}
