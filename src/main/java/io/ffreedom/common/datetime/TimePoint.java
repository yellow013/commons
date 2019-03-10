package io.ffreedom.common.datetime;

public final class TimePoint implements Comparable<TimePoint> {

	private int date;
	private int time;
	private int nano;

	public TimePoint() {
		
	}

	@Override
	public int compareTo(TimePoint o) {
		return date < o.date ? -1
				: date > o.date ? 1
						: time < o.time ? -1 
								: time > o.time ? 1 
										: nano < o.nano ? -1 
												: nano > o.nano ? 1 : 0;
	}

}
