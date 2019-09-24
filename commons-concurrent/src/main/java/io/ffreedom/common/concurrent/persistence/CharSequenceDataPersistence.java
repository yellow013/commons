package io.ffreedom.common.concurrent.queue;

import java.time.LocalDate;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.base.ChronicleDataPersistence;
import io.ffreedom.common.number.RandomNumber;

public class CharSequenceDataPersistence
		extends ChronicleDataPersistence<CharSequence, CharSequenceReader, CharSequenceWriter> {

	public CharSequenceDataPersistence() {
		super();
	}

	public CharSequenceDataPersistence(Logger externalLogger) {
		super(externalLogger);
	}

	public CharSequenceDataPersistence(String prefix, Logger externalLogger) {
		super(prefix, externalLogger);
	}

	public CharSequenceDataPersistence(String prefix, LocalDate date, Logger externalLogger) {
		super(prefix, date, externalLogger);
	}

	public CharSequenceDataPersistence(String rootPath, String prefix, Logger externalLogger) {
		super(rootPath, prefix, externalLogger);
	}

	public CharSequenceDataPersistence(String rootPath, String prefix, LocalDate date, Logger externalLogger) {
		super(rootPath, prefix, date, externalLogger);
	}

	@Override
	public CharSequenceReader newQueueReader() {
		return CharSequenceReader.wrap(queue.createTailer());
	}

	@Override
	public CharSequenceWriter newQueueWriter() {
		return CharSequenceWriter.wrap(queue.acquireAppender());
	}

	public static void main(String[] args) {
		CharSequenceDataPersistence dataPersistence = new CharSequenceDataPersistence();
		CharSequenceWriter queueWriter = dataPersistence.newQueueWriter();
		CharSequenceReader queueReader = dataPersistence.newQueueReader();
		new Thread(() -> {
			for (;;) {
				try {
					queueWriter.write(String.valueOf(RandomNumber.unsafeRandomLong()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		CharSequence read = "";
		long nanoTime0 = System.nanoTime();
		do {
			try {
				read = queueReader.read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (read != null);
		long nanoTime1 = System.nanoTime();
		System.out.println((nanoTime1 - nanoTime0) / 1000);

	}

}
