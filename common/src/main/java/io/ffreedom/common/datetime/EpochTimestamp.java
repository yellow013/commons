package io.ffreedom.common.datetime;

import java.time.Instant;

public class EpochTimestamp {

	private Instant instant;
	private long epochMicrosecond;

	public EpochTimestamp(Instant instant) {
		this(instant, true);
	}

	public EpochTimestamp(Instant instant, boolean lazyEvaluation) {
		this.instant = instant;
		if (!lazyEvaluation) {
			getEpochMicrosecond();
		}
	}

	public static EpochTimestamp now() {
		return new EpochTimestamp(Instant.now());
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

		EpochTimestamp now = EpochTimestamp.now();
		System.out.println(now.getEpochSecond());
		System.out.println(now.getNanoOfSecond());
		System.out.println(now.getEpochMicrosecond());

	}

}
