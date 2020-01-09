package io.mercury.common.codec;

import java.nio.ByteBuffer;
import java.util.function.Function;

@FunctionalInterface
public interface BytesEncoder<T> extends Function<T, ByteBuffer> {

	ByteBuffer encode(T t);

	@Override
	default ByteBuffer apply(T t) {
		return encode(t);
	}

}
