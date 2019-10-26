package io.ffreedom.common.collections;

import static org.eclipse.collections.impl.map.immutable.ImmutableMapFactoryImpl.INSTANCE;

import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.collections.api.factory.map.primitive.ImmutableIntObjectMapFactory;
import org.eclipse.collections.api.factory.map.primitive.ImmutableLongObjectMapFactory;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableIntObjectMapFactoryImpl;
import org.eclipse.collections.impl.map.immutable.primitive.ImmutableLongObjectMapFactoryImpl;

public final class ImmutableMaps {

	private ImmutableMaps() {
	}

	/**
	 * immutable map
	 */
	public static <K, V> ImmutableMap<K, V> newImmutableMap(Supplier<Map<K, V>> supplier) {
		if (supplier == null)
			return INSTANCE.empty();
		return newImmutableMap(supplier.get());
	}

	public static <K, V> ImmutableMap<K, V> newImmutableMap(Map<K, V> map) {
		if (map == null || map.isEmpty())
			return INSTANCE.empty();
		return INSTANCE.withAll(map);
	}

	public static ImmutableIntObjectMapFactory immutableIntObjectMapFactory() {
		return ImmutableIntObjectMapFactoryImpl.INSTANCE;
	}

	public static ImmutableLongObjectMapFactory immutableLongObjectMapFactory() {
		return ImmutableLongObjectMapFactoryImpl.INSTANCE;
	}

}
