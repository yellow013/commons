package io.ffreedom.commons.chronicle.queue.base;

import io.ffreedom.common.datetime.TimeConstants;
import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.RollCycles;

public enum FileCycle {

	MINUTELY(TimeConstants.SECONDS_PER_MINUTE, RollCycles.MINUTELY),

	HOURLY(TimeConstants.SECONDS_PER_HOUR, RollCycles.HOURLY),

	DAILY(TimeConstants.SECONDS_PER_DAY, RollCycles.DAILY),

	;

	private int seconds;
	private RollCycle rollCycle;

	private FileCycle(int seconds, RollCycle rollCycle) {
		this.seconds = seconds;
		this.rollCycle = rollCycle;
	}

	public int getSeconds() {
		return seconds;
	}

	public RollCycle getRollCycle() {
		return rollCycle;
	}

	public long calculateIndex(long epochSecond) {
		if (epochSecond < 0)
			throw new IllegalArgumentException("param : epochSecond is can't less than 0");
		return rollCycle.toIndex((int) (epochSecond / seconds), 0);
	}

	public static void main(String[] args) {
		System.out.println(FileCycle.MINUTELY.calculateIndex(System.currentTimeMillis() / 1000));

	}

}
