package io.ffreedom.commons.chronicle.queue.base;

import java.time.Instant;

import io.ffreedom.common.datetime.TimeConstants;
import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.RollCycles;

public enum FileCycle {

	MINUTELY(TimeConstants.SECONDS_PER_MINUTE, RollCycles.MINUTELY),

	HOURLY(TimeConstants.SECONDS_PER_HOUR, RollCycles.HOURLY),

	SMALL_DAILY(TimeConstants.SECONDS_PER_DAY, RollCycles.SMALL_DAILY),

	DAILY(TimeConstants.SECONDS_PER_DAY, RollCycles.DAILY),

	;

	/* 每个滚动文件包含的秒数 */
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

	/**
	 * 根据<b> epochSecond </b>计算索引, 根据滚动周期计算文件的<b> cycle </b>
	 * 
	 * @param epochSecond
	 * @return
	 */
	public long calculateIndex(long epochSecond) {
		if (epochSecond < 0)
			throw new IllegalArgumentException("param : epochSecond is can't less than 0");
		return rollCycle.toIndex((int) (epochSecond / seconds), 0);
	}

	public static void main(String[] args) {
		System.out.println(FileCycle.MINUTELY.calculateIndex(System.currentTimeMillis() / 1000));

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Instant.now().getEpochSecond() / 60);

	}

}
