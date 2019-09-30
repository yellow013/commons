package io.ffreedom.common.concurrent.persistence.base;

import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.RollCycles;

public enum FileCycle {

	MINUTE(60, RollCycles.MINUTELY),

	HOUR(60 * 60, RollCycles.HOURLY),

	DAY(24 * 60 * 60, RollCycles.DAILY),

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
		return rollCycle.toIndex((int) epochSecond / seconds, 0);
	}

	public static void main(String[] args) {
		System.out.println(FileCycle.MINUTE.calculateIndex(System.currentTimeMillis() / 1000));

	}

}
