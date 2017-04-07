package org.beam.transport.rabbit;

public interface ShutdownEvent {

	void shutdownHandle(Exception exception);

}
