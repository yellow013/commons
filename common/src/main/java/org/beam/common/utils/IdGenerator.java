package org.beam.common.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private static AtomicLong globalId = new AtomicLong(100000L);

	public static long getGlobalId() {
		return globalId.incrementAndGet();
	}

	private AtomicLong privateId;

	public IdGenerator(AtomicLong privateId) {
		this.privateId = privateId;
	}

	public long getPrivateId() {
		return privateId.incrementAndGet();
	}

	public static void main(String[] args) {

	}

}
