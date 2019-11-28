package io.mercury.common.serialization;

public interface Serializer<T, F> {

	T serialization(F f);

}
