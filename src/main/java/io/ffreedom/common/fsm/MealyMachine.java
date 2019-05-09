package io.ffreedom.common.fsm;

public interface MealyMachine<S extends State> extends FiniteStateMachine<S> {

	S setState(S state);

}
