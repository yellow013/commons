package io.ffreedom.common.concurrent.persistence;

import io.ffreedom.common.concurrent.persistence.base.ChronicleDataPersistence;

public class BytesDataPersistence extends ChronicleDataPersistence<byte[], BytesReader, BytesWriter> {

	private BytesDataPersistence(Builder builder) {
		super(builder);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Override
	public BytesReader createReader() {
		return BytesReader.wrap(getQueue().createTailer(), getFileCycle());
	}

	@Override
	public BytesWriter createWriter() {
		return BytesWriter.wrap(getQueue().acquireAppender());
	}

	public static class Builder extends BaseBuilder<Builder> {

		public BytesDataPersistence build() {
			return new BytesDataPersistence(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}

	}

}
