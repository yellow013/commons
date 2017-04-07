package org.beam.common.callback;

import org.beam.common.annotation.mark.NoReturn;

public interface Callback<T> {

	@NoReturn
	void onEvent(T t);

}
