package io.ffreedom.common.fsm;

public interface FiniteStateMachine<S extends State, A extends Action> {

	S state();

	S action(A action);

}
