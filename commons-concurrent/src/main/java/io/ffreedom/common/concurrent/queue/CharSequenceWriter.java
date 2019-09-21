package io.ffreedom.common.concurrent.queue;

import io.ffreedom.common.concurrent.queue.base.QueueWriter;
import net.openhft.chronicle.queue.ExcerptAppender;

public final class CharSequenceWriter extends QueueWriter<CharSequence> {

	private CharSequenceWriter(ExcerptAppender appender) {
		super(appender);
	}

	public static CharSequenceWriter wrap(ExcerptAppender appender) {
		return new CharSequenceWriter(appender);
	}

	@Override
	protected void write0(CharSequence t) {
		appender.writeText(t);
	}

}
