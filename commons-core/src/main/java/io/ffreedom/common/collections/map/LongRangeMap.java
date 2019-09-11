package io.ffreedom.common.collections.map;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

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
@ThreadSafe
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

	public synchronized LongRangeMap<V> put(long key, V value) {
		savedMap.put(key, value);
		savedKey.add(key);
		return this;
	}

	@Nullable
	public V get(long key) {
		return savedMap.get(key);
	}

	@Nullable
	public synchronized V remove(long key) {
		savedKey.remove(key);
		return savedMap.remove(key);
	}

	public MutableList<V> getAll() {
		return MutableLists.newFastList(savedMap.values());
	}

	public synchronized void clear() {
		savedMap.clear();
		savedKey.clear();
	}

	public synchronized MutableList<V> scan(long startPoint, long endPoint) {
		MutableLongSet selectKey = selectKey(startPoint, endPoint);
		MutableList<V> selected = MutableLists.newFastList(selectKey.size());
		operatingSelect(selectKey, key -> selected.add(get(key)));
		return selected;
	}

	public synchronized MutableList<V> remove(long startPoint, long endPoint) {
		MutableLongSet selectKey = selectKey(startPoint, endPoint);
		MutableList<V> removed = MutableLists.newFastList(selectKey.size());
		operatingSelect(selectKey, key -> removed.add(remove(key)));
		return removed;
	}

	// TODO 性能优化
	public MutableLongSet selectKey(long startPoint, long endPoint) {
//		MutableLongIterator longIterator = savedKey.longIterator();
//		MutableLongSet longHashSet = MutableSets.newLongHashSet(512);
//		while (longIterator.hasNext()) {
//			long next = longIterator.next();
//			if (next >= startPoint && next <= endPoint)
//				longHashSet.add(next);
//		}
//		return longHashSet;
		return savedKey.select(key -> key >= startPoint && key <= endPoint, MutableSets.newLongHashSet(64));
	}

	private void operatingSelect(MutableLongSet selectKey, LongProcedure func) {
		if (!selectKey.isEmpty())
			selectKey.each(key -> func.accept(key));
	}

	public static void main(String[] args) {

		long startNano = System.nanoTime();
		LongRangeMap<String> longRangeMap = new LongRangeMap<>(200000);
		for (long l = 0L; l < 10000L; l++) {
			longRangeMap.put(l, "l == " + l);
		}
		long endNano = System.nanoTime();
		System.out.println((endNano - startNano) / 1000000);

		for (int i = 0; i < 200; i++) {
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

}
