package io.mercury.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import static io.mercury.common.datetime.TimeConst.MICROS_PER_DAY;
import static io.mercury.common.datetime.TimeConst.MICROS_PER_MILLIS;
import static io.mercury.common.datetime.TimeConst.MICROS_PER_SECONDS;
import static io.mercury.common.datetime.TimeConst.MILLIS_PER_DAY;
import static io.mercury.common.datetime.TimeConst.MILLIS_PER_SECONDS;
import static io.mercury.common.datetime.TimeConst.NANOS_PER_MICROS;
import static io.mercury.common.datetime.TimeConst.NANOS_PER_MILLIS;
import static io.mercury.common.datetime.TimeZones.SYSTEM_DEFAULT_OFFSET;
import static java.lang.System.currentTimeMillis;;

public final class EpochTime {

	public static long day() {
		return currentTimeMillis() / MILLIS_PER_DAY;
	}

	public static long day(@Nonnull LocalDate date) {
		return date.toEpochDay();
	}

	public static long day(@Nonnull LocalDateTime dateTime) {
		return day(dateTime.toLocalDate());
	}

	public static long day(@Nonnull ZonedDateTime dateTime) {
		return day(dateTime.toLocalDate());
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

	public static long millis() {
		return currentTimeMillis();
	}

	public static long millis(@Nonnull ZonedDateTime datetime) {
		return datetime.toEpochSecond() * MILLIS_PER_SECONDS + datetime.getNano() / NANOS_PER_MILLIS;
	}

	public static long millis(@Nonnull LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * MILLIS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MILLIS_PER_SECONDS + datetime.getNano() / NANOS_PER_MILLIS
				- SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * MILLIS_PER_SECONDS;
	}

	public static long millis(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset zoneOffset) {
		return datetime.toLocalDate().toEpochDay() * MILLIS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MILLIS_PER_SECONDS + datetime.getNano() / NANOS_PER_MILLIS
				- zoneOffset.getTotalSeconds() * MILLIS_PER_SECONDS;
	}

	public static long micros() {
		return currentTimeMillis() * MICROS_PER_MILLIS;
	}

	public static long micros(@Nonnull ZonedDateTime datetime) {
		return datetime.toEpochSecond() * MICROS_PER_SECONDS + datetime.getNano() / NANOS_PER_MICROS;
	}

	public static long micros(@Nonnull LocalDateTime datetime) {
		return datetime.toLocalDate().toEpochDay() * MICROS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MICROS_PER_SECONDS + datetime.getNano() / NANOS_PER_MICROS
				- SYSTEM_DEFAULT_OFFSET.getTotalSeconds() * MICROS_PER_SECONDS;
	}

	public static long micros(@Nonnull LocalDateTime datetime, @Nonnull ZoneOffset zoneOffset) {
		return datetime.toLocalDate().toEpochDay() * MICROS_PER_DAY
				+ datetime.toLocalTime().toSecondOfDay() * MICROS_PER_SECONDS + datetime.getNano() / NANOS_PER_MICROS
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
		System.out.println(millis());
		System.out.println(millis(now));
		System.out.println(millis(now, offset));
		System.out.println();
		System.out.println(micros());
		System.out.println(micros(now));
		System.out.println(micros(now, offset));

		System.out.println(24 >> 1);

		// Charset.availableCharsets().entrySet().stream().forEach(entity ->
		// System.out.println(entity));

	}

}
