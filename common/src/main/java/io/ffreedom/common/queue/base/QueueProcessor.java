package io.ffreedom.common.queue.base;

import io.ffreedom.common.annotation.mark.NoReturn;

public interface QueueProcessor<T> {

	@NoReturn
	void process(T t);

}
