package org.beam.transport.base;

public interface MessageBox<T> {

	boolean put(Message<T> in);

	Message<T> take();

}
