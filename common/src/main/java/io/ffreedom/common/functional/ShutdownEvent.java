package io.ffreedom.common.functional;

@FunctionalInterface
public interface ShutdownEvent<E extends Throwable> {

	void shutdownHandle(E exception);

}
