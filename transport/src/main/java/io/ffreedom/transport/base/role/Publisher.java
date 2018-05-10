package io.ffreedom.transport.base.role;

import io.ffreedom.transport.base.TransportModule;

public interface Publisher<T> extends TransportModule{

	void publish(T msg);

	void publish(String target, T msg);

}
