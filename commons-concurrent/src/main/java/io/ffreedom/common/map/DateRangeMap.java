package io.ffreedom.common.map;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;

import static io.ffreedom.common.datetime.DateTimeUtil.date;

public final class DateRangeMap<V> extends TemporalRangeMap<LocalDate, V, DateRangeMap<V>> {

	private DateRangeMap() {
	}

	public final static <V> DateRangeMap<V> newMap() {
		return new DateRangeMap<>();
	}

	@Override
	public DateRangeMap<V> put(@Nonnull LocalDate key, V value) {
		put(date(key), value);
		return this;
	}

	@Override
	public V get(@Nonnull LocalDate key) {
		return get(date(key));
	}

	@Override
	public MutableList<V> get(@Nonnull LocalDate startPoint, @Nonnull LocalDate endPoint) {
		return get(date(startPoint), date(endPoint));
	}

}
