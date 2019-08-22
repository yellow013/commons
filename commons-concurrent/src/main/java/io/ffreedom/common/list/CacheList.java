package io.ffreedom.common.cache.list;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CacheList<T> {

	private volatile List<T> value;
	private AtomicBoolean available = new AtomicBoolean(false);

	private CacheListRefresh<T> cacheRefresh;

	public CacheList(CacheListRefresh<T> cacheRefresh) {
		if (cacheRefresh == null)
			throw new IllegalArgumentException("cacheRefresh illegalArgumentException.");
		this.cacheRefresh = cacheRefresh;
	}

	private CacheList<T> set(List<T> value) {
		this.value = value;
		this.available.set(true);
		return this;
	}

	public Optional<List<T>> get() {
		if (available.get())
			return Optional.ofNullable(value);
		else {
			List<T> value = cacheRefresh.refresh();
			return value == null ? Optional.empty() : set(value).get();
		}
	}

	public CacheList<T> setUnavailable() {
		this.available.set(false);
		return this;
	}

}
