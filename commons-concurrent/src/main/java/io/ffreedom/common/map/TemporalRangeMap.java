package io.ffreedom.common.map;

import java.time.temporal.Temporal;
import java.util.List;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.collections.MutableSets;

public class DatetimeRangeMap<V> {

	private MutableMap<Temporal, V> savedMap = MutableMaps.newUnifiedMap(256);

	private MutableSet<Temporal> timeSet = MutableSets.newUnifiedSet();

	public List<V> get(Temporal startTime, Temporal endTime) {

		MutableSet<Temporal> select = timeSet.select(datetime -> startTime.equals(datetime));
		return null;
	}

}
