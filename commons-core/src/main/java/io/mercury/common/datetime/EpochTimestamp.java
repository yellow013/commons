package io.mercury.common.datetime;

import static java.lang.System.currentTimeMillis;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class EpochTimestamp {

	private long epochMillis;
	private long epochMicros;
	private Instant instant;
	private ZonedDateTime dateTime;

	private EpochTimestamp() {
		this.epochMillis = currentTimeMillis();
	}

	private void calculateEpochMicros() {
		this.epochMicros = epochMillis * 1000;
	}

	private void newInstant() {
		this.instant = Instant.ofEpochMilli(epochMillis);
	}

	private ZonedDateTime newZonedDateTime() {
		if (instant == null)
			newInstant();
		return updateDateTimeOf(TimeZone.SYS_DEFAULT);
	}

	public ZonedDateTime updateDateTimeOf(ZoneId zoneId) {
		if (instant == null)
			newInstant();
		this.dateTime = ZonedDateTime.ofInstant(instant, zoneId);
		return dateTime;
	}

	public static EpochTimestamp now() {
		return new EpochTimestamp();
	}

	public long epochMillis() {
		return epochMillis;
	}

	public long epochMicros() {
		if (epochMicros == 0L)
			calculateEpochMicros();
		return epochMicros;
	}

	public Instant instant() {
		if (instant == null)
			newInstant();
		return instant;
	}

	public ZonedDateTime dateTime() {
		if (dateTime == null)
			return newZonedDateTime();
		return dateTime;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 100000; i++) {
			EpochTime.millis();
			EpochTimestamp.now();
			Instant.now();
			i++;
			i--;
		}

		for (int i = 0; i < 10000; i++) {
			long l0_0 = System.nanoTime();
			// EpochTime.milliseconds();
			// EpochTimestamp.now();
			Instant.now();
			long l0_1 = System.nanoTime();
			long l0 = l0_1 - l0_0;
			System.out.println(l0);
		}

		long l1_0 = System.nanoTime();
		EpochTimestamp.now();
		long l1_1 = System.nanoTime();

		long l2_0 = System.nanoTime();
		Instant.now();
		long l2_1 = System.nanoTime();

		long l1 = l1_1 - l1_0;
		long l2 = l2_1 - l2_0;

		System.out.println(l1);
		System.out.println(l2);

		EpochTimestamp now = EpochTimestamp.now();

		System.out.println(now.epochMillis());
		System.out.println(now.epochMicros());
		System.out.println(now.instant().getEpochSecond() * 1000000 + now.instant().getNano() / 1000);
		System.out.println(now.instant());
		System.out.println(now.dateTime());

	}

}
