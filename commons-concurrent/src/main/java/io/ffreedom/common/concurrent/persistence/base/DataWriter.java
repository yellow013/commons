package io.ffreedom.common.concurrent.persistence.base;

import org.slf4j.Logger;

import net.openhft.chronicle.queue.ExcerptAppender;

public abstract class DataWriter<T> {

	protected ExcerptAppender appender;

	protected Logger logger;

	protected DataWriter(ExcerptAppender appender) {
		this.appender = appender;
	}

	public ExcerptAppender getAppender() {
		return appender;
	}

	public int currentCycle() {
		return appender.cycle();
	}

	public void append(T t) throws Exception {
		append0(t);
	};

	abstract protected void append0(T t);

}
