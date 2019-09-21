package io.ffreedom.common.concurrent.queue;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.base.ChronicleDataPersistence;
import io.ffreedom.common.datetime.DateTimeUtil;
import io.ffreedom.common.number.RandomNumber;

public class CharSequenceDataPersistence
		extends ChronicleDataPersistence<CharSequence, CharSequenceReader, CharSequenceWriter> {

	public CharSequenceDataPersistence() {
		super(null);
	}

	public CharSequenceDataPersistence(Logger externalLogger) {
		super("data", String.valueOf(DateTimeUtil.date()), externalLogger);
	}

	public CharSequenceDataPersistence(String prefix, String name) {
		super(prefix, name, null);
	}

	public CharSequenceDataPersistence(String prefix, String name, Logger externalLogger) {
		super(prefix, name, externalLogger);
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
