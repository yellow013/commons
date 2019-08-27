package io.ffreedom.common.collections.map;

import javax.annotation.Nullable;

import org.eclipse.collections.api.block.procedure.primitive.LongProcedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.primitive.MutableLongSet;

import io.ffreedom.common.collections.MutableLists;
import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.collections.MutableSets;

/**
 *
 * @author yellow013
 *
 * @param <V>
 */

public final class LongRangeMap<V> {

	private MutableLongObjectMap<V> savedMap;
	private MutableLongSet savedKey;

	public LongRangeMap() {
		this(64);
	}

	public LongRangeMap(int initialCapacity) {
		this.savedMap = MutableMaps.newLongObjectHashMap(initialCapacity);
		this.savedKey = MutableSets.newLongHashSet(initialCapacity);

	}

	public LongRangeMap<V> put(long key, V value) {
		savedMap.put(key, value);
		savedKey.add(key);
		return this;
	}

	@Nullable
	public V get(long key) {
		return savedMap.get(key);
	}

	@Nullable
	public V remove(long key) {
		savedKey.remove(key);
		return savedMap.remove(key);
	}

	public MutableList<V> getAll() {
		return MutableLists.newFastList(savedMap.values());
	}

	public void clear() {
		savedMap.clear();
		savedKey.clear();
	}

	public MutableList<V> scan(long startPoint, long endPoint) {
		MutableLongSet selectKey = selectKey(startPoint, endPoint);
		MutableList<V> selected = MutableLists.newFastList(selectKey.size());
		operatingSelect(selectKey, key -> selected.add(savedMap.get(key)));
		return selected;
	}

	public MutableList<V> remove(long startPoint, long endPoint) {
		MutableLongSet selectKey = selectKey(startPoint, endPoint);
		MutableList<V> removed = MutableLists.newFastList(selectKey.size());
		operatingSelect(selectKey, key -> removed.add(savedMap.remove(key)));
		return removed;
	}

	// TODO 性能优化
	public MutableLongSet selectKey(long startPoint, long endPoint) {
		return savedKey.select(key -> key >= startPoint && key <= endPoint, MutableSets.newLongHashSet(1000));
	}

	private void operatingSelect(MutableLongSet selectKey, LongProcedure func) {
		if (!selectKey.isEmpty())
			selectKey.each(key -> func.accept(key));
	}

	public static void main(String[] args) {

		long startNano = System.nanoTime();
		LongRangeMap<String> longRangeMap = new LongRangeMap<>(20000000);
		for (long l = 0L; l < 10000L; l++) {
			longRangeMap.put(l, "l == " + l);
		}
		long endNano = System.nanoTime();
		System.out.println((endNano - startNano) / 1000000);

		long startNano1 = System.nanoTime();
		for (long l = 1000L; l < 2000L; l++) {
			@SuppressWarnings("unused")
			MutableLongSet selectKey = longRangeMap.selectKey(0, l);
			// longRangeMap.get(1000, l);
		}
		long endNano1 = System.nanoTime();
		System.out.println((endNano1 - startNano1) / 1000000);

	}

}
