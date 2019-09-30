package io.ffreedom.commons.chronicle;

import io.ffreedom.commons.chronicle.base.DataWriter;
import net.openhft.chronicle.queue.ExcerptAppender;

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
