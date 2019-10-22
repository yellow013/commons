package io.ffreedom.commons.chronicle.queue.accessor;

import javax.annotation.concurrent.NotThreadSafe;

import io.ffreedom.commons.chronicle.queue.base.DataWriter;
import net.openhft.chronicle.queue.ExcerptAppender;

@NotThreadSafe
public final class StringWriter extends DataWriter<String> {

	private StringWriter(ExcerptAppender appender) {
		super(appender);
	}

	public static StringWriter wrap(ExcerptAppender appender) {
		return new StringWriter(appender);
	}

	@Override
	protected void append0(String t) {
		appender.writeText(t);
	}

}
