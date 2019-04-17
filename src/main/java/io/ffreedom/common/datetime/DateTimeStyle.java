package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public enum DateTimeStyle {

	HHMM(Pattern.HHMM),

	HHMMSS(Pattern.HHMMSS),

	HH_MM_SS(Pattern.HH_MM_SS),

	YYYYMMDD(Pattern.YYYYMMDD),

	YYYYMMDDHHMMSS(Pattern.YYYYMMDDHHMMSS),

	YYYYMMDD_HHMMSS(Pattern.YYYYMMDD_HHMMSS),

	YYYY_MM_DD(Pattern.YYYY_MM_DD),

	YYYY_MM_DD_HH_MM_SS(Pattern.YYYY_MM_DD_HH_MM_SS),

	HH_MM_SS_MILLISECOND(Pattern.HH_MM_SS_MILLISECOND),

	HH_MM_SS_MICROSECOND(Pattern.HH_MM_SS_MICROSECOND),

	HH_MM_SS_NANOSECOND(Pattern.HH_MM_SS_NANOSECOND),

	YYYYMMDD_HH_MM_SS_MILLISECOND(Pattern.YYYYMMDD_HH_MM_SS_MILLISECOND),

	YYYYMMDD_HH_MM_SS_MICROSECOND(Pattern.YYYYMMDD_HH_MM_SS_MICROSECOND),

	YYYYMMDD_HH_MM_SS_NANOSECOND(Pattern.YYYYMMDD_HH_MM_SS_NANOSECOND),

	YYYY_MM_DD_HH_MM_SS_MILLISECOND(Pattern.YYYY_MM_DD_HH_MM_SS_MILLISECOND),

	YYYY_MM_DD_HH_MM_SS_MICROSECOND(Pattern.YYYY_MM_DD_HH_MM_SS_MICROSECOND),

	YYYY_MM_DD_HH_MM_SS_NANOSECOND(Pattern.YYYY_MM_DD_HH_MM_SS_NANOSECOND),

	;

	public static interface Pattern {

		String HHMM = "HHmm";

		String HHMMSS = "HHmmss";

		String HH_MM_SS = "HH:mm:ss";

		String YYYYMMDD = "yyyyMMdd";

		String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

		String YYYYMMDD_HHMMSS = "yyyyMMdd-HHmmss";

		String YYYY_MM_DD = "yyyy-MM-dd";

		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

		String HH_MM_SS_MILLISECOND = "HH:mm:ss.SSS";

		String HH_MM_SS_MICROSECOND = "HH:mm:ss.SSSSSS";

		String HH_MM_SS_NANOSECOND = "HH:mm:ss.SSSSSSSSS";

		String YYYYMMDD_HH_MM_SS_MILLISECOND = "yyyyMMdd HH:mm:ss.SSS";

		String YYYYMMDD_HH_MM_SS_MICROSECOND = "yyyyMMdd HH:mm:ss.SSSSSS";

		String YYYYMMDD_HH_MM_SS_NANOSECOND = "yyyyMMdd HH:mm:ss.SSSSSSSSS";

		String YYYY_MM_DD_HH_MM_SS_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

		String YYYY_MM_DD_HH_MM_SS_MICROSECOND = "yyyy-MM-dd HH:mm:ss.SSSSSS";

		String YYYY_MM_DD_HH_MM_SS_NANOSECOND = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";

	}

	private String pattern;

	private final DateTimeFormatter formatter;

	private DateTimeStyle(String pattern) {
		this.pattern = pattern;
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPattern() {
		return pattern;
	}

	public DateTimeFormatter getFormatter() {
		return formatter;
	}

	public DateFormat newDateFormat() {
		return new SimpleDateFormat(pattern);
	}

	public DateTimeFormatter newFormatter() {
		return DateTimeFormatter.ofPattern(pattern);
	}

	public static void main(String[] args) {
		System.out.println(DateTimeUtil.now(HH_MM_SS_MICROSECOND));
	}

}
