package io.ffreedom.common.fsm;

import java.util.Collection;

public interface MooreFSM<E> {

	void registerElement(E e);

	default void registerElement(Collection<E> collection) {
		for (E e : collection) {
			registerElement(e);
		}
	}

	void setInitialState();

	void reset();

}
