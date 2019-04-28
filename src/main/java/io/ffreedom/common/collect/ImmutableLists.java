package io.ffreedom.common.collect;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.utility.Iterate;

import io.ffreedom.common.utils.ArrayUtil;

public final class ImmutableLists {

	private ImmutableLists() {
	}

	/**
	 * immutable list
	 */
	public static <E> ImmutableList<E> newImmutableList(Iterable<E> iterable) {
		if (Iterate.isEmpty(iterable))
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
