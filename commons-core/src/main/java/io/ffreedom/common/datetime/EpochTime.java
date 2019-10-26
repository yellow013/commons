package io.ffreedom.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import static java.lang.System.currentTimeMillis;
import static io.ffreedom.common.datetime.TimeConstants.MICROS_PER_DAY;
import static io.ffreedom.common.datetime.TimeConstants.MICROS_PER_MILLIS;
import static io.ffreedom.common.datetime.TimeConstants.MICROS_PER_SECONDS;
import static io.ffreedom.common.datetime.TimeConstants.MILLIS_PER_DAY;
import static io.ffreedom.common.datetime.TimeConstants.MILLIS_PER_SECONDS;
import static io.ffreedom.common.datetime.TimeConstants.NANOS_PER_MICROS;
import static io.ffreedom.common.datetime.TimeConstants.NANOS_PER_MILLIS;
import static io.ffreedom.common.datetime.TimeZones.SYSTEM_DEFAULT_OFFSET;;

public final class EpochTime {

	public static long day() {
		return currentTimeMillis() / MILLIS_PER_DAY;
	}

	public static long day(@Nonnull LocalDate date) {
		return date.toEpochDay();
	}

	public static long seconds() {
		return currentTimeMillis() / MILLIS_PER_SECONDS;
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
		return datetime.toLocalDate().toEpochDay() * MILLIS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MILLIS_PER_SECONDS
				+ datetime.toLocalTime().getNano() / NANOS_PER_MILLIS
				- SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * MILLIS_PER_SECONDS;
	}

	public static long milliseconds(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset zoneOffset) {
		return datetime.toLocalDate().toEpochDay() * MILLIS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MILLIS_PER_SECONDS
				+ datetime.toLocalTime().getNano() / NANOS_PER_MILLIS
				- zoneOffset.getTotalSeconds() * MILLIS_PER_SECONDS;
	}

	public static long microseconds() {
		return currentTimeMillis() * MICROS_PER_MILLIS;
	}

	public static long microseconds(@Nonnull LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * MICROS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MICROS_PER_SECONDS
				+ datetime.toLocalTime().getNano() / NANOS_PER_MICROS
				- SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * MICROS_PER_SECONDS;
	}

	public static long microseconds(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset zoneOffset) {
		return datetime.toLocalDate().toEpochDay() * MICROS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MICROS_PER_SECONDS
				+ datetime.toLocalTime().getNano() / NANOS_PER_MICROS
				- zoneOffset.getTotalSeconds() * MICROS_PER_SECONDS;
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

		System.out.println(24 >> 1);

		// Charset.availableCharsets().entrySet().stream().forEach(entity ->
		// System.out.println(entity));

	}

}
