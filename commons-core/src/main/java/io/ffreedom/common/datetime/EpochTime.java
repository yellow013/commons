package io.ffreedom.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import static java.lang.System.currentTimeMillis;

import static io.ffreedom.common.datetime.TimeZones.SYSTEM_DEFAULT_OFFSET;;

public final class EpochTime {

	private final static int DAY_MILLIS = 24 * 60 * 60 * 1000;

	public static long day() {
		return currentTimeMillis() / DAY_MILLIS;
	}

	public static long day(@Nonnull LocalDate date) {
		return date.toEpochDay();
	}

	public static long seconds() {
		return currentTimeMillis() / 1000;
	}

	public static long seconds(@Nonnull LocalDateTime datetime) {
		return datetime.toEpochSecond(SYSTEM_DEFAULT_OFFSET);
	}

	public static long seconds(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset offset) {
		return datetime.toEpochSecond(offset);
	}

	public static long milliseconds() {
		return currentTimeMillis();
	}

	public static long milliseconds(@Nonnull LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * 86400000L + datetime.toLocalTime().toSecondOfDay() * 1000
				+ datetime.toLocalTime().getNano() / 1000000 - SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * 1000;
	}

	public static long milliseconds(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset offset) {
		return datetime.toLocalDate().toEpochDay() * 86400000L + datetime.toLocalTime().toSecondOfDay() * 1000
				+ datetime.toLocalTime().getNano() / 1000000 - offset.getTotalSeconds() * 1000;
	}

	public static long microseconds() {
		return currentTimeMillis() * 1000;
	}

	public static long microseconds(@Nonnull LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * 86400000000L + datetime.toLocalTime().toSecondOfDay() * 1000000L
				+ datetime.toLocalTime().getNano() / 1000 - SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * 1000000L;
	}

	public static long microseconds(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset offset) {
		return datetime.toLocalDate().toEpochDay() * 86400000000L + datetime.toLocalTime().toSecondOfDay() * 1000000L
				+ datetime.toLocalTime().getNano() / 1000 - offset.getTotalSeconds() * 1000000L;
	}

	public static void main(String[] args) {
		long epoch = currentTimeMillis();
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
