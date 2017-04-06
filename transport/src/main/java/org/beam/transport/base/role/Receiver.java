package org.beam.transport.base.role;

public interface Receiver extends TransportModule{
	
	void receive();

	void destroy();

	String getReceiverName();

}
