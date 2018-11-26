package io.ffreedom.common.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

public class EpochTimestamp {

	private long epochMilliseconds;
	private long epochMicroseconds;
	private Instant instant;

	public EpochTimestamp(boolean lazyCalculate) {
		this.epochMilliseconds = System.currentTimeMillis();
		if (!lazyCalculate) {
			calculateInstant();
			calculateEpochMicroseconds();
		}
	}

	private void calculateInstant() {
		this.instant = Instant.ofEpochMilli(epochMilliseconds);
	}

	private void calculateEpochMicroseconds() {
		if (instant == null)
			calculateInstant();
		this.epochMicroseconds = instant.getEpochSecond() * 1000000 + instant.getNano() / 1000;
	}

	public static EpochTimestamp now() {
		return now(false);
	}

	public static EpochTimestamp now(boolean lazyEvaluation) {
		return new EpochTimestamp(lazyEvaluation);
	}

	public Instant getInstant() {
		if (instant == null)
			calculateInstant();
		return instant;
	}

	public long getEpochSecond() {
		if (instant == null)
			calculateInstant();
		return instant.getEpochSecond();
	}

	public long getEpochMilliseconds() {
		return epochMilliseconds;
	}

	public long getEpochMicroseconds() {
		if (epochMicroseconds == 0L)
			calculateEpochMicroseconds();
		return epochMicroseconds;
	}

	public static void main(String[] args) {

		// EpochTimestamp now = EpochTimestamp.now();
		// System.out.println(now.getEpochSecond());
		// System.out.println(now.getNanoOfSecond());
		// System.out.println(now.getEpochMicrosecond());

		long l0 = System.nanoTime();
		LocalTime now = LocalTime.now();
		Duration between = Duration.between(LocalTime.MIN, now);
		// long r = now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond();

		long l1 = System.nanoTime();
		long l = l1 - l0;

		System.out.println(l);
		// System.out.println(r);
		System.out.println(between.getSeconds());

		// 201099700
		// 167711000
		System.out.println(Long.MAX_VALUE);
		// 1536917333323
		// 9223372036854775807
		// YYMMDDhhmmssSSS
		// 9220123123595999999
	}

}
