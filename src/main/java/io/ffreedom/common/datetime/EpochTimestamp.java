package io.ffreedom.common.datetime;

import java.time.Instant;
import java.time.ZonedDateTime;

public class EpochTimestamp {

	private long epochMilliseconds;
	private long epochMicroseconds;
	private Instant instant;
	private ZonedDateTime zonedDateTime;

	public EpochTimestamp() {
		this.epochMilliseconds = System.currentTimeMillis();
	}

	private void calculateEpochMicroseconds() {
		this.epochMicroseconds = epochMilliseconds * 1000;
	}

	private void calculateInstant() {
		this.instant = Instant.ofEpochMilli(epochMilliseconds);
	}

	private void calculateZonedDateTime() {
		if (instant == null)
			calculateInstant();
		this.zonedDateTime = ZonedDateTime.ofInstant(instant, TimeZones.DEFAULT_ZONE_ID);
	}

	public static EpochTimestamp now() {
		return new EpochTimestamp();
	}

	public long getEpochMilliseconds() {
		return epochMilliseconds;
	}

	public long getEpochMicroseconds() {
		if (epochMicroseconds == 0L)
			calculateEpochMicroseconds();
		return epochMicroseconds;
	}

	public Instant getInstant() {
		if (instant == null)
			calculateInstant();
		return instant;
	}

	public ZonedDateTime getZonedDateTime() {
		if (zonedDateTime == null)
			calculateZonedDateTime();
		return zonedDateTime;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 100000; i++) {
			EpochTime.milliseconds();
			EpochTimestamp.now();
			Instant.now();
			i++;
			i--;
		}

		long l0_0 = System.nanoTime();
		EpochTime.milliseconds();
		long l0_1 = System.nanoTime();

		long l1_0 = System.nanoTime();
		EpochTimestamp.now();
		long l1_1 = System.nanoTime();

		long l2_0 = System.nanoTime();
		Instant.now();
		long l2_1 = System.nanoTime();

		long l0 = l0_1 - l0_0;
		long l1 = l1_1 - l1_0;
		long l2 = l2_1 - l2_0;

		System.out.println(l0);
		System.out.println(l1);
		System.out.println(l2);

		EpochTimestamp now = EpochTimestamp.now();

		System.out.println(now.getEpochMilliseconds());
		System.out.println(now.getEpochMicroseconds());
		System.out.println(now.getInstant().getEpochSecond() * 1000000 + now.getInstant().getNano() / 1000);
		System.out.println(now.getInstant());
		System.out.println(now.getZonedDateTime());

	}

}
