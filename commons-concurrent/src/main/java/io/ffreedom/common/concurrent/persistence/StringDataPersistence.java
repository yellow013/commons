package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.ChronicleDataPersistence;
import io.ffreedom.common.concurrent.persistence.base.FileCycle;
import io.ffreedom.common.number.RandomNumber;

public class StringDataPersistence extends ChronicleDataPersistence<String, StringReader, StringWriter> {

	private StringDataPersistence(Builder builder) {
		super(builder);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Override
	public StringReader createReader() {
		return StringReader.wrap(queue.createTailer(), fileCycle);
	}

	@Override
	public StringWriter createWriter() {
		return StringWriter.wrap(queue.acquireAppender());
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
		StringDataPersistence dataPersistence = StringDataPersistence.newBuilder().setFileCycle(FileCycle.HOUR).build();
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
