package io.ffreedom.common.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

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

//		EpochTimestamp now = EpochTimestamp.now();
//		System.out.println(now.getEpochSecond());
//		System.out.println(now.getNanoOfSecond());
//		System.out.println(now.getEpochMicrosecond());

		long l0 = System.nanoTime();
		LocalTime now = LocalTime.now();
		Duration between = Duration.between(LocalTime.MIN, now);
		long r = now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond();
		
		long l1 = System.nanoTime();
		long l = l1 - l0;
		
		System.out.println(l);
		System.out.println(r);
		System.out.println(between.getSeconds());
		
		
		//201099700
		//167711000
		System.out.println(Long.MAX_VALUE);
//			1536917333323
		//9223372036854775807
		//  YYMMDDhhmmssSSS
	//	  9220123123595999999
	}

}
