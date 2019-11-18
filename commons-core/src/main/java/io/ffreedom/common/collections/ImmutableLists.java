package io.ffreedom.common.collections;

import org.eclipse.collections.api.factory.list.ImmutableListFactory;
import org.eclipse.collections.api.factory.list.primitive.ImmutableIntListFactory;
import org.eclipse.collections.api.factory.list.primitive.ImmutableLongListFactory;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.list.immutable.primitive.ImmutableIntListFactoryImpl;
import org.eclipse.collections.impl.list.immutable.primitive.ImmutableLongListFactoryImpl;

import io.ffreedom.common.utils.ArrayUtil;

public final class ImmutableLists {

	private ImmutableLists() {
	}

	/**
	 * 
	 * immutable int list
	 */
	public static ImmutableIntListFactory immutableIntListFactory() {
		return ImmutableIntListFactoryImpl.INSTANCE;
	}

	public static ImmutableIntList newImmutableIntList(int... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return ImmutableIntListFactoryImpl.INSTANCE.empty();
		return ImmutableIntListFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * 
	 * immutable long list
	 */
	public static ImmutableLongListFactory immutableLongListFactory() {
		return ImmutableLongListFactoryImpl.INSTANCE;
	}

	public static ImmutableLongList newImmutableLongList(long... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return ImmutableLongListFactoryImpl.INSTANCE.empty();
		return ImmutableLongListFactoryImpl.INSTANCE.with(values);
	}

	/**
	 * 
	 * immutable list
	 */
	public static ImmutableListFactory immutableListFactory() {
		return ImmutableListFactoryImpl.INSTANCE;
	}

	public static <E> ImmutableList<E> newImmutableList(Iterable<E> iterable) {
		if (iterable == null)
			return ImmutableListFactoryImpl.INSTANCE.empty();
		return ImmutableListFactoryImpl.INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableList<E> newImmutableList(E... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return ImmutableListFactoryImpl.INSTANCE.empty();
		return ImmutableListFactoryImpl.INSTANCE.with(values);
	}

}
