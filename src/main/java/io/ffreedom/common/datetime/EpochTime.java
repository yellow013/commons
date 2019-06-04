package io.ffreedom.common.datetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class EpochTime {

	public static long seconds() {
		return System.currentTimeMillis() / 1000;
	}

	public static long seconds(LocalDateTime datetime, ZoneOffset offset) {
		return datetime.toEpochSecond(offset);
	}

	public static long milliseconds() {
		return System.currentTimeMillis();
	}

	public static long milliseconds(LocalDateTime datetime, ZoneOffset offset) {
		return datetime.toLocalDate().toEpochDay() * 86400000L + datetime.toLocalTime().toSecondOfDay() * 1000
				+ datetime.toLocalTime().getNano() / 1000000 - offset.getTotalSeconds() * 1000;
	}

	public static long microseconds() {
		return System.currentTimeMillis() * 1000;
	}

	public static void main(String[] args) {
		long epoch = System.currentTimeMillis();
		LocalDateTime now = LocalDateTime.now();
		ZoneOffset offset = ZonedDateTime.now().getOffset();
		System.out.println(offset.getId() + offset.getTotalSeconds());
		System.out.println(epoch);
		System.out.println(milliseconds(now, offset));
		System.out.println(now.toEpochSecond(offset));
	}

}
