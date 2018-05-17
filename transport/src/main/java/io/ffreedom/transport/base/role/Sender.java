package io.ffreedom.transport.base.role;

import io.ffreedom.transport.base.TransportModule;

public interface Sender<T> extends TransportModule{

	void sent(T msg);

}
