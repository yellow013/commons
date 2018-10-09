package io.ffreedom.common.functional;

import java.util.function.Supplier;

@FunctionalInterface
public interface Initializer<T> extends Supplier<T>{

	T initialize();
	
	@Override
	default T get() {
		return initialize();
	}

}
