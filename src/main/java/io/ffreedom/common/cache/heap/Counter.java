package io.ffreedom.common.cache.heap;

public interface Counter<T extends Counter<?>> {

	T add(long tag, long delta);

	T subtract(long tag, long delta);

	T removeHistoryDelta(long tag);
	
	T removeHistoryDelta(long tag, long delta);

	default T increment(long tag) {
		return add(tag, 1);
	}

	default T decrement(long tag) {
		return subtract(tag, 1);
	}

	long getValue();

}
