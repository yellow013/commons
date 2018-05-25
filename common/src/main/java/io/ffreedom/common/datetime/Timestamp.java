package io.ffreedom.common.datetime;

import java.time.Instant;

public class Timestamp {

	private Instant instant;
	private long epochMicrosecond;

	/**
	 * @param instant
	 */
	private Timestamp(Instant instant) {
		this.instant = instant;
		this.epochMicrosecond = instant.getEpochSecond() * 1000000 + instant.getNano();
	}

	public static Timestamp now() {
		return new Timestamp(Instant.now());
	}

	public Instant getInstant() {
		return instant;
	}

	public long getEpochSecond() {
		return instant.getEpochSecond();
	}

	public int getNanoOfSecond() {
		return instant.getNano();
	}

	public long getEpochMicrosecond() {
		return epochMicrosecond;
	}

}
