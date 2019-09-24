package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.QueueWriter;
import net.openhft.chronicle.queue.ExcerptAppender;

public final class BytesWriter extends QueueWriter<byte[]> {

	private BytesWriter(ExcerptAppender appender) {
		super(appender);
	}

	public static BytesWriter wrap(ExcerptAppender appender) {
		return new BytesWriter(appender);
	}

	@Override
	protected void write0(byte[] t) {
		
		appender.writeBytes(wire -> {
		});
	}

}
