package io.ffreedom.common.cache.heap.list;

import java.util.List;

@FunctionalInterface
public interface CacheListRefresh<T> {

	List<T> refresh();
	
	//OrderRef DEV

}
