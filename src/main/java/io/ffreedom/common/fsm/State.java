package io.ffreedom.common.fsm;

@FunctionalInterface
public interface State {

	int stateCode();

	State Uninitialized = () -> 0;

	State Initialized = () -> 1;

	State Normal = () -> 2;

	State Failure = () -> -1;

}