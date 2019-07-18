package io.ffreedom.common.queue.impl;

import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PreloadingQueue<E> {

	private LinkedList<E> objs = new LinkedList<>();

	public E next() {
		return objs.removeFirst();
	}

	public void addLast(E element) {
		objs.addLast(element);
	}

	public boolean isEmpty() {
		return objs.isEmpty();
	}

	public boolean notEmpty() {
		return !isEmpty();
	}

	public Collection<E> getAllElement() {
		return objs;
	}

}
