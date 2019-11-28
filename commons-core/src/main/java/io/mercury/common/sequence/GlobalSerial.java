package io.mercury.common.sequence;

import java.util.concurrent.atomic.AtomicLong;

public final class GlobalSerial {

	private static AtomicLong InnerId = new AtomicLong(0L);;

	private GlobalSerial() {
	}

	public static long incrementAndGet() {
		return InnerId.incrementAndGet();
	}

}
