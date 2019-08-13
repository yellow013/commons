package io.ffreedom.common.cache.list;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LazyLoadingCacheList<T> {

	private volatile List<T> value;
	private AtomicBoolean available = new AtomicBoolean(false);

	private CacheListRefresh<T> cacheRefresh;

	public LazyLoadingCacheList(CacheListRefresh<T> cacheRefresh) {
		if (cacheRefresh == null)
			throw new IllegalArgumentException("cacheRefresh illegalArgumentException.");
		this.cacheRefresh = cacheRefresh;
	}

	public LazyLoadingCacheList<T> set(List<T> value) {
		this.value = value;
		this.available.set(true);
		return this;
	}

	public List<T> get() {
		if (isAvailable()) {
			return value;
		} else {
			List<T> value = cacheRefresh.refresh();
			if (value == null) 
				return null;
			set(value);
			return get();
		}
	}

	private boolean isAvailable() {
		return available.get();
	}

	public LazyLoadingCacheList<T> setUnavailable() {
		this.available.set(false);
		return this;
	}

}
