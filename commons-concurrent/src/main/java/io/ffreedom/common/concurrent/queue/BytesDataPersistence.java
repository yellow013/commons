package io.ffreedom.common.concurrent.queue;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.base.ChronicleDataPersistence;
import io.ffreedom.common.datetime.DateTimeUtil;

public class BytesDataPersistence extends ChronicleDataPersistence<byte[], BytesReader, BytesWriter> {

	public BytesDataPersistence() {
		super(null);
	}

	public BytesDataPersistence(Logger externalLogger) {
		super("data", String.valueOf(DateTimeUtil.date()), externalLogger);
	}

	public BytesDataPersistence(String prefix, String name) {
		super(prefix, name, null);
	}

	public BytesDataPersistence(String prefix, String name, Logger externalLogger) {
		super(prefix, name, externalLogger);
	}

	@Override
	public BytesReader newQueueReader() {
		return BytesReader.wrap(queue.createTailer());
	}

	@Override
	public BytesWriter newQueueWriter() {
		return BytesWriter.wrap(queue.acquireAppender());
	}

}
