package io.ffreedom.common.queue.base;

import io.ffreedom.common.mark.NoReturn;

@FunctionalInterface
public interface QueueProcessor<T> {

	@NoReturn
	void process(T t);

}
