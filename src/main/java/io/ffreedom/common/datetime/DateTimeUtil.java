package io.ffreedom.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.ThreadSafe;

import io.ffreedom.common.utils.StringUtil;

@ThreadSafe
public final class DateTimeUtil {

	public final static int intDate() {
		return intDate(LocalDate.now());
	}

	public final static int intDate(LocalDate date) {
		return date.getYear() * 10000 + date.getMonth().getValue() * 100 + date.getDayOfMonth();
	}

	public final static int intTime() {
		return intTime(LocalTime.now());
	}

	public final static int intTime(LocalTime time) {
		return time.getHour() * 10000 + time.getMinute() * 100 + time.getSecond();
	}

	public final static int intTimeToMillisecond() {
		return intTimeToMillisecond(LocalTime.now());
	}

	public final static int intTimeToMillisecond(LocalTime time) {
		return intTime(time) * 1000 + time.getNano() / 1000000;
	}

	public final static long datetimeToSecond() {
		return datetimeToSecond(LocalDateTime.now());
	}

	public final static long datetimeToSecond(LocalDateTime dateTime) {
		return intDate(dateTime.toLocalDate()) * 1000000L + intTime(dateTime.toLocalTime());
	}

	public final static long datetimeToMillisecond() {
		return datetimeToMillisecond(LocalDateTime.now());
	}

	public final static long datetimeToMillisecond(LocalDateTime dateTime) {
		return datetimeToSecond(dateTime) * 1000L + dateTime.toLocalTime().getNano() / 1000000;
	}

	public final static int secondsOfDay() {
		return secondsOfDay(LocalTime.now());
	}

	public final static int secondsOfDay(LocalTime time) {
		return time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
	}

	public final static LocalDate toLocalDate(int date) {
		return LocalDate.of(date / 10000, (date % 10000) / 100, date % 100);
	}

	public final static LocalTime toLocalTime(int time) {
		return LocalTime.of(time / 10000000, (time % 10000000) / 100000, (time % 100000) / 1000,
				(time % 1000) * 1000000);
	}

	public final static LocalDateTime toLocalDateTime(long datetime) {
		return LocalDateTime.of(toLocalDate((int) (datetime / 1000000000)), toLocalTime((int) (datetime % 1000000000)));
	}

	@CheckForNull
	public final static TemporalAccessor strToDate(DateTimeStyle style, String str) {
		try {
			return str.length() == style.getPattern().length() ? style.getFormatter().parse(str) : null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public final static LocalDateTime strToLocalDateTime(DateTimeStyle style, String str) {
		return !StringUtil.isNullOrEmpty(str) ? LocalDateTime.parse(str, style.getFormatter()) : null;
	}

	public final static LocalDateTime dateToLocalDateTime(Date date) {
		return dateToLocalDateTime(date, TimeZones.SYS_DEFAULT);
	}

	public final static LocalDateTime dateToLocalDateTime(Date date, ZoneId zoneId) {
		return LocalDateTime.ofInstant(date.toInstant(), zoneId);
	}

	public final static String now(DateTimeStyle style) {
		return style.getFormatter().format(LocalDateTime.now());
	}

	private static AtomicReference<LocalDate> currentDate = new AtomicReference<>(LocalDate.now());

	private static AtomicReference<LocalDate> yesterdayDate = new AtomicReference<>(
			currentDate.get().minusDays(1));
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

	public final static void setCurrentDate(LocalDate date) {
		currentDate.set(date);
		yesterdayDate.set(date.minusDays(1));
		tomorrowDate.set(date.plusDays(1));
	}

	public static void main(String[] args) {

		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(intDate(dateTime.toLocalDate()));
		System.out.println(intTime(dateTime.toLocalTime()));
		System.out.println(intTimeToMillisecond(dateTime.toLocalTime()));
		System.out.println(datetimeToSecond(dateTime));
		System.out.println(datetimeToMillisecond(dateTime));
		System.out.println(toLocalDate(20161223));
		System.out.println(toLocalTime(234554987));
		System.out.println(toLocalDateTime(20161223234554987L));

	}

}
