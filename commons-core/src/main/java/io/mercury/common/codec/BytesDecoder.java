package io.mercury.common.codec;

import java.nio.ByteBuffer;
import java.util.function.Function;

@FunctionalInterface
public interface BytesDecoder<R> extends Function<ByteBuffer, R> {

	R decode(ByteBuffer t);

	@Override
	default R apply(ByteBuffer t) {
		return decode(t);
	}

}
