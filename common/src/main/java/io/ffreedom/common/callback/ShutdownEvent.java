package io.ffreedom.common.callback;

@FunctionalInterface
public interface ShutdownEvent {

	void shutdownHandle(Exception exception);

}
