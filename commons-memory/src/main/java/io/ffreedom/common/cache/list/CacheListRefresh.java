package io.ffreedom.common.cache.list;

import java.util.List;

@FunctionalInterface
public interface CacheListRefresh<T> {

	List<T> refresh();
	
}
