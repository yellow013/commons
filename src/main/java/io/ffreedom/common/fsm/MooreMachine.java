package io.ffreedom.common.fsm;

public interface MooreMachine<S extends State> extends FiniteStateMachine<S> {

	void setState(S state);

}
