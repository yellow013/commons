package io.mercury.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import io.mercury.common.annotations.lang.MayThrowsRuntimeException;
import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.DateTimePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.utils.StringUtil;

@ThreadSafe
public final class DateTimeUtil {

	/**
	 * 返回 primitive int 表示的 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public final static int date() {
		return date(LocalDate.now());
	}

	/**
	 * 根据指定 LocalDate 返回 primitive int 表示的 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public final static int date(@Nonnull LocalDate date) {
		return date.getYear() * 10000 + date.getMonth().getValue() * 100 + date.getDayOfMonth();
	}

	/**
	 * 返回 primitive int 表示的 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public final static int yearDay() {
		return yearDay(LocalDate.now());
	}

	/**
	 * 根据指定 LocalDate 返回 primitive int 表示的 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public final static int yearDay(@Nonnull LocalDate date) {
		return date.getYear() * 1000 + date.getDayOfYear();
	}

	/**
	 * 返回 primitive int 表示的 HH
	 * 
	 * @return
	 */
	public final static int timeToHour() {
		return timeToHour(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive int 表示的 HH
	 * 
	 * @param time
	 * @return
	 */
	public final static int timeToHour(@Nonnull LocalTime time) {
		return time.getHour();
	}

	/**
	 * 返回 primitive int 表示的 HHmm
	 * 
	 * @return
	 */
	public final static int timeToMinute() {
		return timeToMinute(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive int 表示的 HHmm
	 * 
	 * @param time
	 * @return
	 */
	public final static int timeToMinute(@Nonnull LocalTime time) {
		return time.getHour() * 100 + time.getMinute();
	}

	/**
	 * 返回 primitive int 表示的 HHmmss
	 * 
	 * @return
	 */
	public final static int timeToSecond() {
		return timeToSecond(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive int 表示的 HHmmss
	 * 
	 * @param time
	 * @return
	 */
	public final static int timeToSecond(@Nonnull LocalTime time) {
		return time.getHour() * 10000 + time.getMinute() * 100 + time.getSecond();
	}

	/**
	 * 返回 primitive int 表示的 HHmmssSSS
	 * 
	 * @return
	 */
	public final static int timeToMillisecond() {
		return timeToMillisecond(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive int 表示的 HHmmssSSS
	 * 
	 * @param time
	 * @return
	 */
	public final static int timeToMillisecond(@Nonnull LocalTime time) {
		return timeToSecond(time) * 1000 + time.getNano() / 1000000;
	}

	/**
	 * 返回 primitive long 表示的 HHmmssSSSSSS
	 * 
	 * @return
	 */
	public final static long timeToMicrosecond() {
		return timeToMicrosecond(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive long 表示的 HHmmssSSSSSS
	 * 
	 * @param time
	 * @return
	 */
	public final static long timeToMicrosecond(@Nonnull LocalTime time) {
		return timeToSecond(time) * 1000000L + time.getNano() / 1000;
	}

	/**
	 * 返回 primitive long 表示的 HHmmssSSSSSSSSS
	 * 
	 * @return
	 */
	public final static long timeToNanosecond() {
		return timeToNanosecond(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 返回 primitive long 表示的 HHmmssSSSSSSSSS
	 * 
	 * @param time
	 * @return
	 */
	public final static long timeToNanosecond(@Nonnull LocalTime time) {
		return timeToSecond(time) * 1000000000L + time.getNano();
	}

	/**
	 * 返回 primitive long 表示的 yyyyMMddHH
	 * 
	 * @return
	 */
	public final static long datetimeToHour() {
		return datetimeToHour(LocalDateTime.now());
	}

	/**
	 * 根据指定 LocalDateTime 返回 primitive long 表示的 yyyyMMddHH
	 * 
	 * @param dateTime
	 * @return
	 */
	public final static long datetimeToHour(@Nonnull LocalDateTime dateTime) {
		return date(dateTime.toLocalDate()) * 100L + timeToHour(dateTime.toLocalTime());
	}

	/**
	 * 返回 primitive long 表示的 yyyyMMddHHmm
	 * 
	 * @return
	 */
	public final static long datetimeToMinute() {
		return datetimeToMinute(LocalDateTime.now());
	}

	/**
	 * 根据指定 LocalDateTime 返回 primitive long 表示的 yyyyMMddHHmm
	 * 
	 * @param dateTime
	 * @return
	 */
	public final static long datetimeToMinute(@Nonnull LocalDateTime dateTime) {
		return date(dateTime.toLocalDate()) * 10000L + timeToMinute(dateTime.toLocalTime());
	}

	/**
	 * 返回 primitive long 表示的 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public final static long datetimeToSecond() {
		return datetimeToSecond(LocalDateTime.now());
	}

	/**
	 * 根据指定 LocalDateTime 返回 primitive long 表示的 yyyyMMddHHmmss
	 * 
	 * @param dateTime
	 * @return
	 */
	public final static long datetimeToSecond(@Nonnull LocalDateTime dateTime) {
		return date(dateTime.toLocalDate()) * 1000000L + timeToSecond(dateTime.toLocalTime());
	}

	/**
	 * 返回 primitive long 表示的 yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public final static long datetimeToMillisecond() {
		return datetimeToMillisecond(LocalDateTime.now());
	}

	/**
	 * 根据指定 LocalDateTime 返回 primitive long 表示的 yyyyMMddHHmmssSSS<br>
	 * year 不可超过 922337
	 * 
	 * @param dateTime
	 * @return
	 */
	public final static long datetimeToMillisecond(@Nonnull LocalDateTime dateTime) {
		return datetimeToSecond(dateTime) * 1000L + dateTime.toLocalTime().getNano() / 1000000;
	}

	/**
	 * primitive int yyyyMMdd 转换为 LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public final static LocalDate toLocalDate(int date) {
		return LocalDate.of(date / 10000, (date % 10000) / 100, date % 100);
	}

	/**
	 * primitive int HHmmss 转换为 LocalTime
	 * 
	 * @param date
	 * @return
	 */
	public final static LocalTime toLocalTime(int time) {
		return LocalTime.of(time / 10000000, (time % 10000000) / 100000, (time % 100000) / 1000,
				(time % 1000) * 1000000);
	}

	/**
	 * primitive long yyyyMMddHHmmss 转换为 LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public final static LocalDateTime toLocalDateTime(long datetime) {
		return LocalDateTime.of(toLocalDate((int) (datetime / 1000000000)), toLocalTime((int) (datetime % 1000000000)));
	}

	@MayThrowsRuntimeException
	public final static LocalDate toLocalDate(@Nonnull DatePattern pattern, @Nonnull String str) {
		checkFormatParam(pattern, str);
		return LocalDate.parse(str, pattern.getFormatter());
	}

	@MayThrowsRuntimeException
	public final static LocalTime toLocalTime(@Nonnull TimePattern pattern, @Nonnull String str) {
		checkFormatParam(pattern, str);
		return LocalTime.parse(str, pattern.getFormatter());
	}

	@MayThrowsRuntimeException
	public final static LocalDateTime toLocalDateTime(@Nonnull DateTimePattern pattern, @Nonnull String str) {
		checkFormatParam(pattern, str);
		return LocalDateTime.parse(str, pattern.getFormatter());
	}

	private static void checkFormatParam(Pattern pattern, String str) {
		if (pattern == null)
			throw new IllegalArgumentException("pattern cannot null");
		if (StringUtil.isNullOrEmpty(str))
			throw new IllegalArgumentException("str cannot null or empty.");
		if (str.length() == pattern.getPattern().length())
			throw new IllegalArgumentException("str and pattern length no match.");
	}

	public final static long durationByDay(@Nonnull LocalDate startDate) {
		return durationByDay(startDate, LocalDate.now());
	}

	public final static long durationByDay(@Nonnull LocalDate startDate, @Nonnull LocalDate endDate) {
		return endDate.toEpochDay() - startDate.toEpochDay();
	}

	public final static long durationBySecond(@Nonnull LocalTime startTime) {
		return durationBySecond(startTime, LocalTime.now());
	}

	public final static long durationBySecond(@Nonnull LocalTime startTime, @Nonnull LocalTime endTime) {
		return endTime.toSecondOfDay() - startTime.toSecondOfDay();
	}

	public final static LocalDateTime dateToLocalDateTime(@Nonnull Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), TimeZones.SYSTEM_DEFAULT);
	}

	public final static LocalDateTime dateToLocalDateTime(@Nonnull Date date, @Nonnull ZoneId zoneId) {
		return LocalDateTime.ofInstant(date.toInstant(), zoneId);
	}

	public final static String nowDateStr(@Nonnull DatePattern pattern) {
		return dateStr(LocalDate.now(), pattern);
	}

	public final static String dateStr(@Nonnull LocalDate date, @Nonnull DatePattern pattern) {
		return pattern.getFormatter().format(date);
	}

	public final static String nowTimeStr(@Nonnull TimePattern pattern) {
		return timeStr(LocalTime.now(), pattern);
	}

	public final static String timeStr(@Nonnull LocalTime time, @Nonnull TimePattern pattern) {
		return pattern.getFormatter().format(time);
	}

	public final static String nowDatetimeStr(@Nonnull DateTimePattern pattern) {
		return datetimeStr(LocalDateTime.now(), pattern);
	}

	public final static String datetimeStr(@Nonnull LocalDateTime datetime, @Nonnull DateTimePattern pattern) {
		return pattern.getFormatter().format(datetime);
	}

	private static LocalDate CurrentDate = LocalDate.now();

	private static LocalDate PreviousDate = CurrentDate.minusDays(1);

	private static LocalDate NextDate = CurrentDate.plusDays(1);

	public static LocalDate resetCurrentDate(LocalDate date) {
		CurrentDate = date;
		PreviousDate = CurrentDate.minusDays(1);
		NextDate = CurrentDate.plusDays(1);
		return CurrentDate;
	}

	public static LocalDate CurrentDate() {
		return CurrentDate;
	}

	public static LocalDate PreviousDate() {
		return PreviousDate;
	}

	public static LocalDate NextDate() {
		return NextDate;
	}

	public static void main(String[] args) {

		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(date(dateTime.toLocalDate()));
		System.out.println(timeToHour(dateTime.toLocalTime()));
		System.out.println(timeToMinute(dateTime.toLocalTime()));
		System.out.println(timeToSecond(dateTime.toLocalTime()));
		System.out.println(timeToMillisecond(dateTime.toLocalTime()));
		System.out.println(timeToMicrosecond(dateTime.toLocalTime()));
		System.out.println(timeToNanosecond(dateTime.toLocalTime()));
		System.out.println(datetimeToHour(dateTime));
		System.out.println(datetimeToMinute(dateTime));
		System.out.println(datetimeToSecond(dateTime));
		System.out.println(datetimeToMillisecond(dateTime));
		System.out.println(toLocalDate(20161223));
		System.out.println(toLocalTime(234554987));
		System.out.println(toLocalDateTime(20161223234554987L));
		System.out.println(yearDay());
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)));
		System.out.println(System.currentTimeMillis() / (24 * 60 * 60 * 1000));

	}

}
