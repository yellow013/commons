package org.beam.transport.base;

public interface Message<T> {

	T get();

	void set(T t);

}
