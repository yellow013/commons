package io.ffreedom.common.collect;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

import io.ffreedom.common.utils.ArrayUtil;

public final class MutableLists {

	private MutableLists() {
	}

	/**
	 * primitive list
	 */
	public static MutableIntList newIntArrayList() {
		return new IntArrayList();
	}

	public static MutableIntList newIntArrayList(int initialCapacity) {
		return new IntArrayList(initialCapacity);
	}

	public static MutableIntList newIntArrayListWith(int... intValues) {
		if (ArrayUtil.isNullOrEmpty(intValues))
			return newIntArrayList();
		return new IntArrayList(intValues);
	}

	public static MutableLongList newLongArrayList() {
		return new LongArrayList();
	}

	public static MutableLongList newLongArrayList(int initialCapacity) {
		return new LongArrayList(initialCapacity);
	}

	public static MutableLongList newLongArrayListWith(long... longValues) {
		if (ArrayUtil.isNullOrEmpty(longValues))
			return newLongArrayList();
		return new LongArrayList(longValues);
	}

	public static MutableDoubleList newDoubleArrayList() {
		return new DoubleArrayList();
	}

	public static MutableDoubleList newDoubleArrayList(int initialCapacity) {
		return new DoubleArrayList(initialCapacity);
	}

	public static MutableDoubleList newDoubleArrayListWith(double... doubleValues) {
		if (ArrayUtil.isNullOrEmpty(doubleValues))
			return newDoubleArrayList();
		return new DoubleArrayList(doubleValues);
	}

	/**
	 * list
	 */
	public static <E> MutableList<E> newFastList() {
		return new FastList<>();
	}

	public static <E> MutableList<E> newFastList(int initialCapacity) {
		return new FastList<>(initialCapacity);
	}

	public static <E> MutableList<E> newFastList(Supplier<Iterable<E>> supplier) {
		if (supplier == null)
			return newFastList();
		return newFastList(supplier.get());
	}

	public static <E> MutableList<E> newFastList(Iterable<E> iterable) {
		if (iterable == null)
			return newFastList();
		return FastList.newList(iterable);
	}

	@SafeVarargs
	public static <E> MutableList<E> newFastList(E... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return newFastList();
		return FastList.newListWith(values);
	}

}
