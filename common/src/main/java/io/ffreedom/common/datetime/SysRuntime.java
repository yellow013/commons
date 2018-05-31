package io.ffreedom.common.datetime;

import java.time.LocalDate;

public final class SysRuntime {

	private static LocalDate date = LocalDate.now();

	private SysRuntime() {
	}

	public static LocalDate getDate() {
		return SysRuntime.date;
	}

	public static void setDate(LocalDate date) {
		SysRuntime.date = date;
	}

}
