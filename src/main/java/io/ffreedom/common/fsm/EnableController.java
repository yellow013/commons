package io.ffreedom.common.fsm;

public final class EnableController implements Enable {

	private boolean isEnable;

	public final static EnableController newInstance() {
		return new EnableController();
	}

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
