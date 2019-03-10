package io.ffreedom.common.datetime;

import java.time.LocalDateTime;

public final class TimePoint implements Comparable<TimePoint> {

	private int date;
	private int time;
	private int nano;

	public TimePoint() {

	}

	public static TimePoint now() {
		LocalDateTime now = LocalDateTime.now();
		return new TimePoint().setDate(now.getYear() * 10000 + now.getMonthValue() * 100 + now.getDayOfMonth())
				.setTime(now.getHour() * 10000 + now.getMinute() * 100 + now.getSecond()).setNano(now.getNano());
	}

	public int getDate() {
		return date;
	}

	public TimePoint setDate(int date) {
		this.date = date;
		return this;
	}

	public int getTime() {
		return time;
	}

	public TimePoint setTime(int time) {
		this.time = time;
		return this;
	}

	public int getNano() {
		return nano;
	}

	public TimePoint setNano(int nano) {
		this.nano = nano;
		return this;
	}

	@Override
	public int compareTo(TimePoint o) {
		return date < o.date ? -1
				: date > o.date ? 1
						: time < o.time ? -1 : time > o.time ? 1 : nano < o.nano ? -1 : nano > o.nano ? 1 : 0;
	}

}
