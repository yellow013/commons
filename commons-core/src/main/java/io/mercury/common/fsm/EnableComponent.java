package io.mercury.common.fsm;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class EnableComponent implements Enable {

	private boolean isEnable;

	@Override
	public boolean isEnabled() {
		return isEnable;
	}

	@Override
	public boolean isDisabled() {
		return !isEnable;
	}

	@Override
	public void disable() {
		isEnable = false;
	}

	@Override
	public void enable() {
		isEnable = true;
	}

}
