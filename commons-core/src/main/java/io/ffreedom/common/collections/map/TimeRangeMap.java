package io.ffreedom.common.collections.map;

import static io.ffreedom.common.datetime.DateTimeUtil.timeToHour;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToMillisecond;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToMinute;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToSecond;

import java.time.LocalTime;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;

import io.ffreedom.common.datetime.DateTimeUtil;

public final class TimeRangeMap<V> extends TemporalRangeMap<LocalTime, V, TimeRangeMap<V>> {

	private TimeRangeMap(ToLongFunction<LocalTime> conversionFunc) {
		super(conversionFunc);
	}

	public final static <V> TimeRangeMap<V> newMapToHour() {
		return new TimeRangeMap<>(time -> timeToHour(time));
	}

	public final static <V> TimeRangeMap<V> newMapToMinute() {
		return new TimeRangeMap<>(time -> timeToMinute(time));
	}

	public final static <V> TimeRangeMap<V> newMapToSecond() {
		return new TimeRangeMap<>(time -> timeToSecond(time));
	}

	public final static <V> TimeRangeMap<V> newMapToMillisecond() {
		return new TimeRangeMap<>(time -> timeToMillisecond(time));
	}

	@Override
	public TimeRangeMap<V> put(@Nonnull LocalTime key, V value) {
		put(conversionFunc.applyAsLong(key), value);
		return this;
	}

	public static void main(String[] args) {

		System.out.println(Long.MAX_VALUE);
		System.out.println(DateTimeUtil.datetimeToMillisecond());

	}

}
