package io.ffreedom.common.collections;

import static org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl.INSTANCE;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;

import io.ffreedom.common.utils.ArrayUtil;

public final class ImmutableLists {

	private ImmutableLists() {
	}

	/**
	 * immutable list
	 */
	public static <E> ImmutableList<E> newImmutableList(Supplier<Iterable<E>> supplier) {
		if (supplier == null)
			return INSTANCE.empty();
		return newImmutableList(supplier.get());
	}

	public static <E> ImmutableList<E> newImmutableList(Iterable<E> iterable) {
		if (iterable == null)
			return INSTANCE.empty();
		return INSTANCE.withAll(iterable);
	}

	@SafeVarargs
	public static <E> ImmutableList<E> newImmutableList(E... values) {
		if (ArrayUtil.isNullOrEmpty(values))
			return INSTANCE.empty();
		return INSTANCE.with(values);
	}

}
