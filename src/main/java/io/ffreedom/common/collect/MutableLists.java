package io.ffreedom.common.collect;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

public final class MutableLists {

	private MutableLists() {
	}

	/**
	 * primitive list
	 */
	public static MutableLongList newLongArrayList() {
		return new LongArrayList();
	}

	public static MutableLongList newLongArrayList(int initialCapacity) {
		return new LongArrayList(initialCapacity);
	}

	public static MutableLongList newLongArrayListWith(long... longs) {
		return new LongArrayList(longs);
	}

	public static MutableDoubleList newDoubleArrayList() {
		return new DoubleArrayList();
	}

	public static MutableDoubleList newDoubleArrayList(int initialCapacity) {
		return new DoubleArrayList(initialCapacity);
	}

	public static MutableDoubleList newDoubleArrayListWith(double... doubles) {
		return new DoubleArrayList(doubles);
	}

	/**
	 * list
	 */
	public static <E> MutableList<E> newFastList() {
		return FastList.newList();
	}

	public static <E> MutableList<E> newFastList(int size) {
		return FastList.newList(size);
	}

	public static <E> MutableList<E> newFastList(Iterable<E> items) {
		return FastList.newList(items);
	}

}
