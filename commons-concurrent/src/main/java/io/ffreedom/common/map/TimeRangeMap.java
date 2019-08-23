package io.ffreedom.common.map;

import static io.ffreedom.common.datetime.DateTimeUtil.timeToHour;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToMinute;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToSecond;
import static io.ffreedom.common.datetime.DateTimeUtil.timeToMillisecond;

import java.time.LocalTime;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;

import io.ffreedom.common.datetime.DateTimeUtil;

public final class TimeRangeMap<V> extends TemporalRangeMap<LocalTime, V, TimeRangeMap<V>> {

	private ToLongFunction<LocalTime> conversionFunc;

	private TimeRangeMap(ToLongFunction<LocalTime> conversionFunc) {
		this.conversionFunc = conversionFunc;
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

	@Override
	public V get(@Nonnull LocalTime key) {
		return get(conversionFunc.applyAsLong(key));
	}

	@Override
	public MutableList<V> get(@Nonnull LocalTime startPoint, @Nonnull LocalTime endPoint) {
		return get(conversionFunc.applyAsLong(startPoint), conversionFunc.applyAsLong(endPoint));
	}

	public static void main(String[] args) {

		System.out.println(Long.MAX_VALUE);
		System.out.println(DateTimeUtil.datetimeToMillisecond());

	}

}
