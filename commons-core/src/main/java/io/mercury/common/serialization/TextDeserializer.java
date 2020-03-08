package io.mercury.common.serialization;

@FunctionalInterface
public interface TextDeserializer<T> {

	T deserialization(CharSequence source);

}
