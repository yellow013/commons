package io.ffreedom.common.collect;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;

public final class ImmutableLists {

	private ImmutableLists() {
	}

	/**
	 * immutable list
	 */
	public static <E> ImmutableList<E> newImmutableList(Iterable<E> items) {
		return ImmutableListFactoryImpl.INSTANCE.withAll(items);
	}

	@SafeVarargs
	public static <E> ImmutableList<E> newImmutableList(E... es) {
		return ImmutableListFactoryImpl.INSTANCE.with(es);
	}

}
