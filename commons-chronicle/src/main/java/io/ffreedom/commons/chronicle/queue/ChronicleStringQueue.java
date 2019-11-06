package io.ffreedom.commons.chronicle.queue;

import io.ffreedom.common.number.RandomNumber;
import io.ffreedom.commons.chronicle.queue.accessor.StringReader;
import io.ffreedom.commons.chronicle.queue.accessor.StringWriter;
import io.ffreedom.commons.chronicle.queue.base.ChronicleDataQueue;
import io.ffreedom.commons.chronicle.queue.base.FileCycle;

public class ChronicleStringQueue extends ChronicleDataQueue<String, StringReader, StringWriter> {

	private ChronicleStringQueue(Builder builder) {
		super(builder);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Override
	public StringReader createReader() {
		return StringReader.wrap(getQueue().createTailer(), getFileCycle());
	}

	@Override
	public StringWriter acquireWriter() {
		return StringWriter.wrap(getQueue().acquireAppender());
	}

	public static class Builder extends BaseBuilder<Builder> {

		public ChronicleStringQueue build() {
			return new ChronicleStringQueue(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}

	}

	public static void main(String[] args) {
		ChronicleStringQueue dataPersistence = ChronicleStringQueue.newBuilder().setFileCycle(FileCycle.HOURLY).build();
		StringWriter queueWriter = dataPersistence.acquireWriter();
		StringReader queueReader = dataPersistence.createReader();
		new Thread(() -> {
			for (;;) {
				try {
					queueWriter.append(String.valueOf(RandomNumber.randomLong()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		CharSequence read = "";
		long nanoTime0 = System.nanoTime();
		do {
			try {
				read = queueReader.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (read != null);
		long nanoTime1 = System.nanoTime();
		System.out.println((nanoTime1 - nanoTime0) / 1000);

	}

}
