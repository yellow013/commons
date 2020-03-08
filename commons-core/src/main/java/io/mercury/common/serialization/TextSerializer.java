package io.mercury.common.serialization;

@FunctionalInterface
public interface TextSerializer<T> {

	CharSequence serialization(T obj);

}
