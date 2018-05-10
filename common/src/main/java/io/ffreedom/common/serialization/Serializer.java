package io.ffreedom.common.serialization;

public interface Serializer<T, F> {

	T serialization(F f);

}
