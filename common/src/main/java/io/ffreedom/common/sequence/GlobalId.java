package io.ffreedom.common.sequence;

import java.util.concurrent.atomic.AtomicLong;

public final class GlobalId {

	private static AtomicLong globalId = new AtomicLong(99999999L);;

	private GlobalId(long initialValue) {
	}

	public static long generate() {
		return globalId.incrementAndGet();
	}

}
