package io.ffreedom.common.collections.limited;

import org.eclipse.collections.impl.list.mutable.FastList;

public final class LimitedFastList<E> extends LimitedList<FastList<E>, E> {

	public LimitedFastList(int capacity) {
		super(capacity);
	}

	@Override
	protected FastList<E> initList(int capacity) {
		return new FastList<>(capacity);
	}

}
