package io.ffreedom.common.collections;

import java.util.function.Supplier;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.sorted.immutable.ImmutableSortedSetFactoryImpl;

import io.ffreedom.common.utils.ArrayUtil;

public final class ImmutableSets {

	private ImmutableSets() {
	}

	/**
	 * immutable set
	 */
	public static <E> ImmutableSet<E> newImmutableSet(Supplier<Iterable<E>> supplier) {
		if (supplier == null)
			return ImmutableSetFactoryImpl.INSTANCE.empty();
		return newImmutableSet(supplier.get());
	}

	public static <E> ImmutableSet<E> newImmutableSet(Iterable<E> iterable) {
		if (iterable == null)
			return ImmutableSetFactoryImpl.INSTANCE.empty();
		return ImmutableSetFactoryImpl.INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableSet<E> newImmutableSet(E... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return ImmutableSetFactoryImpl.INSTANCE.empty();
		return ImmutableSetFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * immutable sorted set
	 */
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(Supplier<Iterable<E>> supplier) {
		if (supplier == null)
			return ImmutableSortedSetFactoryImpl.INSTANCE.empty();
		return newImmutableSortedSet(supplier.get());
	}

	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(Iterable<E> iterable) {
		if (iterable == null)
			return ImmutableSortedSetFactoryImpl.INSTANCE.empty();
		return ImmutableSortedSetFactoryImpl.INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(E... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return ImmutableSortedSetFactoryImpl.INSTANCE.empty();
		return ImmutableSortedSetFactoryImpl.INSTANCE.with(values);
	}

}
