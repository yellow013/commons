package io.ffreedom.common.collect;

import org.eclipse.collections.api.map.ConcurrentMutableMap;
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
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectLongHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.multimap.set.UnifiedSetMultimap;

public final class MutableMaps {

	private MutableMaps() {
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
		return new IntObjectHashMap<>();
	}

	public static <V> MutableIntObjectMap<V> newIntObjectHashMap(int size) {
		return new IntObjectHashMap<>(size);
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap() {
		return new LongObjectHashMap<>();
	}

	public static <V> MutableLongObjectMap<V> newLongObjectHashMap(int size) {
		return new LongObjectHashMap<>(size);
	}

	public static <K> ObjectBooleanHashMap<K> newObjectBooleanHashMap() {
		return new ObjectBooleanHashMap<>();
	}

	public static <K> ObjectBooleanHashMap<K> newObjectBooleanHashMap(int size) {
		return new ObjectBooleanHashMap<>(size);
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap() {
		return new ObjectLongHashMap<>();
	}

	public static <K> MutableObjectLongMap<K> newObjectLongHashMap(int size) {
		return new ObjectLongHashMap<>(size);
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

}
