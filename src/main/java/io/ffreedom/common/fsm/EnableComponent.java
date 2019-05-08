package io.ffreedom.common.fsm;

public abstract class EnableController implements Enable {

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
		this.isEnable = false;
	}

	@Override
	public void enable() {
		this.isEnable = true;
	}

}
