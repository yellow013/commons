package io.mercury.common.collections.customize;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Keeper<K, V> {

	@Nonnull
	V acquire(@Nonnull K k);

	@Nullable
	V get(@Nonnull K k);

}
