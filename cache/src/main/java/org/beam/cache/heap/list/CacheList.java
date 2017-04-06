package org.beam.cache.heap.list;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CacheList<T> {

	private volatile List<T> value;
	private AtomicBoolean available = new AtomicBoolean(false);
	
	private CacheListRefresh<T> cacheRefresh;
	
	public CacheList(CacheListRefresh<T> cacheRefresh) {
		if (cacheRefresh == null || !(cacheRefresh instanceof CacheListRefresh)) {
			throw new IllegalArgumentException("cacheRefresh illegalArgumentException");
		}
		this.cacheRefresh = cacheRefresh;
	}

	public CacheList<T> set(List<T> value) {
		this.value = value;
		this.available.set(true);
		return this;
	}
	
	public List<T> get() {
		if(isAvailable()){
			return value;
		}else{
			List<T> value = cacheRefresh.refresh();
			if(value == null){
				return null;
			}
			set(value);
			return value;
		}
	}

	private boolean isAvailable() {
		return available.get();
	}

	public CacheList<T> setUnavailable() {
		this.available.set(false);
		return this;
	}

}
