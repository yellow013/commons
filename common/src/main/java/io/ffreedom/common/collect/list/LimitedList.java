package io.ffreedom.common.collect.list;

import java.util.List;

public abstract class LimitedList<L extends List<E>, E> {

	private L innerList;

	private int head = 0;
	private int tail = 0;
	private int count = 0;

	private int capacity;

	public LimitedList(int capacity) {
		this.capacity = capacity;
		this.innerList = initList(capacity);
	}

	protected abstract L initList(int capacity);

	public void addTail(E e) {
		updateTail();
		innerList.set(tail, e);
		updateHead();
		updateCount();
	}

	public E getTail() {
		return innerList.get(tail);
	}

	public E getHead() {
		return innerList.get(head);
	}

	private void updateTail() {
		if (++tail == capacity) {
			tail = 0;
		}
	}

	private void updateHead() {
		if (count == capacity) {
			if (++head == capacity) {
				head = 0;
			}
		}
	}

	private void updateCount() {
		if (count < capacity) {
			count++;
		}
	}

	public int getCount() {
		return count;
	}

}
