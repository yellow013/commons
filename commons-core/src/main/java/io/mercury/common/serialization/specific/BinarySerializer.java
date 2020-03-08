package io.mercury.common.serialization;

import java.nio.ByteBuffer;

@FunctionalInterface
public interface BinarySerializer<T> {

	ByteBuffer serialization(T obj);

}
