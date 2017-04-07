package org.beam.common.serialization;

public interface Serialization<R, T extends SerializableBean> {

	R serialization(T t);

	T deSerialization(R r, Class<T> tclass);

}
