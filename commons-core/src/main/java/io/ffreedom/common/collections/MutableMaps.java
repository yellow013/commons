package io.ffreedom.common.collections;

import java.util.Comparator;
import java.util.Map;

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

import io.ffreedom.common.utils.ArrayUtil;

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

	public static MutableIntIntMap newIntIntHashMap(int initialCapacity) {
		return new IntIntHashMap(initialCapacity);
	}

	public static MutableIntLongMap newIntLongHashMap() {
		return new IntLongHashMap();
	}

	public static MutableIntLongMap newIntLongHashMap(int initialCapacity) {
		return new IntLongHashMap(initialCapacity);
	}

	public static MutableIntDoubleMap newIntDoubleHashMap() {
		return new IntDoubleHashMap();
	}

	public static MutableIntDoubleMap newIntDoubleHashMap(int initialCapacity) {
		return new IntDoubleHashMap(initialCapacity);
	}

	public static MutableIntBooleanMap newIntBooleanHashMap() {
		return new IntBooleanHashMap();
	}

	public static MutableIntBooleanMap newIntBooleanHashMap(int initialCapacity) {
		return new IntBooleanHashMap(initialCapacity);
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap() {
		return new IntObjectHashMap<>();
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap(int initialCapacity) {
		return new IntObjectHashMap<>(initialCapacity);
	}

	// Key -> long
	public static MutableLongLongMap newLongLongHashMap() {
		return new LongLongHashMap();
	}

	public static MutableLongLongMap newLongLongHashMap(int initialCapacity) {
		return new LongLongHashMap(initialCapacity);
	}

	public static MutableLongIntMap newLongIntHashMap() {
		return new LongIntHashMap();
	}

	public static MutableLongIntMap newLongIntHashMap(int initialCapacity) {
		return new LongIntHashMap(initialCapacity);
	}

	public static MutableLongDoubleMap newLongDoubleHashMap() {
		return new LongDoubleHashMap();
	}

	public static MutableLongDoubleMap newLongDoubleHashMap(int initialCapacity) {
		return new LongDoubleHashMap(initialCapacity);
	}

	public static MutableLongBooleanMap newLongBooleanHashMap() {
		return new LongBooleanHashMap();
	}

	public static MutableLongBooleanMap newLongBooleanHashMap(int initialCapacity) {
		return new LongBooleanHashMap(initialCapacity);
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return new LongObjectHashMap<>();
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap(int initialCapacity) {
		return new LongObjectHashMap<>(initialCapacity);
	}

	// Key -> double
	public static MutableDoubleBooleanMap newDoubleBooleanHashMap() {
		return new DoubleBooleanHashMap();
	}

	public static MutableDoubleBooleanMap newDoubleBooleanHashMap(int initialCapacity) {
		return new DoubleBooleanHashMap(initialCapacity);
	}

	public static MutableDoubleIntMap newDoubleIntHashMap() {
		return new DoubleIntHashMap();
	}

	public static MutableDoubleIntMap newDoubleIntHashMap(int initialCapacity) {
		return new DoubleIntHashMap(initialCapacity);
	}

	public static MutableDoubleLongMap newDoubleLongHashMap() {
		return new DoubleLongHashMap();
	}

	public static MutableDoubleLongMap newDoubleLongHashMap(int initialCapacity) {
		return new DoubleLongHashMap(initialCapacity);
	}

	// Key -> Object
	public static <K> MutableObjectBooleanMap<K> newObjectBooleanHashMap() {
		return new ObjectBooleanHashMap<>();
	}

	public static <K> MutableObjectBooleanMap<K> newObjectBooleanHashMap(int initialCapacity) {
		return new ObjectBooleanHashMap<>(initialCapacity);
	}

	public static <K> MutableObjectIntMap<K> newObjectIntHashMap() {
		return new ObjectIntHashMap<>();
	}

	public static <K> MutableObjectIntMap<K> newObjectIntHashMap(int initialCapacity) {
		return new ObjectIntHashMap<>(initialCapacity);
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap() {
		return new ObjectLongHashMap<>();
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap(int initialCapacity) {
		return new ObjectLongHashMap<>(initialCapacity);
	}

	public static <K> MutableObjectDoubleMap<K> newObjectDoubleHashMap() {
		return new ObjectDoubleHashMap<>();
	}

	public static <K> MutableObjectDoubleMap<K> newObjectDoubleHashMap(int initialCapacity) {
		return new ObjectDoubleHashMap<>(initialCapacity);
	}

	/**
	 * map
	 */
	public static <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(int size) {
		return UnifiedMap.newMap(size);
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return newUnifiedMap();
		return UnifiedMap.newMap(map);
	}

	/**
	 * concurrent map
	 */
	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap() {
		return ConcurrentHashMap.newMap();
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(int size) {
		return ConcurrentHashMap.newMap(size);
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return newConcurrentHashMap();
		return ConcurrentHashMap.newMap(map);
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe() {
		return ConcurrentHashMapUnsafe.newMap();
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMapUnsafe(int size) {
		return ConcurrentHashMapUnsafe.newMap(size);
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
