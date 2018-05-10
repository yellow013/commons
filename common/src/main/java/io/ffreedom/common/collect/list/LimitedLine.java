package io.ffreedom.common.collect.list;

import java.util.List;

public abstract class LimitedLine<E> {

	private List<E> innerList;

	private int head = 0;
	private int tail = 0;
	private int count = 0;

	private int capacity;

	public LimitedLine(int capacity) {
		this.capacity = capacity;
		this.innerList = initList(capacity);
	}

	protected abstract List<E> initList(int capacity);

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
