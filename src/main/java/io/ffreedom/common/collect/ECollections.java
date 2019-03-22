package io.ffreedom.common.collect;

import java.util.Collection;
import java.util.Map;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableDoubleIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableDoubleSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.DoubleIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.primitive.DoubleHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.set.sorted.immutable.ImmutableSortedSetFactoryImpl;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public final class ECollections {

	private ECollections() {
	}

	/**
	 * primitive map
	 */
	public static MutableLongLongMap newLongLongHashMap() {
		return new LongLongHashMap();
	}

	public static MutableLongLongMap newLongLongHashMap(int size) {
		return new LongLongHashMap(size);
	}

	public static MutableLongIntMap newLongIntHashMap() {
		return new LongIntHashMap();
	}

	public static MutableLongIntMap newLongIntHashMap(int size) {
		return new LongIntHashMap(size);
	}

	public static MutableIntDoubleMap newIntDoubleHashMap() {
		return new IntDoubleHashMap();
	}

	public static MutableIntDoubleMap newIntDoubleHashMap(int size) {
		return new IntDoubleHashMap(size);
	}

	public static MutableIntIntMap newIntIntHashMap() {
		return new IntIntHashMap();
	}

	public static MutableIntIntMap newIntIntHashMap(int size) {
		return new IntIntHashMap(size);
	}

	public static MutableLongDoubleMap newLongDoubleHashMap() {
		return new LongDoubleHashMap();
	}

	public static MutableLongDoubleMap newLongDoubleHashMap(int size) {
		return new LongDoubleHashMap(size);
	}

	public static MutableDoubleIntMap newDoubleIntHashMap() {
		return new DoubleIntHashMap();
	}

	public static MutableDoubleIntMap newDoubleIntHashMap(int size) {
		return new DoubleIntHashMap(size);
	}

	/**
	 * primitive list
	 */
	public static MutableLongList newLongArrayList() {
		return new LongArrayList();
	}

	public static MutableLongList newLongArrayList(int initialCapacity) {
		return new LongArrayList(initialCapacity);
	}

	public static MutableLongList newLongArrayListWith(long... longs) {
		return new LongArrayList(longs);
	}

	public static MutableDoubleList newDoubleArrayList() {
		return new DoubleArrayList();
	}

	public static MutableDoubleList newDoubleArrayList(int initialCapacity) {
		return new DoubleArrayList(initialCapacity);
	}

	public static MutableDoubleList newDoubleArrayListWith(double... doubles) {
		return new DoubleArrayList(doubles);
	}

	/**
	 * primitive set
	 */
	public static MutableIntSet newIntHashSet() {
		return new IntHashSet();
	}

	public static MutableIntSet newIntHashSet(int size) {
		return new IntHashSet(size);
	}

	public static MutableIntSet newIntHashSetWith(int... ints) {
		return new IntHashSet(ints);
	}

	public static MutableDoubleSet newDoubleHashSet() {
		return new DoubleHashSet();
	}

	public static MutableDoubleSet newDoubleHashSet(int size) {
		return new DoubleHashSet(size);
	}

	public static MutableDoubleSet newDoubleHashSetWith(double... doubles) {
		return new DoubleHashSet(doubles);
	}

	/**
	 * map
	 */
	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap() {
		return ConcurrentHashMap.newMap();
	}

	public static <K, V> ConcurrentMutableMap<K, V> newConcurrentHashMap(int size) {
		return ConcurrentHashMap.newMap(size);
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static <K, V> MutableMap<K, V> newUnifiedMap(int size) {
		return UnifiedMap.newMap(size);
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap() {
		return IntObjectHashMap.newMap();
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap(int size) {
		return new IntObjectHashMap<>(size);
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return LongObjectHashMap.newMap();
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap(int size) {
		return new LongObjectHashMap<>(size);
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap() {
		return new ObjectLongHashMap<>();
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap(int size) {
		return new ObjectLongHashMap<>(size);
	}

	/**
	 * set
	 */
	public static <E> MutableSet<E> newUnifiedSet() {
		return UnifiedSet.newSet();
	}

	public static <E> MutableSet<E> newUnifiedSet(int size) {
		return UnifiedSet.newSet(size);
	}

	public static <E> MutableSet<E> newUnifiedSet(Collection<E> collection) {
		return new UnifiedSet<>(collection);
	}

	public static <E> MutableSortedSet<E> newTreeSortedSet() {
		return TreeSortedSet.newSet();
	}

	public static <E> MutableSortedSet<E> newTreeSortedSet(Iterable<E> items) {
		return TreeSortedSet.newSet(items);
	}

	public static MutableLongSet newLongHashSet() {
		return new LongHashSet();
	}

	public static MutableLongSet newLongHashSet(int size) {
		return new LongHashSet(size);
	}

	/**
	 * list
	 */
	public static <E> MutableList<E> newFastList() {
		return FastList.newList();
	}

	public static <E> MutableList<E> newFastList(int size) {
		return FastList.newList(size);
	}

	/**
	 * multimap
	 */
	public static <K, V> MutableListMultimap<K, V> newFastListMultimap() {
		return FastListMultimap.newMultimap();
	}

	public static <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap() {
		return UnifiedSetMultimap.newMultimap();
	}

	/**
	 * immutable map
	 */
	public static <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		return ImmutableMapFactoryImpl.INSTANCE.withAll(map);
	}

	/**
	 * immutable set
	 */
	public static <E> ImmutableSet<E> newImmutableSet(Iterable<E> items) {
		return ImmutableSetFactoryImpl.INSTANCE.withAll(items);
	}

	public static <E> ImmutableSet<E> newImmutableSet(E[] es) {
		return ImmutableSetFactoryImpl.INSTANCE.with(es);
	}

	/**
	 * immutable sorted set
	 */
	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(Iterable<E> items) {
		return ImmutableSortedSetFactoryImpl.INSTANCE.withAll(items);
	}

	public static <E> ImmutableSortedSet<E> newImmutableSortedSet(E[] es) {
		return ImmutableSortedSetFactoryImpl.INSTANCE.with(es);
	}

	/**
	 * immutable list
	 */
	public static <E> ImmutableList<E> newImmutableList(Iterable<E> items) {
		return ImmutableListFactoryImpl.INSTANCE.withAll(items);
	}

	public static <E> ImmutableList<E> newImmutableList(E[] es) {
		return ImmutableListFactoryImpl.INSTANCE.with(es);
	}

}
