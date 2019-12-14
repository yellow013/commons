package io.mercury.common.codec;

import java.util.function.Function;

@FunctionalInterface
public interface TextDecoder<R> extends Function<CharSequence, R> {

	R decode(CharSequence t);

	@Override
	default R apply(CharSequence t) {
		return decode(t);
	}

}
