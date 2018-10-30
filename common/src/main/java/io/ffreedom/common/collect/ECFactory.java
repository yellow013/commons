package io.ffreedom.common.collect;

import java.util.Map;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.list.immutable.ImmutableListFactoryImpl;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.set.immutable.ImmutableSetFactoryImpl;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public final class ECFactory {

	public static final <K, V> MutableMap<K, V> newUnifiedMap() {
		return UnifiedMap.newMap();
	}

	public static final <K, V> MutableMap<K, V> newUnifiedMap(int size) {
		return UnifiedMap.newMap(size);
	}

	public static final <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		return ImmutableMapFactoryImpl.INSTANCE.withAll(map);
	}

	public static final <E> MutableSet<E> newUnifiedSet() {
		return UnifiedSet.newSet();
	}

	public static final <E> MutableSet<E> newUnifiedSet(int size) {
		return UnifiedSet.newSet(size);
	}

	public static final <E> ImmutableSet<E> newImmutableSet(Iterable<E> iterable) {
		return ImmutableSetFactoryImpl.INSTANCE.withAll(iterable);
	}

	public static final <E> MutableList<E> newFastList() {
		return FastList.newList();
	}

	public static final <E> MutableList<E> newFastList(int size) {
		return FastList.newList(size);
	}

	public static final <E> ImmutableList<E> newImmutableList(Iterable<E> iterable) {
		return ImmutableListFactoryImpl.INSTANCE.withAll(iterable);
	}

}
