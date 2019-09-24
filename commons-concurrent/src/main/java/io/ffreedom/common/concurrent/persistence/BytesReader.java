package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.QueueReader;
import net.openhft.chronicle.queue.ExcerptTailer;

public final class BytesReader extends QueueReader<byte[]> {

	private BytesReader(ExcerptTailer tailer) {
		super(tailer);
	}

	public static BytesReader wrap(ExcerptTailer tailer) {
		return new BytesReader(tailer);
	}

	@Override
	protected byte[] read0() {
		
		return null;
	}

}
