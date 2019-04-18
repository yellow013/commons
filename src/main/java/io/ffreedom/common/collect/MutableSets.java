package io.ffreedom.common.collect;

import java.util.Collection;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.primitive.DoubleHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public final class MutableSets {

	private MutableSets() {
	}

	/**
	 * primitive set
	 */
	public static MutableIntSet newIntHashSet() {
		return new IntHashSet();
	}

	public static MutableIntSet newIntHashSet(int size) {
		return new IntHashSet(size);
	}

	public static MutableIntSet newIntHashSetWith(int... ints) {
		return new IntHashSet(ints);
	}

	public static MutableDoubleSet newDoubleHashSet() {
		return new DoubleHashSet();
	}

	public static MutableDoubleSet newDoubleHashSet(int size) {
		return new DoubleHashSet(size);
	}

	public static MutableDoubleSet newDoubleHashSetWith(double... doubles) {
		return new DoubleHashSet(doubles);
	}

	/**
	 * set
	 */
	public static <E> MutableSet<E> newUnifiedSet() {
		return UnifiedSet.newSet();
	}

	public static <E> MutableSet<E> newUnifiedSet(int size) {
		return UnifiedSet.newSet(size);
	}

	public static <E> MutableSet<E> newUnifiedSet(Collection<E> collection) {
		return new UnifiedSet<>(collection);
	}

	public static <E> MutableSortedSet<E> newTreeSortedSet() {
		return TreeSortedSet.newSet();
	}

	public static <E> MutableSortedSet<E> newTreeSortedSet(Iterable<E> items) {
		return TreeSortedSet.newSet(items);
	}

	public static MutableLongSet newLongHashSet() {
		return new LongHashSet();
	}

	public static MutableLongSet newLongHashSet(int size) {
		return new LongHashSet(size);
	}

}
