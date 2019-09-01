package io.ffreedom.common.concurrent.list;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public final class CacheList<T> {

	private volatile List<T> value;
	private AtomicBoolean available = new AtomicBoolean(false);

	private Supplier<List<T>> refresher;

	public CacheList(Supplier<List<T>> refresher) {
		if (refresher == null)
			throw new IllegalArgumentException("refresher is can't null...");
		this.refresher = refresher;
	}

	private CacheList<T> set(List<T> value) {
		this.value = value;
		this.available.set(true);
		return this;
	}

	public Optional<List<T>> get() {
		if (available.get())
			return Optional.ofNullable(value);
		else {
			List<T> value = refresher.get();
			return value == null ? Optional.empty() : set(value).get();
		}
	}

	public CacheList<T> setUnavailable() {
		this.available.set(false);
		return this;
	}

}
