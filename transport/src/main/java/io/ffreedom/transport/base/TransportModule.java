package io.ffreedom.transport.base;

public interface TransportModule {

	String getName();

	boolean isConnected();

	void destroy();

}
