package io.ffreedom.common.functional;

import java.util.function.Function;

@FunctionalInterface
public interface MsgPipeline<IN, OUT> extends Function<IN, OUT> {

	default OUT stream(IN in) {
		return apply(in);
	}

}
