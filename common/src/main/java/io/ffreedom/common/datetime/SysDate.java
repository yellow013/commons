package io.ffreedom.common.datetime;

import java.time.LocalDate;

public final class SysDate {

	private static LocalDate now = LocalDate.now();

	private static LocalDate yesterday = now.minusDays(1);
	private static LocalDate tomorrow = now.plusDays(1);

	private SysDate() {
	}

	public static LocalDate getNow() {
		return now;
	}

	public static LocalDate getTomorrow() {
		return tomorrow;
	}

	public static LocalDate getYesterday() {
		return yesterday;
	}

	public static void setNow(LocalDate now) {
		SysDate.now = now;
		SysDate.yesterday = now.minusDays(1);
		SysDate.tomorrow = now.plusDays(1);
	}

}
