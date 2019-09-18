package io.ffreedom.common.collections.set;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.number.counters.SyncDeRepeatCounter;

public final class DeRepeatCounterSet<T> {

	private SyncDeRepeatCounter<T> counter = new SyncDeRepeatCounter<>();
	private MutableIntObjectMap<SyncDeRepeatCounter<T>> groupCounter = MutableMaps.newIntObjectHashMap();

	public SyncDeRepeatCounter<T> getCounter() {
		return counter;
	}

	public synchronized SyncDeRepeatCounter<T> getCounterByGroup(int groupId) {
		SyncDeRepeatCounter<T> counter = groupCounter.get(groupId);
		if (counter == null) {
			counter = new SyncDeRepeatCounter<>();
			groupCounter.put(groupId, counter);
		}
		return counter;
	}

	public void putEvent(int groupId, T event) {
		counter.add(event);
		getCounterByGroup(groupId).add(event);
	}

}
