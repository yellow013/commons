package io.ffreedom.common.queue;

import java.util.Collection;

import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public class PriorityDoubleQueue<T> {

	private MutableSortedSet<T> prioritySet = TreeSortedSet.newSet();
	private MutableSortedSet<T> secondarySet = TreeSortedSet.newSet();

	private int waitSignal = 0;

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
		return prioritySet.isEmpty() || secondarySet.isEmpty();
	}

	public T next() {
		if (!prioritySet.isEmpty()) {
			T firstPriority = prioritySet.first();
			calculateWaitSignal(firstPriority);
			if (waitSignal == 0) {
				return firstPriority;
			} else {
				return secondarySet.first();
			}
		} else {
			if (!secondarySet.isEmpty()) {
				return secondarySet.first();
			}
		}
		return null;
	}

	private void calculateWaitSignal(T firstPriority) {
		MutableSortedSet<T> select = secondarySet.select(secondary -> secondary != firstPriority);
		waitSignal = select.size();
	}

}
