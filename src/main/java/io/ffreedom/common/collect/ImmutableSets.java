package io.ffreedom.common.collect;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.sorted.immutable.ImmutableSortedSetFactoryImpl;

public class ImmutableSets {

	private ImmutableSets() {
	}

	/**
	 * immutable set
	 */
	public static <E> ImmutableSet<E> newImmutableSet(Iterable<E> items) {
		return ImmutableSetFactoryImpl.INSTANCE.withAll(items);
	}

	@SafeVarargs
	public static <E> ImmutableSet<E> newImmutableSet(E... es) {
		return ImmutableSetFactoryImpl.INSTANCE.with(es);
	}

	/**
	 * immutable sorted set
	 */
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(Iterable<E> items) {
		return ImmutableSortedSetFactoryImpl.INSTANCE.withAll(items);
	}

	@SafeVarargs
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(E... es) {
		return ImmutableSortedSetFactoryImpl.INSTANCE.with(es);
	}

}
