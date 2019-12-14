package io.mercury.common.codec;

import java.util.function.Function;

@FunctionalInterface
public interface TextEncoder<T> extends Function<T, CharSequence> {

	CharSequence encode(T t);

	@Override
	default CharSequence apply(T t) {
		return encode(t);
	}

}
