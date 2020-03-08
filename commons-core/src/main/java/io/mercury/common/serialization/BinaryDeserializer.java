package io.mercury.common.serialization;

import java.nio.ByteBuffer;

@FunctionalInterface
public interface BinaryDeserializer<T> {

	T deserialization(ByteBuffer source);

}
