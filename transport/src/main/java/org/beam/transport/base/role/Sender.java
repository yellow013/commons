package org.beam.transport.base.role;

public interface Sender<T> extends TransportModule{

	void send(T msg);

	void destroy();
	
	String getRequesterName();

}
