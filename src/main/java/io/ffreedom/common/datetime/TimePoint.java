package io.ffreedom.common.datetime;

import java.time.LocalDateTime;

public final class TimePoint implements Comparable<TimePoint> {

	private int date;
	private int time;
	private int nano;

	public TimePoint(int date, int time, int nano) {
		super();
		this.date = date;
		this.time = time;
		this.nano = nano;
	}

	public static TimePoint now() {
		LocalDateTime now = LocalDateTime.now();
		return new TimePoint(now.getYear() * 10000 + now.getMonthValue() * 100 + now.getDayOfMonth(),
				now.getHour() * 10000 + now.getMinute() * 100 + now.getSecond(), now.getNano());
	}

	public static TimePoint of(int date, int time, int nano) {
		return new TimePoint(date, time, nano);
	}

	public int getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

	public int getNano() {
		return nano;
	}

	@Override
	public int compareTo(TimePoint o) {
		return date < o.date ? -1
				: date > o.date ? 1
						: time < o.time ? -1 : time > o.time ? 1 : nano < o.nano ? -1 : nano > o.nano ? 1 : 0;
	}

	public static void main(String[] args) {

		TimePoint now = TimePoint.now();

		System.out.println(now.getDate());
		System.out.println(now.getTime());
		System.out.println(now.getNano());

	}

}
