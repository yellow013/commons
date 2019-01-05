package io.ffreedom.common.cache.heap;

import org.eclipse.collections.api.map.primitive.MutableDoubleIntMap;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;

import io.ffreedom.common.collect.ECollections;

public final class DoubleRecorder {

	private MutableDoubleIntMap doubleCounter;
	private MutableDoubleSet doubleSet;

	public DoubleRecorder(int size) {
		this.doubleCounter = ECollections.newDoubleIntHashMap(size);
		this.doubleSet = ECollections.newDoubleHashSet(size);
	}

	public void put(double value) {
		int count = doubleCounter.get(value);
		if (count == 0)
			doubleSet.add(value);
		doubleCounter.put(value, ++count);
	}

	public void remove(double value) {
		int count = doubleCounter.get(value);
		if (count == 0)
			return;
		if (count == 1) {
			doubleSet.remove(value);
			doubleCounter.remove(value);
		} else
			doubleCounter.put(value, --count);
	}

	public double max() {
		return doubleSet.maxIfEmpty(Double.MIN_VALUE);
	}

	public double min() {
		return doubleSet.minIfEmpty(Double.MAX_VALUE);
	}

}
