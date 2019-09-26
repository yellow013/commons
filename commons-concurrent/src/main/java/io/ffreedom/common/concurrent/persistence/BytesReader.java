package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.DataReader;
import io.ffreedom.common.concurrent.persistence.base.FileCycle;
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
