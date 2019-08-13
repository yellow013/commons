package io.ffreedom.common.fsm;

public interface FiniteStateMachine<S extends State> {

	S state();

}
