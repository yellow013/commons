package io.ffreedom.common.queue;

import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PreloadingQueue<T> {

	private LinkedList<T> objs = new LinkedList<>();

	public T next() {
		return objs.removeFirst();
	}

	public void addLast(T content) {
		objs.addLast(content);
	}

	public boolean isEmpty() {
		return objs.isEmpty();
	}

	public boolean notEmpty() {
		return !isEmpty();
	}

	public Collection<T> getAllElement() {
		return objs;
	}

}
