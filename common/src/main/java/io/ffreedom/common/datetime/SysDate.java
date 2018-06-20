package io.ffreedom.common.datetime;

import java.time.LocalDate;

public final class SysDate {

	private static LocalDate now = LocalDate.now();

	private static LocalDate tomorrow = now.plusDays(1);

	private SysDate() {
	}

	public static LocalDate getNow() {
		return now;
	}

	public static LocalDate getTomorrow() {
		return tomorrow;
	}

	public static void setNow(LocalDate now) {
		SysDate.now = now;
		SysDate.tomorrow = now.plusDays(1);
	}

}
