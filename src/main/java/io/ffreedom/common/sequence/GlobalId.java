package io.ffreedom.common.sequence;

import java.util.concurrent.atomic.AtomicLong;

public final class GlobalId {

	private static AtomicLong ID = new AtomicLong(0L);;

	private GlobalId() {
	}

	public static long incrementAndGet() {
		return ID.incrementAndGet();
	}

}
