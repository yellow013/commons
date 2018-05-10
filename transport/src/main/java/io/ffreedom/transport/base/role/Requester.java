package io.ffreedom.transport.base.role;

import io.ffreedom.transport.base.TransportModule;

public interface Requester<T> extends TransportModule {

	T request();

}
