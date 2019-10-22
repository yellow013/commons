package io.ffreedom.commons.chronicle.queue.accessor;

import io.ffreedom.commons.chronicle.queue.base.DataReader;
import io.ffreedom.commons.chronicle.queue.base.FileCycle;
import net.openhft.chronicle.queue.ExcerptTailer;

public final class BytesReader extends DataReader<byte[]> {

	private BytesReader(ExcerptTailer tailer, FileCycle fileCycle) {
		super(tailer, fileCycle);
	}

	public static BytesReader wrap(ExcerptTailer tailer, FileCycle fileCycle) {
		return new BytesReader(tailer, fileCycle);
	}

	@Override
	protected byte[] next0() {
		return null;
	}

}
