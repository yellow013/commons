package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import io.ffreedom.common.utils.StringUtil;

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

	// TODO Delete
	private final ThreadLocal<DateFormat> threadLocalDateFormat;

	private final DateTimeFormatter dateTimeFormatter;

	private DateStyle(String pattern) {
		this.pattern = pattern;
		this.threadLocalDateFormat = new ThreadLocal<>();
		this.threadLocalDateFormat.set(new SimpleDateFormat(pattern));
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPattern() {
		return pattern;
	}

	public DateFormat newDateFormat() {
		return new SimpleDateFormat(pattern);
	}

	public DateTimeFormatter newDateTimeFormatter() {
		return DateTimeFormatter.ofPattern(pattern);
	}

	public DateFormat getDateFormat() {
		return threadLocalDateFormat.get();
	}

	public DateTimeFormatter getDateTimeFormatter() {
		return dateTimeFormatter;
	}

	public Date toDate(String str) {
		try {
			return !StringUtil.isNullOrEmpty(str) ? threadLocalDateFormat.get().parse(str) : null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public LocalDateTime toLocalDateTime(String str) {
		return !StringUtil.isNullOrEmpty(str) ? LocalDateTime.parse(str, dateTimeFormatter) : null;
	}

	public LocalDateTime toLocalDateTime(Date str) {
		return toLocalDateTime(str, TimeZone.SYSTEM_DEFAULT);
	}

	public LocalDateTime toLocalDateTime(Date str, ZoneId zoneId) {
		return LocalDateTime.ofInstant(str.toInstant(), zoneId);
	}

	public String getNowStr() {
		return dateTimeFormatter.format(LocalDateTime.now());
	}
	
	public static void main(String[] args) {
		
		System.out.println(DateStyle.HH_MM_SS_MILLISECOND.getNowStr());
		
	}


}
