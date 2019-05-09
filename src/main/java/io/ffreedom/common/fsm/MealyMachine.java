package io.ffreedom.common.fsm;

public interface MealyMachine<A extends Action, S extends State> extends FiniteStateMachine<S> {

	S setState(A action);

}
