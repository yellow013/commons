package io.ffreedom.commons.chronicle.map.base;

import java.io.IOException;

import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import io.ffreedom.common.annotations.lang.MayThrowRuntimeException;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class ChronicleMapKeeper<K, V> {

	private ConcurrentMutableMap<String, ChronicleMap<K, V>> savedMap = ConcurrentHashMap.newMap(64);

	@MayThrowRuntimeException
	public ChronicleMap<K, V> acquire(String keyPrefix, ChronicleMapSetter<K, V> builder) throws ChronicleIOException {
		return savedMap.getIfAbsentPutWith(generateKey(keyPrefix, builder), this::newChronicleMap, builder);
	}

	private ChronicleMap<K, V> newChronicleMap(ChronicleMapSetter<K, V> setter) {
		ChronicleMapBuilder<K, V> builder = ChronicleMapBuilder.of(setter.getKeyClass(), setter.getValueClass())
				.entries(setter.getEntries());
		
		if (setter.getAverageKey() != null)
			builder.averageKey(setter.getAverageKey());
		if (setter.getAverageValue() != null)
			builder.averageValue(setter.getAverageValue());
		if (setter.getPersistedFile() != null) {
			try {
				return builder.createOrRecoverPersistedTo(setter.getPersistedFile());
			} catch (IOException e) {
				throw new ChronicleIOException();
			}
		} else
			return builder.create();
	}

	private String generateKey(String keyPrefix, ChronicleMapSetter<K, V> setter) {
		StringBuilder builder = new StringBuilder(128).append(keyPrefix).append("[KeyTyep:")
				.append(setter.getKeyClass().getSimpleName()).append("][ValueTyep:")
				.append(setter.getValueClass().getSimpleName()).append("]");
		return setter.getPersistedFile() == null ? builder.toString()
				: builder.append("[FilePath:").append(setter.getPersistedFile().getAbsolutePath()).append("]")
						.toString();
	}

}
