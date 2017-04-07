package org.beam.transport.base.role;

public interface Subscriber extends TransportModule{

	void subscribe();

	void destroy();

	String getSubscriberName();

	boolean isConnected();
	
}
