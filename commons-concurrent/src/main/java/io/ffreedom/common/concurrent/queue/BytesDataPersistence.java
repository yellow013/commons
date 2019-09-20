package io.ffreedom.common.concurrent.queue;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.base.ChronicleDataPersistence;
import io.ffreedom.common.datetime.DateTimeUtil;

public class BytesDataPersistence extends ChronicleDataPersistence<byte[]> {

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
	protected void append0(byte[] event) {
		// TODO Auto-generated method stub

	}

}
