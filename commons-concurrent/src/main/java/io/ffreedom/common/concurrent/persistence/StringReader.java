package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.QueueReader;
import net.openhft.chronicle.queue.ExcerptTailer;

public final class CharSequenceReader extends QueueReader<CharSequence> {

	private CharSequenceReader(ExcerptTailer tailer) {
		super(tailer);
	}

	public static CharSequenceReader wrap(ExcerptTailer tailer) {
		return new CharSequenceReader(tailer);
	}

	@Override
	protected CharSequence read0() {
		return tailer.readText();
	}

}
