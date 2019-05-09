package io.ffreedom.common.fsm;

public interface MooreMachine<S extends State> extends FiniteStateMachine<S> {

	void onAction(S state);

}
