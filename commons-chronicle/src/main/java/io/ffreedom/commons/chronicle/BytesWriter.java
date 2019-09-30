package io.ffreedom.commons.chronicle;

import io.ffreedom.commons.chronicle.base.DataWriter;
import net.openhft.chronicle.queue.ExcerptAppender;

public final class BytesWriter extends DataWriter<byte[]> {

	private BytesWriter(ExcerptAppender appender) {
		super(appender);
	}

	public static BytesWriter wrap(ExcerptAppender appender) {
		return new BytesWriter(appender);
	}

	@Override
	protected void append0(byte[] t) {
		
		appender.writeBytes(wire -> {
		});
	}

}
