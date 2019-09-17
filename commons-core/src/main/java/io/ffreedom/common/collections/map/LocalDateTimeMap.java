package io.ffreedom.common.collections.map;

import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToHour;
import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToMinute;
import static io.ffreedom.common.datetime.DateTimeUtil.datetimeToSecond;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class LocalDateTimeMap<V> extends TemporalMap<LocalDateTime, V, LocalDateTimeMap<V>> {

	/**
	 * 
	 * @param conversionFunc
	 */
	private LocalDateTimeMap(ToLongFunction<LocalDateTime> keyToLangFunc,
			Function<LocalDateTime, LocalDateTime> nextKeyFunc, BiPredicate<LocalDateTime, LocalDateTime> hasNextKey) {
		super(keyToLangFunc, nextKeyFunc, hasNextKey);
	}

	private static ToLongFunction<LocalDateTime> keyToLangFuncWithHour = key -> datetimeToHour(key);
	private static Function<LocalDateTime, LocalDateTime> nextKeyFuncWithHour = key -> key.plusHours(1);

	private static ToLongFunction<LocalDateTime> keyToLangFuncWithMinute = key -> datetimeToMinute(key);
	private static Function<LocalDateTime, LocalDateTime> nextKeyFuncWithMinute = key -> key.plusMinutes(1);

	private static ToLongFunction<LocalDateTime> keyToLangFuncWithSecond = key -> datetimeToSecond(key);
	private static Function<LocalDateTime, LocalDateTime> nextKeyFuncWithSecond = key -> key.plusSeconds(1);

	private static BiPredicate<LocalDateTime, LocalDateTime> hasNextKey = (nextKey,
			endPoint) -> nextKey.isBefore(endPoint) || nextKey.equals(endPoint);

	public final static <V> LocalDateTimeMap<V> newMapToHour() {
		return new LocalDateTimeMap<>(keyToLangFuncWithHour, nextKeyFuncWithHour, hasNextKey);

	}

	public final static <V> LocalDateTimeMap<V> newMapToMinute() {
		return new LocalDateTimeMap<>(keyToLangFuncWithMinute, nextKeyFuncWithMinute, hasNextKey);
	}

	public final static <V> LocalDateTimeMap<V> newMapToSecond() {
		return new LocalDateTimeMap<>(keyToLangFuncWithSecond, nextKeyFuncWithSecond, hasNextKey);
	}

	@Override
	public LocalDateTimeMap<V> put(@Nonnull LocalDateTime key, V value) {
		put(keyToLangFunc.applyAsLong(key), value);
		return this;
	}

}
