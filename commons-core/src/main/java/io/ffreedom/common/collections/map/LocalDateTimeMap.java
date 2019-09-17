package io.ffreedom.common.collections.map;

import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToHour;
import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToMillisecond;
import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToMinute;
import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToSecond;

import java.time.LocalDateTime;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class DatetimeRangeMap<V> extends TemporalRangeMap<LocalDateTime, V, DatetimeRangeMap<V>> {

	/**
	 * 
	 * @param conversionFunc
	 */
	private DatetimeRangeMap(ToLongFunction<LocalDateTime> conversionFunc) {
		super(conversionFunc);
	}

	public final static <V> DatetimeRangeMap<V> newMapToHour() {
		return new DatetimeRangeMap<>(datetime -> datetimeToHour(datetime));
	}

	public final static <V> DatetimeRangeMap<V> newMapToMinute() {
		return new DatetimeRangeMap<>(datetime -> datetimeToMinute(datetime));
	}

	public final static <V> DatetimeRangeMap<V> newMapToSecond() {
		return new DatetimeRangeMap<>(datetime -> datetimeToSecond(datetime));
	}

	public final static <V> DatetimeRangeMap<V> newMapToMillisecond() {
		return new DatetimeRangeMap<>(datetime -> datetimeToMillisecond(datetime));
	}

	@Override
	public DatetimeRangeMap<V> put(@Nonnull LocalDateTime key, V value) {
		put(conversionFunc.applyAsLong(key), value);
		return this;
	}

}
