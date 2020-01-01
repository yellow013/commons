package io.mercury.common.collections;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableByteList;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

import io.mercury.common.util.ArrayUtil;

public final class MutableLists {

	private MutableLists() {
	}

	/**
	 * primitive list
	 */
	public static MutableByteList newByteArrayList() {
		return new ByteArrayList();
	}

	public static MutableByteList newByteArrayList(int initialCapacity) {
		return new ByteArrayList(initialCapacity);
	}

	public static MutableByteList newByteArrayListWith(byte... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return new ByteArrayList();
		return new ByteArrayList(values);
	}

	public static MutableCharList newCharArrayList() {
		return new CharArrayList();
	}

	public static MutableCharList newCharArrayList(int initialCapacity) {
		return new CharArrayList(initialCapacity);
	}

	public static MutableCharList newCharArrayList(char... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return new CharArrayList();
		return new CharArrayList(values);
	}

	public static MutableIntList newIntArrayList() {
		return new IntArrayList();
	}

	public static MutableIntList newIntArrayList(int initialCapacity) {
		return new IntArrayList(initialCapacity);
	}

	public static MutableIntList newIntArrayListWith(int... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return newIntArrayList();
		return new IntArrayList(values);
	}

	public static MutableLongList newLongArrayList() {
		return new LongArrayList();
	}

	public static MutableLongList newLongArrayList(int initialCapacity) {
		return new LongArrayList(initialCapacity);
	}

	public static MutableLongList newLongArrayListWith(long... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return newLongArrayList();
		return new LongArrayList(values);
	}

	public static MutableDoubleList newDoubleArrayList() {
		return new DoubleArrayList();
	}

	public static MutableDoubleList newDoubleArrayList(int initialCapacity) {
		return new DoubleArrayList(initialCapacity);
	}

	public static MutableDoubleList newDoubleArrayListWith(double... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return newDoubleArrayList();
		return new DoubleArrayList(values);
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

	public static <E> MutableList<E> newFastList(Collection<E> collection) {
		return new FastList<>(collection);
	}

	public static <E> MutableList<E> newFastList(Iterator<E> iterator) {
		MutableList<E> list = newFastList();
		if (iterator != null && iterator.hasNext())
			while (iterator.hasNext())
				list.add(iterator.next());
		return list;
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
