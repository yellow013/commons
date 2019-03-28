package io.ffreedom.common.queue.impl;

import java.util.Collection;
import java.util.Optional;

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
		return prioritySet.isEmpty() && secondarySet.isEmpty();
	}

	public Optional<T> next() {
		return prioritySet.notEmpty() ? Optional.of(prioritySet.first())
				: secondarySet.notEmpty() ? Optional.of(secondarySet.first()) : Optional.empty();
	}

}
