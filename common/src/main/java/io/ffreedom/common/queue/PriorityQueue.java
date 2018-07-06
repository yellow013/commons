package io.ffreedom.common.queue;

import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

@NotThreadSafe
public class PriorityQueue<T> {

	private MutableSortedSet<T> prioritySet = TreeSortedSet.newSet();
	private MutableSortedSet<T> secondarySet = TreeSortedSet.newSet();

	public boolean addAllToPrioritySet(Collection<T> collection) {
		return prioritySet.addAll(collection);
	}

	public boolean addToPrioritySet(T t) {
		return prioritySet.add(t);
	}

	public boolean addAllToSecondarySet(Collection<T> collection) {
		return secondarySet.addAll(collection);
	}

	public boolean addToSecondarySet(T t) {
		return secondarySet.add(t);
	}

	public boolean isEmpty() {
		return prioritySet.notEmpty() || secondarySet.notEmpty();
	}

	public T next() {
		return prioritySet.notEmpty() ? prioritySet.first() : secondarySet.notEmpty() ? secondarySet.first() : null;
	}

}
