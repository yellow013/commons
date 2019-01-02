package io.ffreedom.common.collect;

import java.util.Collection;
import java.util.Map;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableLongList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.mutable.primitive.LongHashSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public final class EclipseCollections {

	/**
	 * primitive map
	 */
	public static final MutableLongLongMap newLongLongHashMap() {
		return new LongLongHashMap();
	}

	public static MutableLongLongMap newLongLongHashMap(int size) {
		return new LongLongHashMap(size);
	}

	public static final MutableLongIntMap newLongIntHashMap() {
		return new LongIntHashMap();
	}

	public static final MutableLongIntMap newLongIntHashMap(int size) {
		return new LongIntHashMap(size);
	}

	public static final MutableIntDoubleMap newIntDoubleHashMap() {
		return new IntDoubleHashMap();
	}

	public static final MutableIntDoubleMap newIntDoubleHashMap(int size) {
		return new IntDoubleHashMap(size);
	}

	public static final MutableLongDoubleMap newLongDoubleHashMap() {
		return new LongDoubleHashMap();
	}

	public static final MutableLongDoubleMap newLongDoubleHashMap(int size) {
		return new LongDoubleHashMap(size);
	}

	/**
	 * primitive list
	 */
	public static final MutableLongList newLongArrayList() {
		return new LongArrayList();
	}

	public static final MutableLongList newLongArrayList(int initialCapacity) {
		return new LongArrayList(initialCapacity);
	}

	public static final MutableLongList newLongArrayListWith(long... longArray) {
		return new LongArrayList(longArray);
	}

	/**
	 * map
	 */
	public static final <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static final <K, V> MutableMap<K, V> newUnifiedMap(int size) {
		return UnifiedMap.newMap(size);
	}

	public static final <V> MutableIntObjectMap<V> newIntObjectHashMap() {
		return IntObjectHashMap.newMap();
	}

	public static final <V> MutableIntObjectMap<V> newIntObjectHashMap(int size) {
		return new IntObjectHashMap<>(size);
	}

	public static final <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return LongObjectHashMap.newMap();
	}

	public static final <V> MutableLongObjectMap<V> newLongObjectHashMap(int size) {
		return new LongObjectHashMap<>(size);
	}

	/**
	 * set
	 */
	public static final <E> MutableSet<E> newUnifiedSet() {
		return UnifiedSet.newSet();
	}

	public static final <E> MutableSet<E> newUnifiedSet(int size) {
		return UnifiedSet.newSet(size);
	}

	public static final <E> MutableSet<E> newUnifiedSet(Collection<E> collection) {
		return new UnifiedSet<>(collection);
	}

	public static final <E> MutableSortedSet<E> newTreeSortedSet() {
		return TreeSortedSet.newSet();
	}

	public static final MutableLongSet newLongHashSet() {
		return new LongHashSet();
	}

	public static final MutableLongSet newLongHashSet(int size) {
		return new LongHashSet(size);
	}

	/**
	 * list
	 */
	public static final <E> MutableList<E> newFastList() {
		return FastList.newList();
	}

	public static final <E> MutableList<E> newFastList(int size) {
		return FastList.newList(size);
	}

	/**
	 * multimap
	 */
	public static final <K, V> MutableListMultimap<K, V> newFastListMultimap() {
		return FastListMultimap.newMultimap();
	}

	public static final <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap() {
		return UnifiedSetMultimap.newMultimap();
	}

	/**
	 * immutable map
	 */
	public static final <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		return ImmutableMapFactoryImpl.INSTANCE.withAll(map);
	}

	/**
	 * immutable set
	 */
	public static final <E> ImmutableSet<E> newImmutableSet(Iterable<E> item) {
		return ImmutableSetFactoryImpl.INSTANCE.withAll(item);
	}

	/**
	 * immutable set
	 */
	public static final <E> ImmutableSet<E> newImmutableSet(E[] es) {
		return ImmutableSetFactoryImpl.INSTANCE.with(es);
	}

	/**
	 * immutable list
	 */
	public static final <E> ImmutableList<E> newImmutableList(Iterable<E> item) {
		return ImmutableListFactoryImpl.INSTANCE.withAll(item);
	}

	public static final <E> ImmutableList<E> newImmutableList(E[] es) {
		return ImmutableListFactoryImpl.INSTANCE.with(es);
	}

}
