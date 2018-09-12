package io.ffreedom.common.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class DateTimeUtil {

	public final static int intYearMonthDay() {
		return intYearMonthDay(LocalDate.now());
	}

	public final static int intYearMonthDay(LocalDate date) {
		return date.getYear() * 10000 + date.getMonth().getValue() * 100 + date.getDayOfMonth();
	}

	public final static int intHourMinuteSecond() {
		return intHourMinuteSecond(LocalTime.now());
	}

	public final static int intHourMinuteSecond(LocalTime time) {
		return time.getHour() * 10000 + time.getMinute() * 100 + time.getSecond();
	}

	public final static int timeAccurateToMillisecond() {
		return timeAccurateToMillisecond(LocalTime.now());
	}

	public final static int timeAccurateToMillisecond(LocalTime time) {
		return intHourMinuteSecond(time) * 1000 + time.getNano() / 1000000;
	}

	public final static long datetimeAccurateToSecond() {
		return datetimeAccurateToSecond(LocalDateTime.now());
	}

	public final static long datetimeAccurateToSecond(LocalDateTime dateTime) {
		return intYearMonthDay(dateTime.toLocalDate()) * 1000000L + intHourMinuteSecond(dateTime.toLocalTime());
	}

	public final static long datetimeAccurateToMillisecond() {
		return datetimeAccurateToMillisecond(LocalDateTime.now());
	}

	public final static long datetimeAccurateToMillisecond(LocalDateTime dateTime) {
		return datetimeAccurateToSecond(dateTime) * 1000L + dateTime.toLocalTime().getNano() / 1000000;
	}

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(intYearMonthDay());
		System.out.println(intHourMinuteSecond());
		System.out.println(timeAccurateToMillisecond());
		System.out.println(datetimeAccurateToSecond());
		System.out.println(datetimeAccurateToMillisecond());

	}

}
