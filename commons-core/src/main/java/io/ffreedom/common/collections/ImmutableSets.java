package io.ffreedom.common.collections;

import static io.ffreedom.common.utils.ArrayUtil.isNullOrEmpty;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.factory.set.ImmutableSetFactory;
import org.eclipse.collections.api.factory.set.primitive.ImmutableDoubleSetFactory;
import org.eclipse.collections.api.factory.set.primitive.ImmutableIntSetFactory;
import org.eclipse.collections.api.factory.set.primitive.ImmutableLongSetFactory;
import org.eclipse.collections.api.factory.set.sorted.ImmutableSortedSetFactory;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.primitive.ImmutableDoubleSet;
import org.eclipse.collections.api.set.primitive.ImmutableIntSet;
import org.eclipse.collections.api.set.primitive.ImmutableLongSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.immutable.primitive.ImmutableDoubleSetFactoryImpl;
import org.eclipse.collections.impl.set.immutable.primitive.ImmutableIntSetFactoryImpl;
import org.eclipse.collections.impl.set.immutable.primitive.ImmutableLongSetFactoryImpl;
import org.eclipse.collections.impl.set.sorted.immutable.ImmutableSortedSetFactoryImpl;

public final class ImmutableSets {

	private ImmutableSets() {
	}

	/**
	 * immutable int set
	 */
	public static ImmutableIntSetFactory immutableIntSetFactory() {
		return ImmutableIntSetFactoryImpl.INSTANCE;
	}

	public static ImmutableIntSet newImmutableIntSet(@Nonnull int... values) {
		if (isNullOrEmpty(values))
			return ImmutableIntSetFactoryImpl.INSTANCE.empty();
		return ImmutableIntSetFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * immutable long set
	 */
	public static ImmutableLongSetFactory immutableLongSetFactory() {
		return ImmutableLongSetFactoryImpl.INSTANCE;
	}

	public static ImmutableLongSet newImmutableLongSet(@Nonnull long... values) {
		if (isNullOrEmpty(values))
			return ImmutableLongSetFactoryImpl.INSTANCE.empty();
		return ImmutableLongSetFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * immutable double set
	 */
	public static ImmutableDoubleSetFactory immutableDoubleSetFactory() {
		return ImmutableDoubleSetFactoryImpl.INSTANCE;
	}

	public static ImmutableDoubleSet newImmutableDoubleSet(@Nonnull double... values) {
		if (isNullOrEmpty(values))
			return ImmutableDoubleSetFactoryImpl.INSTANCE.empty();
		return ImmutableDoubleSetFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * immutable set
	 */
	public static ImmutableSetFactory immutableSetFactory() {
		return ImmutableSetFactoryImpl.INSTANCE;
	}

	public static <E> ImmutableSet<E> newImmutableSet(@Nonnull Iterable<E> iterable) {
		if (iterable == null)
			return ImmutableSetFactoryImpl.INSTANCE.empty();
		return ImmutableSetFactoryImpl.INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableSet<E> newImmutableSet(@Nonnull E... values) {
		if (isNullOrEmpty(values))
			return ImmutableSetFactoryImpl.INSTANCE.empty();
		return ImmutableSetFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * immutable sorted set
	 */
	public static ImmutableSortedSetFactory immutableSortedSetFactory() {
		return ImmutableSortedSetFactoryImpl.INSTANCE;
	}

	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(@Nonnull Iterable<E> iterable) {
		if (iterable == null)
			return ImmutableSortedSetFactoryImpl.INSTANCE.empty();
		return ImmutableSortedSetFactoryImpl.INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(@Nonnull E... values) {
		if (isNullOrEmpty(values))
			return ImmutableSortedSetFactoryImpl.INSTANCE.empty();
		return ImmutableSortedSetFactoryImpl.INSTANCE.with(values);
	}

}
