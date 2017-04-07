package org.beam.transport.base.role;

public interface Publisher<T> extends TransportModule{

	void publish(T msg);

	void publish(String target, T msg);

	void destroy();

	String getPublisherName();
	
}
