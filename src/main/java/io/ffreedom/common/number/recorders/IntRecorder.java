package io.ffreedom.common.number.recorders;

import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.common.collect.MutableSets;

public final class IntRecorder {

	private MutableIntIntMap intCounter;
	private MutableIntSet intSet;

	public IntRecorder(int size) {
		this.intCounter = MutableMaps.newIntIntHashMap(size);
		this.intSet = MutableSets.newIntHashSet(size);
	}

	public void put(int value) {
		int count = intCounter.get(value);
		if (count == 0)
			intSet.add(value);
		intCounter.put(value, ++count);
	}

	public void remove(int value) {
		int count = intCounter.get(value);
		if (count == 0)
			return;
		if (count == 1) {
			intSet.remove(value);
			intCounter.remove(value);
		} else
			intCounter.put(value, --count);
	}

	public int max() {
		return intSet.maxIfEmpty(Integer.MIN_VALUE);
	}

	public int min() {
		return intSet.minIfEmpty(Integer.MAX_VALUE);
	}

}
