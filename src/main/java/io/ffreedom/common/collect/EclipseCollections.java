package io.ffreedom.common.collect;

import java.util.Map;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

public final class EclipseCollections {

	public static final <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static final <K, V> MutableMap<K, V> newUnifiedMap(int size) {
		return UnifiedMap.newMap(size);
	}

	public static final <E> MutableSet<E> newUnifiedSet() {
		return UnifiedSet.newSet();
	}

	public static final <E> MutableSet<E> newUnifiedSet(int size) {
		return UnifiedSet.newSet(size);
	}

	public static final <E> MutableSortedSet<E> newTreeSortedSet() {
		return TreeSortedSet.newSet();
	}

	public static final <E> MutableList<E> newFastList() {
		return FastList.newList();
	}

	public static final <E> MutableList<E> newFastList(int size) {
		return FastList.newList(size);
	}

	public static final <V> MutableIntObjectMap<V> newIntObjectHashMap() {
		return IntObjectHashMap.newMap();
	}

	public static final <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return LongObjectHashMap.newMap();
	}

	public static final MutableLongDoubleMap newLongDoubleHashMap(int size) {
		return new LongDoubleHashMap(size);
	}

	public static final <K, V> MutableListMultimap<K, V> newFastListMultimap() {
		return FastListMultimap.newMultimap();
	}

	public static final <K, V> MutableSetMultimap<K, V> newUnifiedSetMultimap() {
		return UnifiedSetMultimap.newMultimap();
	}

	public static final <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		return ImmutableMapFactoryImpl.INSTANCE.withAll(map);
	}

	public static final <E> ImmutableSet<E> newImmutableSet(Iterable<E> item) {
		return ImmutableSetFactoryImpl.INSTANCE.withAll(item);
	}

	public static final <E> ImmutableSet<E> newImmutableSet(E[] es) {
		return ImmutableSetFactoryImpl.INSTANCE.with(es);
	}

	public static final <E> ImmutableList<E> newImmutableList(Iterable<E> item) {
		return ImmutableListFactoryImpl.INSTANCE.withAll(item);
	}

	public static final <E> ImmutableList<E> newImmutableList(E[] es) {
		return ImmutableListFactoryImpl.INSTANCE.with(es);
	}

}
