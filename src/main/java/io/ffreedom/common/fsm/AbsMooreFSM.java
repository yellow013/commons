package io.ffreedom.common.fsm;

public abstract class AbsMooreFSM<E> implements MooreFSM<E> {

    protected boolean isInitialState = false;

    @Override
    public void setInitialState() {
        isInitialState = true;
    }

    @Override
    public void reset() {
        isInitialState = false;
        clear();
    }

    protected abstract void clear();

}
