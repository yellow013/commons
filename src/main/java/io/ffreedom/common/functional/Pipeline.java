package io.ffreedom.common.functional;

import java.util.function.Function;

@FunctionalInterface
public interface Pipeline<IN, OUT> extends Function<IN, OUT>{

	OUT stream(IN in);
	
	@Override
	default OUT apply(IN i) {
		return stream(i);
	}

}
