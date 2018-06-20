package io.ffreedom.common.functional;

import java.util.function.Consumer;

@FunctionalInterface
public interface Callback<T> extends Consumer<T> {

	default void onEvent(T t) {
		accept(t);
	}

}
