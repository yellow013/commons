package io.ffreedom.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import io.ffreedom.common.utils.StringUtil;

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
		return date(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
	}

	/**
	 * 根据指定 year, month, day 返回 primitive int 表示的 yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public final static int date(int year, int month, int dayOfMonth) {
		return year * 10000 + month * 100 + dayOfMonth;
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
		return timeToSecond(time.getHour(), time.getMinute(), time.getSecond());
	}

	/**
	 * 根据指定 hour, minute, second 返回 primitive int 表示的 HHmmss
	 * 
	 * @param time
	 * @return
	 */
	public final static int timeToSecond(int hour, int minute, int second) {
		return hour * 10000 + minute * 100 + second;
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
	 * 获取当天经过的 seconds
	 * 
	 * @return
	 */
	public final static int secondsOfDay() {
		return secondsOfDay(LocalTime.now());
	}

	/**
	 * 根据指定 LocalTime 获取当天经过的 seconds
	 * 
	 * @param time
	 * @return
	 */
	public final static int secondsOfDay(@Nonnull LocalTime time) {
		return time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
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

	@CheckForNull
	public final static TemporalAccessor strToDate(DateTimePattern style, String str) {
		if (style == null)
			throw new IllegalArgumentException("style cannot null");
		if (str == null)
			throw new IllegalArgumentException("str cannot null");
		try {
			return str.length() == style.getPattern().length() ? style.getFormatter().parse(str) : null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@CheckForNull
	public final static LocalDateTime strToLocalDateTime(DateTimePattern style, String str) {
		return !StringUtil.isNullOrEmpty(str) ? LocalDateTime.parse(str, style.getFormatter()) : null;
	}

	public final static LocalDateTime dateToLocalDateTime(@Nonnull Date date) {
		return dateToLocalDateTime(date, TimeZones.DEFAULT_ZONE_ID);
	}

	public final static long durationByDay(@Nonnull LocalDate startDate) {
		return durationByDay(startDate, LocalDate.now());
	}

	public final static long durationByDay(@Nonnull LocalDate startDate, @Nonnull LocalDate endDate) {
		return endDate.toEpochDay() - startDate.toEpochDay();
	}

	public final static LocalDateTime dateToLocalDateTime(@Nonnull Date date, @Nonnull ZoneId zoneId) {
		return LocalDateTime.ofInstant(date.toInstant(), zoneId);
	}

	public final static String now(@Nonnull DateTimePattern style) {
		return style.getFormatter().format(LocalDateTime.now());
	}

	private static AtomicReference<LocalDate> currentDate = new AtomicReference<>(LocalDate.now());

	private static AtomicReference<LocalDate> yesterdayDate = new AtomicReference<>(currentDate.get().minusDays(1));

	private static AtomicReference<LocalDate> tomorrowDate = new AtomicReference<>(currentDate.get().plusDays(1));

	public final static LocalDate getCurrentDate() {
		return currentDate.get();
	}

	public final static LocalDate getYesterdayDate() {
		return yesterdayDate.get();
	}

	public final static LocalDate getTomorrowDate() {
		return tomorrowDate.get();
	}

	public final static void setCurrentDate(@Nonnull LocalDate date) {
		currentDate.set(date);
		yesterdayDate.set(date.minusDays(1));
		tomorrowDate.set(date.plusDays(1));
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

	}

}
