package io.ffreedom.common.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public abstract class Pattern {

	private final String pattern;
	private final DateTimeFormatter formatter;

	protected Pattern(String pattern) {
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

	/**
	 * 日期格式列表
	 * 
	 * @author yellow013
	 *
	 */
	public static final class DatePattern extends Pattern {

		public final static DatePattern YYYYMM = new DatePattern("yyyyMM");

		public final static DatePattern YYYYMMDD = new DatePattern("yyyyMMdd");

		public final static DatePattern YYYY_MM = new DatePattern("yyyy-MM");

		public final static DatePattern YYYY_MM_DD = new DatePattern("yyyy-MM-dd");

		private DatePattern(String pattern) {
			super(pattern);
		}

	}

	public static final class TimePattern extends Pattern {

		public final static TimePattern HHMM = new TimePattern("HHmm");

		public final static TimePattern HH_MM = new TimePattern("HH:mm");

		public final static TimePattern HHMMSS = new TimePattern("HHmmss");

		public final static TimePattern HH_MM_SS = new TimePattern("HH:mm:ss");

		public final static TimePattern HH_MM_SS_MILLISECOND = new TimePattern("HH:mm:ss.SSS");

		public final static TimePattern HH_MM_SS_MICROSECOND = new TimePattern("HH:mm:ss.SSSSSS");

		public final static TimePattern HH_MM_SS_NANOSECOND = new TimePattern("HH:mm:ss.SSSSSSSSS");

		;

		private TimePattern(String pattern) {
			super(pattern);
		}

	}

	public static final class DateTimePattern extends Pattern {

		public final static DateTimePattern YYYYMMDDHHMMSS = new DateTimePattern("yyyyMMddHHmmss");

		public final static DateTimePattern YYYYMMDD_HHMMSS = new DateTimePattern("yyyyMMdd-HHmmss");

		public final static DateTimePattern YYYY_MM_DD_HH_MM_SS = new DateTimePattern("yyyy-MM-dd HH:mm:ss");

		public final static DateTimePattern YYYYMMDD_HH_MM_SS_MILLISECOND = new DateTimePattern(
				"yyyyMMdd HH:mm:ss.SSS");

		public final static DateTimePattern YYYYMMDD_HH_MM_SS_MICROSECOND = new DateTimePattern(
				"yyyyMMdd HH:mm:ss.SSSSSS");

		public final static DateTimePattern YYYYMMDD_HH_MM_SS_NANOSECOND = new DateTimePattern(
				"yyyyMMdd HH:mm:ss.SSSSSSSSS");

		public final static DateTimePattern YYYY_MM_DD_HH_MM_SS_MILLISECOND = new DateTimePattern(
				"yyyy-MM-dd HH:mm:ss.SSS");

		public final static DateTimePattern YYYY_MM_DD_HH_MM_SS_MICROSECOND = new DateTimePattern(
				"yyyy-MM-dd HH:mm:ss.SSSSSS");

		public final static DateTimePattern YYYY_MM_DD_HH_MM_SS_NANOSECOND = new DateTimePattern(
				"yyyy-MM-dd HH:mm:ss.SSSSSSSSS");

		private DateTimePattern(String pattern) {
			super(pattern);
		}

	}

}
