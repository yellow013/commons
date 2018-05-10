package io.ffreedom.common.collect.base;

public class LoadContainer<T> {

	private T t;

	public void loading(T t) {
		this.t = t;
	}

	public T unloading() {
		return t;
	}

}