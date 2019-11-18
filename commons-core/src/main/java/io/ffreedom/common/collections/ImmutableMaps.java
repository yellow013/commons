package io.ffreedom.common.collections;

import java.util.Map;

import org.eclipse.collections.api.factory.map.ImmutableMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableIntDoubleMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableIntIntMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableIntLongMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableIntObjectMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableLongDoubleMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableLongIntMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableLongLongMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableLongObjectMapFactory;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntDoubleMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntIntMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntLongMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntObjectMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableLongDoubleMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableLongIntMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableLongLongMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableLongObjectMapFactoryImpl;

public final class ImmutableMaps {

	private ImmutableMaps() {
	}

	/**
	 * 
	 * factory
	 */
	public static ImmutableIntIntMapFactory immutableIntIntMapFactory() {
		return ImmutableIntIntMapFactoryImpl.INSTANCE;
	}

	public static ImmutableIntLongMapFactory immutableIntLongMapFactory() {
		return ImmutableIntLongMapFactoryImpl.INSTANCE;
	}

	public static ImmutableIntDoubleMapFactory immutableIntDoubleMapFactory() {
		return ImmutableIntDoubleMapFactoryImpl.INSTANCE;
	}

	public static ImmutableIntObjectMapFactory immutableIntObjectMapFactory() {
		return ImmutableIntObjectMapFactoryImpl.INSTANCE;
	}

	public static ImmutableLongIntMapFactory immutableLongIntMapFactory() {
		return ImmutableLongIntMapFactoryImpl.INSTANCE;
	}

	public static ImmutableLongLongMapFactory immutableLongLongMapFactory() {
		return ImmutableLongLongMapFactoryImpl.INSTANCE;
	}

	public static ImmutableLongDoubleMapFactory immutableLongDoubleMapFactory() {
		return ImmutableLongDoubleMapFactoryImpl.INSTANCE;
	}

	public static ImmutableLongObjectMapFactory immutableLongObjectMapFactory() {
		return ImmutableLongObjectMapFactoryImpl.INSTANCE;
	}

	public static ImmutableMapFactory immutableMapFactory() {
		return ImmutableMapFactoryImpl.INSTANCE;
	}

	public static <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return ImmutableMapFactoryImpl.INSTANCE.empty();
		return ImmutableMapFactoryImpl.INSTANCE.withAll(map);
	}

}
