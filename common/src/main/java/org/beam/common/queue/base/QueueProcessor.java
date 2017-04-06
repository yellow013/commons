package org.beam.common.queue.base;

import org.beam.common.annotation.mark.NoReturn;

public interface QueueProcessor<T> {

	@NoReturn
	void process(T t);

}
