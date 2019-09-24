package io.ffreedom.common.concurrent.persistence;

import java.time.LocalDate;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.persistence.base.ChronicleDataPersistence;

public class BytesDataPersistence extends ChronicleDataPersistence<byte[], BytesReader, BytesWriter> {

	public BytesDataPersistence() {
		super();
	}

	public BytesDataPersistence(Logger externalLogger) {
		super(externalLogger);
	}

	public BytesDataPersistence(String prefix, Logger externalLogger) {
		super(prefix, externalLogger);
	}

	public BytesDataPersistence(String prefix, LocalDate date, Logger externalLogger) {
		super(prefix, date, externalLogger);
	}

	public BytesDataPersistence(String rootPath, String prefix, Logger externalLogger) {
		super(rootPath, prefix, externalLogger);
	}

	public BytesDataPersistence(String rootPath, String prefix, LocalDate date, Logger externalLogger) {
		super(rootPath, prefix, date, externalLogger);
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
