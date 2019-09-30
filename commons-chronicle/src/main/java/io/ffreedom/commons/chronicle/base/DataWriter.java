package io.ffreedom.commons.chronicle.base;

import io.ffreedom.common.annotations.lang.MayThrowRuntimeException;
import net.openhft.chronicle.queue.ExcerptAppender;

public abstract class DataWriter<T> {

	protected ExcerptAppender appender;

	protected DataWriter(ExcerptAppender appender) {
		this.appender = appender;
	}

	public ExcerptAppender getAppender() {
		return appender;
	}

	public int currentCycle() {
		return appender.cycle();
	}

	@MayThrowRuntimeException
	public void append(T t) throws ChronicleWriteException {
		try {
			append0(t);
		} catch (Exception e) {
			throw new ChronicleWriteException(e.getMessage(), e);
		}
	}

	abstract protected void append0(T t);

}
