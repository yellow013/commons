package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public enum DateTimePattern {

	HHMM("HHmm"),

	HHMMSS("HHmmss"),

	HH_MM_SS("HH:mm:ss"),

	HH_MM_SS_MILLISECOND("HH:mm:ss.SSS"),

	HH_MM_SS_MICROSECOND("HH:mm:ss.SSSSSS"),

	HH_MM_SS_NANOSECOND("HH:mm:ss.SSSSSSSSS"),

	YYYYMM("yyyyMM"),

	YYYYMMDD("yyyyMMdd"),

	YYYY_MM_DD("yyyy-MM-dd"),

	YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

	YYYYMMDD_HHMMSS("yyyyMMdd-HHmmss"),

	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

	YYYYMMDD_HH_MM_SS_MILLISECOND("yyyyMMdd HH:mm:ss.SSS"),

	YYYYMMDD_HH_MM_SS_MICROSECOND("yyyyMMdd HH:mm:ss.SSSSSS"),

	YYYYMMDD_HH_MM_SS_NANOSECOND("yyyyMMdd HH:mm:ss.SSSSSSSSS"),

	YYYY_MM_DD_HH_MM_SS_MILLISECOND("yyyy-MM-dd HH:mm:ss.SSS"),

	YYYY_MM_DD_HH_MM_SS_MICROSECOND("yyyy-MM-dd HH:mm:ss.SSSSSS"),

	YYYY_MM_DD_HH_MM_SS_NANOSECOND("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"),

	;

	private String pattern;
	private final DateTimeFormatter formatter;

	private DateTimePattern(String pattern) {
		this.pattern = pattern;
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPattern() {
		return pattern;
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public DateFormat newFormat() {
		return new SimpleDateFormat(pattern);
	}

	public DateTimeFormatter newFormatter() {
		return DateTimeFormatter.ofPattern(pattern);
	}

	public static void main(String[] args) {
		System.out.println(DateTimeUtil.now(HH_MM_SS_MICROSECOND));
	}

}
