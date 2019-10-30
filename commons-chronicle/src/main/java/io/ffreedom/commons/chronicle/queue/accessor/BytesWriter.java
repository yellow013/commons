package io.ffreedom.commons.chronicle.queue.accessor;

import java.nio.ByteBuffer;

import javax.annotation.concurrent.NotThreadSafe;

import io.ffreedom.commons.chronicle.queue.base.DataWriter;
import net.openhft.chronicle.bytes.BytesStore;
import net.openhft.chronicle.queue.ExcerptAppender;

@NotThreadSafe
public final class BytesWriter extends DataWriter<ByteBuffer> {

	private BytesWriter(ExcerptAppender appender) {
		super(appender);
	}

	public static BytesWriter wrap(ExcerptAppender appender) {
		return new BytesWriter(appender);
	}

	@Override
	protected void append0(ByteBuffer t) {
		// use heap memory
		appender.writeBytes(BytesStore.wrap(t));
	}

}
