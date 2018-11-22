package io.ffreedom.common.functional;

import java.util.function.Function;

@FunctionalInterface
public interface Factory<IN, OUT> extends Function<IN, OUT> {

	OUT produce(IN in);

	@Override
	default OUT apply(IN in) {
		return produce(in);
	}

}
