package io.ffreedom.common.collect;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;

import io.ffreedom.common.utils.ArrayUtil;

public final class ImmutableLists {

	private ImmutableLists() {
	}

	/**
	 * immutable list
	 */
	public static <E> ImmutableList<E> newImmutableList(Supplier<Iterable<E>> supplier) {
		if (supplier == null)
			return ImmutableListFactoryImpl.INSTANCE.empty();
		return newImmutableList(supplier.get());
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
