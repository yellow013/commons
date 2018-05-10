package io.ffreedom.common.callback;

import io.ffreedom.common.annotation.mark.NoReturn;

@FunctionalInterface
public interface Callback<T> {

	@NoReturn
	void onEvent(T t);

}
