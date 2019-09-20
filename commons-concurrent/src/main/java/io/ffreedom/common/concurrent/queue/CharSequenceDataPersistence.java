package io.ffreedom.common.concurrent.queue;

import org.slf4j.Logger;

import io.ffreedom.common.concurrent.queue.base.ChronicleDataPersistence;
import io.ffreedom.common.datetime.DateTimeUtil;

public class CharSequenceDataPersistence extends ChronicleDataPersistence<CharSequence> {

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
	protected void append0(CharSequence event) {
		try {
			appender.writeText(event);
		} catch (Exception e) {
			logger.error("ChronicleDataPersistence name==[{}] throw RuntimeException", getName(), e);
		}
	}

}
