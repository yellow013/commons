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
	public ChronicleMap<K, V> acquire(String keyPrefix, ChronicleMapAttributes<K, V> attributes)
			throws ChronicleIOException {
		return savedMap.getIfAbsentPutWith(generateKey(keyPrefix, attributes), this::newChronicleMap, attributes);
	}

	private ChronicleMap<K, V> newChronicleMap(ChronicleMapAttributes<K, V> attributes) {
		ChronicleMapBuilder<K, V> builder = ChronicleMapBuilder.of(attributes.getKeyClass(), attributes.getValueClass())
				.entries(attributes.getEntries());
		if (attributes.getAverageKey() != null)
			builder.averageKey(attributes.getAverageKey());
		if (attributes.getAverageValue() != null)
			builder.averageValue(attributes.getAverageValue());
		if (attributes.getPersistedFile() != null) {
			try {
				return builder.createOrRecoverPersistedTo(attributes.getPersistedFile());
			} catch (IOException e) {
				throw new ChronicleIOException(e);
			}
		} else
			return builder.create();
	}

	private String generateKey(String keyPrefix, ChronicleMapAttributes<K, V> setter) {
		StringBuilder builder = new StringBuilder(128).append(keyPrefix).append("[KeyTyep:")
				.append(setter.getKeyClass().getSimpleName()).append("][ValueTyep:")
				.append(setter.getValueClass().getSimpleName()).append("]");
		return setter.getPersistedFile() == null ? builder.toString()
				: builder.append("[FilePath:").append(setter.getPersistedFile().getAbsolutePath()).append("]")
						.toString();
	}

}
