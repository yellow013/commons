package io.ffreedom.common.concurrent.persistence.base;

import org.slf4j.Logger;

import net.openhft.chronicle.queue.ExcerptAppender;

public abstract class QueueWriter<T> {

	protected ExcerptAppender appender;

	protected Logger logger;

	protected QueueWriter(ExcerptAppender appender) {
		this.appender = appender;
	}

	public ExcerptAppender getAppender() {
		return appender;
	}

	public void write(T t) throws Exception {
		write0(t);
	};

	abstract protected void write0(T t);

}
