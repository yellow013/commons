package io.ffreedom.common.cache.heap;

public interface Counter<T extends Counter<?>> {

	T add(long delta);

	T subtract(long delta);

	default T increment() {
		return add(1);
	}

	default T decrement() {
		return subtract(1);
	}

	long value();

}
