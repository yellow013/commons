package io.mercury.common.fsm;

public interface Enable {

	boolean isEnabled();

	default boolean isDisabled() {
		return !isEnabled();
	}

	void disable();

	void enable();

}