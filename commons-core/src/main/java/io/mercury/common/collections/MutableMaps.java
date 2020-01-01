package io.mercury.common.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableDoubleBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableDoubleIntMap;
import org.eclipse.collections.api.map.primitive.MutableDoubleLongMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntLongMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableObjectDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMapUnsafe;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.DoubleBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.DoubleIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.DoubleLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.multimap.set.sorted.TreeSortedSetMultimap;

import io.mercury.common.util.ArrayUtil;

public final class MutableMaps {

	private MutableMaps() {
	}

	/**
	 * primitive map
	 */

	// Key -> int
	public static MutableIntIntMap newIntIntHashMap() {
		return new IntIntHashMap();
	}

	public static MutableIntIntMap newIntIntHashMap(Capacity capacity) {
		return new IntIntHashMap(capacity.size());
	}

	public static MutableIntIntMap newIntIntHashMap(int capacity) {
		return new IntIntHashMap(capacity);
	}

	public static MutableIntLongMap newIntLongHashMap() {
		return new IntLongHashMap();
	}

	public static MutableIntLongMap newIntLongHashMap(Capacity capacity) {
		return new IntLongHashMap(capacity.size());
	}

	public static MutableIntLongMap newIntLongHashMap(int capacity) {
		return new IntLongHashMap(capacity);
	}

	public static MutableIntDoubleMap newIntDoubleHashMap() {
		return new IntDoubleHashMap();
	}

	public static MutableIntDoubleMap newIntDoubleHashMap(Capacity capacity) {
		return new IntDoubleHashMap(capacity.size());
	}

	public static MutableIntDoubleMap newIntDoubleHashMap(int capacity) {
		return new IntDoubleHashMap(capacity);
	}

	public static MutableIntBooleanMap newIntBooleanHashMap() {
		return new IntBooleanHashMap();
	}

	public static MutableIntBooleanMap newIntBooleanHashMap(Capacity capacity) {
		return new IntBooleanHashMap(capacity.size());
	}

	public static MutableIntBooleanMap newIntBooleanHashMap(int capacity) {
		return new IntBooleanHashMap(capacity);
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap() {
		return new IntObjectHashMap<>();
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap(Capacity capacity) {
		return new IntObjectHashMap<>(capacity.size());
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap(int capacity) {
		return new IntObjectHashMap<>(capacity);
	}

	// Key -> long
	public static MutableLongLongMap newLongLongHashMap() {
		return new LongLongHashMap();
	}

	public static MutableLongLongMap newLongLongHashMap(Capacity capacity) {
		return new LongLongHashMap(capacity.size());
	}

	public static MutableLongLongMap newLongLongHashMap(int capacity) {
		return new LongLongHashMap(capacity);
	}

	public static MutableLongIntMap newLongIntHashMap() {
		return new LongIntHashMap();
	}

	public static MutableLongIntMap newLongIntHashMap(Capacity capacity) {
		return new LongIntHashMap(capacity.size());
	}

	public static MutableLongIntMap newLongIntHashMap(int capacity) {
		return new LongIntHashMap(capacity);
	}

	public static MutableLongDoubleMap newLongDoubleHashMap() {
		return new LongDoubleHashMap();
	}

	public static MutableLongDoubleMap newLongDoubleHashMap(Capacity capacity) {
		return new LongDoubleHashMap(capacity.size());
	}

	public static MutableLongDoubleMap newLongDoubleHashMap(int capacity) {
		return new LongDoubleHashMap(capacity);
	}

	public static MutableLongBooleanMap newLongBooleanHashMap() {
		return new LongBooleanHashMap();
	}

	public static MutableLongBooleanMap newLongBooleanHashMap(Capacity capacity) {
		return new LongBooleanHashMap(capacity.size());
	}

	public static MutableLongBooleanMap newLongBooleanHashMap(int capacity) {
		return new LongBooleanHashMap(capacity);
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return new LongObjectHashMap<>();
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap(Capacity capacity) {
		return new LongObjectHashMap<>(capacity.size());
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap(int capacity) {
		return new LongObjectHashMap<>(capacity);
	}

	// Key -> double
	public static MutableDoubleBooleanMap newDoubleBooleanHashMap() {
		return new DoubleBooleanHashMap();
	}

	public static MutableDoubleBooleanMap newDoubleBooleanHashMap(Capacity capacity) {
		return new DoubleBooleanHashMap(capacity.size());
	}

	public static MutableDoubleBooleanMap newDoubleBooleanHashMap(int capacity) {
		return new DoubleBooleanHashMap(capacity);
	}

	public static MutableDoubleIntMap newDoubleIntHashMap() {
		return new DoubleIntHashMap();
	}

	public static MutableDoubleIntMap newDoubleIntHashMap(Capacity capacity) {
		return new DoubleIntHashMap(capacity.size());
	}

	public static MutableDoubleIntMap newDoubleIntHashMap(int capacity) {
		return new DoubleIntHashMap(capacity);
	}

	public static MutableDoubleLongMap newDoubleLongHashMap() {
		return new DoubleLongHashMap();
	}

	public static MutableDoubleLongMap newDoubleLongHashMap(Capacity capacity) {
		return new DoubleLongHashMap(capacity.size());
	}

	public static MutableDoubleLongMap newDoubleLongHashMap(int capacity) {
		return new DoubleLongHashMap(capacity);
	}

	// Key -> Object
	public static <K> MutableObjectBooleanMap<K> newObjectBooleanHashMap() {
		return new ObjectBooleanHashMap<>();
	}

	public static <K> MutableObjectBooleanMap<K> newObjectBooleanHashMap(Capacity capacity) {
		return new ObjectBooleanHashMap<>(capacity.size());
	}

	public static <K> MutableObjectBooleanMap<K> newObjectBooleanHashMap(int capacity) {
		return new ObjectBooleanHashMap<>(capacity);
	}

	public static <K> MutableObjectIntMap<K> newObjectIntHashMap() {
		return new ObjectIntHashMap<>();
	}

	public static <K> MutableObjectIntMap<K> newObjectIntHashMap(Capacity capacity) {
		return new ObjectIntHashMap<>(capacity.size());
	}

	public static <K> MutableObjectIntMap<K> newObjectIntHashMap(int capacity) {
		return new ObjectIntHashMap<>(capacity);
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap() {
		return new ObjectLongHashMap<>();
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap(Capacity capacity) {
		return new ObjectLongHashMap<>(capacity.size());
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap(int capacity) {
		return new ObjectLongHashMap<>(capacity);
	}

	public static <K> MutableObjectDoubleMap<K> newObjectDoubleHashMap() {
		return new ObjectDoubleHashMap<>();
	}

	public static <K> MutableObjectDoubleMap<K> newObjectDoubleHashMap(Capacity capacity) {
		return new ObjectDoubleHashMap<>(capacity.size());
	}

	public static <K> MutableObjectDoubleMap<K> newObjectDoubleHashMap(int capacity) {
		return new ObjectDoubleHashMap<>(capacity);
	}

	/**
	 * map
	 */
	public static <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(Capacity capacity) {
		return UnifiedMap.newMap(capacity.size());
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(int capacity) {
		return UnifiedMap.newMap(capacity);
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return newUnifiedMap();
		return UnifiedMap.newMap(map);
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(Supplier<Map<K, V>> supplier) {
		if (supplier == null)
			return newUnifiedMap();
		return newUnifiedMap(supplier.get());
	}

	/**
	 * concurrent map
	 */
	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap() {
		return ConcurrentHashMap.newMap();
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(Capacity capacity) {
		return ConcurrentHashMap.newMap(capacity.size());
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(int capacity) {
		return ConcurrentHashMap.newMap(capacity);
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return newConcurrentHashMap();
		return ConcurrentHashMap.newMap(map);
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe() {
		return ConcurrentHashMapUnsafe.newMap();
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe(Capacity capacity) {
		return ConcurrentHashMapUnsafe.newMap(capacity.size());
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe(int capacity) {
		return ConcurrentHashMapUnsafe.newMap(capacity);
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return newConcurrentHashMapUnsafe();
		return ConcurrentHashMapUnsafe.newMap(map);
	}

	/**
	 * multimap
	 */
	public static <K, V> MutableListMultimap<K, V> newFastListMultimap() {
		return FastListMultimap.newMultimap();
	}

	public static <K, V> MutableListMultimap<K, V> newFastListMultimap(Iterable<Pair<K, V>> iterable) {
		if (iterable == null)
			return newFastListMultimap();
		return FastListMultimap.newMultimap(iterable);
	}

	@SafeVarargs
	public static <K, V> MutableListMultimap<K, V> newFastListMultimap(Pair<K, V>... pairs) {
		if (ArrayUtil.isNullOrEmpty(pairs))
			return newFastListMultimap();
		return FastListMultimap.newMultimap(pairs);
	}

	public static <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap() {
		return UnifiedSetMultimap.newMultimap();
	}

	public static <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap(Iterable<Pair<K, V>> iterable) {
		if (iterable == null)
			return newUnifiedSetMultimap();
		return UnifiedSetMultimap.newMultimap(iterable);
	}

	@SafeVarargs
	public static <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap(Pair<K, V>... pairs) {
		if (ArrayUtil.isNullOrEmpty(pairs))
			return newUnifiedSetMultimap();
		return UnifiedSetMultimap.newMultimap(pairs);
	}

	public static <K, V> MutableSortedSetMultimap<K, V> newTreeSortedSetMultimap() {
		return TreeSortedSetMultimap.newMultimap();
	}

	public static <K, V> MutableSortedSetMultimap<K, V> newTreeSortedSetMultimap(Iterable<Pair<K, V>> iterable) {
		if (iterable == null)
			return newTreeSortedSetMultimap();
		return TreeSortedSetMultimap.newMultimap(iterable);
	}

	public static <K, V> MutableSortedSetMultimap<K, V> newTreeSortedSetMultimap(Comparator<V> comparator) {
		if (comparator == null)
			return newTreeSortedSetMultimap();
		return TreeSortedSetMultimap.newMultimap(comparator);
	}

	@SafeVarargs
	public static <K, V> MutableSortedSetMultimap<K, V> newTreeSortedSetMultimap(Pair<K, V>... pairs) {
		if (ArrayUtil.isNullOrEmpty(pairs))
			return newTreeSortedSetMultimap();
		return TreeSortedSetMultimap.newMultimap(pairs);
	}

}
