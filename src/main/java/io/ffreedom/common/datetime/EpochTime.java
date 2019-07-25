package io.ffreedom.common.datetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static io.ffreedom.common.datetime.TimeZones.DEFAULT_ZONE_OFFSET;

public final class EpochTime {

	public static long seconds() {
		return System.currentTimeMillis() / 1000;
	}

	public static long seconds(LocalDateTime datetime) {
		return datetime.toEpochSecond(DEFAULT_ZONE_OFFSET);
	}

	public static long seconds(LocalDateTime datetime, ZoneOffset offset) {
		return datetime.toEpochSecond(offset);
	}

	public static long milliseconds() {
		return System.currentTimeMillis();
	}

	public static long milliseconds(LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * 86400000L + datetime.toLocalTime().toSecondOfDay() * 1000
				+ datetime.toLocalTime().getNano() / 1000000 - DEFAULT_ZONE_OFFSET.getTotalSeconds() * 1000;
	}

	public static long milliseconds(LocalDateTime datetime, ZoneOffset offset) {
		return datetime.toLocalDate().toEpochDay() * 86400000L + datetime.toLocalTime().toSecondOfDay() * 1000
				+ datetime.toLocalTime().getNano() / 1000000 - offset.getTotalSeconds() * 1000;
	}

	public static long microseconds() {
		return System.currentTimeMillis() * 1000;
	}

	public static long microseconds(LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * 86400000000L + datetime.toLocalTime().toSecondOfDay() * 1000000L
				+ datetime.toLocalTime().getNano() / 1000 - DEFAULT_ZONE_OFFSET.getTotalSeconds() * 1000000L;
	}

	public static long microseconds(LocalDateTime datetime, ZoneOffset offset) {
		return datetime.toLocalDate().toEpochDay() * 86400000000L + datetime.toLocalTime().toSecondOfDay() * 1000000L
				+ datetime.toLocalTime().getNano() / 1000 - offset.getTotalSeconds() * 1000000L;
	}

	public static void main(String[] args) {
		long epoch = System.currentTimeMillis();
		LocalDateTime now = LocalDateTime.now();
		ZoneOffset offset = ZonedDateTime.now().getOffset();
		System.out.println(offset.getId() + "-" + offset.getTotalSeconds());
		System.out.println(epoch);
		System.out.println();
		System.out.println(seconds(now));
		System.out.println(now.toEpochSecond(offset));
		System.out.println();
		System.out.println(milliseconds());
		System.out.println(milliseconds(now));
		System.out.println(milliseconds(now, offset));
		System.out.println();
		System.out.println(microseconds());
		System.out.println(microseconds(now));
		System.out.println(microseconds(now, offset));

		// Charset.availableCharsets().entrySet().stream().forEach(entity ->
		// System.out.println(entity));

	}

}
