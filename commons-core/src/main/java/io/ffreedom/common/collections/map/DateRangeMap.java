package io.ffreedom.common.collections.map;

import static io.ffreedom.common.datetime.DateTimeUtil.date;

import java.time.LocalDate;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;

public final class DateRangeMap<V> extends TemporalRangeMap<LocalDate, V, DateRangeMap<V>> {

	private DateRangeMap(ToLongFunction<LocalDate> conversionFunc) {
		super(conversionFunc);
	}

	public final static <V> DateRangeMap<V> newMap() {
		return new DateRangeMap<>(date -> date(date));
	}

	@Override
	public DateRangeMap<V> put(@Nonnull LocalDate key, V value) {
		put(date(key), value);
		return this;
	}

}
