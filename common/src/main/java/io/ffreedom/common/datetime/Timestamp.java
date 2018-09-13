package io.ffreedom.common.datetime;

import java.time.Instant;

public class Timestamp {

	private Instant instant;
	private long epochMicrosecond;

	public Timestamp(Instant instant) {
		this(instant, true);
	}

	public Timestamp(Instant instant, boolean lazyEvaluation) {
		this.instant = instant;
		if (!lazyEvaluation) {
			getEpochMicrosecond();
		}
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
		if (epochMicrosecond == 0L) {
			epochMicrosecond = instant.getEpochSecond() * 1000000 + instant.getNano() / 1000;
		}
		return epochMicrosecond;
	}

	public static void main(String[] args) {

		Timestamp now = Timestamp.now();
		System.out.println(now.getEpochSecond());
		System.out.println(now.getNanoOfSecond());
		System.out.println(now.getEpochMicrosecond());

	}

}
