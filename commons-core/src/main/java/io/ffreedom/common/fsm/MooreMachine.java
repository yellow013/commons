package io.ffreedom.common.fsm;

public interface MooreMachine<A extends Action, S extends State> extends FiniteStateMachine<S> {

	void setState(A action);

}
