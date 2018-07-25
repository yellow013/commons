package io.ffreedom.common.datetime;

import java.time.Instant;
import java.time.LocalDateTime;

public class Timestamp {

	private Instant instant;
	private long epochMicrosecond;
	private LocalDateTime dateTime;

	/**
	 * @param instant
	 */
	private Timestamp(Instant instant) {
		this.instant = instant;
		this.dateTime = LocalDateTime.ofInstant(instant, TimeZone.SYSTEM_DEFAULT);
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public static void main(String[] args) {
		
		System.out.println(10000 >> 1);
		
	}

}
