package io.ffreedom.commons.chronicle.queue.accessor;

import java.nio.ByteBuffer;

import javax.annotation.concurrent.NotThreadSafe;

import io.ffreedom.commons.chronicle.queue.base.DataReader;
import io.ffreedom.commons.chronicle.queue.base.FileCycle;
import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.queue.ExcerptTailer;

@NotThreadSafe
public final class BytesReader extends DataReader<ByteBuffer> {

	private BytesReader(ExcerptTailer tailer, FileCycle fileCycle) {
		super(tailer, fileCycle);
	}

	public static BytesReader wrap(ExcerptTailer tailer, FileCycle fileCycle) {
		return new BytesReader(tailer, fileCycle);
	}

	@Override
	protected ByteBuffer next0() {
		// use heap memory
		Bytes<ByteBuffer> bytes = Bytes.elasticByteBuffer();
		tailer.readBytes(bytes);
		return ByteBuffer.wrap(bytes.toByteArray());
	}

}
