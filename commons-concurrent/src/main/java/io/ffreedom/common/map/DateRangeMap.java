package io.ffreedom.common.map;

import static io.ffreedom.common.datetime.DateTimeUtil.date;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;

public class DateRangeMap<V> extends TemporalRangeMap<LocalDate, V, DateRangeMap<V>> {

	public final static <V> DateRangeMap<V> newInstance() {
		return new DateRangeMap<>();
	}

	@Override
	public DateRangeMap<V> put(LocalDate key, V value) {
		put(date(key), value);
		return this;
	}

	@Override
	public V get(@Nonnull LocalDate key) {
		return get(date(key));
	}

	@Override
	public MutableList<V> get(LocalDate startPoint, LocalDate endPoint) {
		return get(date(startPoint), date(endPoint));
	}

}
