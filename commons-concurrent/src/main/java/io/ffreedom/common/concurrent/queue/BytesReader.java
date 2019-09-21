package io.ffreedom.common.concurrent.queue;

import io.ffreedom.common.concurrent.queue.base.QueueReader;
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
		// TODO Auto-generated method stub
		return null;
	}

}
