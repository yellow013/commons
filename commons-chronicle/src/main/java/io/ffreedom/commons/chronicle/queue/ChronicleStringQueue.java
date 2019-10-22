package io.ffreedom.commons.chronicle.queue;

import io.ffreedom.common.number.RandomNumber;
import io.ffreedom.commons.chronicle.queue.base.ChronicleDataPersistence;
import io.ffreedom.commons.chronicle.queue.base.FileCycle;

public class StringDataPersistence extends ChronicleDataPersistence<String, StringReader, StringWriter> {

	private StringDataPersistence(Builder builder) {
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
	public StringWriter createWriter() {
		return StringWriter.wrap(getQueue().acquireAppender());
	}

	public static class Builder extends BaseBuilder<Builder> {

		public StringDataPersistence build() {
			return new StringDataPersistence(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}

	}

	public static void main(String[] args) {
		StringDataPersistence dataPersistence = StringDataPersistence.newBuilder().setFileCycle(FileCycle.HOURLY).build();
		StringWriter queueWriter = dataPersistence.createWriter();
		StringReader queueReader = dataPersistence.createReader();
		new Thread(() -> {
			for (;;) {
				try {
					queueWriter.append0(String.valueOf(RandomNumber.unsafeRandomLong()));
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
