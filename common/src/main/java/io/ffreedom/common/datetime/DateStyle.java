package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import io.ffreedom.common.annotation.thread.ThreadSafe;
import io.ffreedom.common.annotation.thread.ThreadUnsafe;

public enum DateStyle {

	HHMM(DatePattern.HHMM),

	HHMMSS(DatePattern.HHMMSS),

	HH_MM_SS(DatePattern.HH_MM_SS),

	YYYYMMDD(DatePattern.YYYYMMDD),

	YYYYMMDD_HHMMSS(DatePattern.YYYYMMDD_HHMMSS),

	YYYY_MM_DD(DatePattern.YYYY_MM_DD),

	YYYY_MM_DD_HH_MM_SS(DatePattern.YYYY_MM_DD_HH_MM_SS),

	HH_MM_SS_MILLISECOND(DatePattern.HH_MM_SS_MILLISECOND),

	HH_MM_SS_MICROSECOND(DatePattern.HH_MM_SS_MICROSECOND),

	HH_MM_SS_NANOSECOND(DatePattern.HH_MM_SS_NANOSECOND),

	YYYYMMDD_HH_MM_SS_MILLISECOND(DatePattern.YYYYMMDD_HH_MM_SS_MILLISECOND),

	YYYYMMDD_HH_MM_SS_MICROSECOND(DatePattern.YYYYMMDD_HH_MM_SS_MICROSECOND),

	YYYYMMDD_HH_MM_SS_NANOSECOND(DatePattern.YYYYMMDD_HH_MM_SS_NANOSECOND),

	YYYY_MM_DD_HH_MM_SS_MILLISECOND(DatePattern.YYYY_MM_DD_HH_MM_SS_MILLISECOND),

	YYYY_MM_DD_HH_MM_SS_MICROSECOND(DatePattern.YYYY_MM_DD_HH_MM_SS_MICROSECOND),

	YYYY_MM_DD_HH_MM_SS_NANOSECOND(DatePattern.YYYY_MM_DD_HH_MM_SS_NANOSECOND),

	;

	private String pattern;
	private final DateFormat dateFormat;
	private final DateTimeFormatter dateTimeFormatter;

	private DateStyle(String pattern) {
		this.pattern = pattern;
		this.dateFormat = new SimpleDateFormat(pattern);
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPattern() {
		return pattern;
	}

	@ThreadSafe
	public DateFormat newDateFormat() {
		return new SimpleDateFormat(pattern);
	}

	@ThreadSafe
	public DateTimeFormatter newDateTimeFormatter() {
		return DateTimeFormatter.ofPattern(pattern);
	}

	@ThreadUnsafe
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	@ThreadUnsafe
	public DateTimeFormatter getDateTimeFormatter() {
		return dateTimeFormatter;
	}

}
