package io.mercury.common.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface ValueTransferer<F, T> extends BiFunction<F, T, T> {

	T transfer(F from, T to);

	@Override
	default T apply(F from, T to) {
		return transfer(from, to);
	}

}
