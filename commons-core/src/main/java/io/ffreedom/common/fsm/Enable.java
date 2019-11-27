package io.ffreedom.common.fsm;

public interface Enable {

	boolean isEnabled();

	default boolean isDisabled() {
		return !isEnabled();
	}

	void disable();

	void enable();

}