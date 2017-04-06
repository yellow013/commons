package org.beam.transport.module;

import static com.google.inject.Guice.createInjector;

import org.beam.common.annotation.thread.Locking;

import com.google.inject.Injector;

@Deprecated
public class TransportModuleFactory {

	private static Injector injector = createInjector(new TransportModule());

	private TransportModuleFactory() {
	}

	@Locking
	public synchronized static <T> T getModuleInstance(Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
