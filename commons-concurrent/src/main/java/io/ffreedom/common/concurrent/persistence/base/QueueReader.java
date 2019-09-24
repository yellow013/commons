package io.ffreedom.common.concurrent.queue.base;

import net.openhft.chronicle.queue.ExcerptTailer;

public abstract class QueueReader<T> {

	protected ExcerptTailer tailer;

	protected QueueReader(ExcerptTailer tailer) {
		super();
		this.tailer = tailer;
	}

//	public static <T> QueueReader<T> wrap(ExcerptTailer tailer) {
//		return new QueueReader<>(tailer);
//	}

	public ExcerptTailer getTailer() {
		return tailer;
	}

	public T read() throws Exception {
		return read0();
	};

	abstract protected T read0();

}
