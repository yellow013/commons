package io.ffreedom.common.collect.limited;

import org.eclipse.collections.impl.list.mutable.FastList;

public final class FastLimitedList<E> extends LimitedList<FastList<E>, E> {

	public FastLimitedList(int capacity) {
		super(capacity);
	}

	@Override
	protected FastList<E> initList(int capacity) {
		return new FastList<>(capacity);
	}

}
