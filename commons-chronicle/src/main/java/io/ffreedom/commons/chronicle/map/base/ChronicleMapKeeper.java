package io.ffreedom.commons.chronicle.map.base;

import java.io.File;
import java.io.IOException;

import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import io.ffreedom.common.annotations.lang.MayThrowRuntimeException;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class ChronicleMapKeeper<K, V> {

	private ConcurrentMutableMap<String, ChronicleMap<K, V>> savedMap = ConcurrentHashMap.newMap(64);

	@MayThrowRuntimeException
	public ChronicleMap<K, V> acquire(String filename, ChronicleMapAttributes<K, V> attributes)
			throws ChronicleIOException {
		return savedMap.getIfAbsentPutWith(generateKey(filename, attributes), this::newChronicleMap,
				attributes.setPersistedFile(filename));
	}

	private ChronicleMap<K, V> newChronicleMap(ChronicleMapAttributes<K, V> attributes) {
		ChronicleMapBuilder<K, V> builder = ChronicleMapBuilder.of(attributes.getKeyClass(), attributes.getValueClass())
				.putReturnsNull(attributes.isPutReturnsNull()).removeReturnsNull(attributes.isRemoveReturnsNull())
				.entries(attributes.getEntries());
		if (attributes.getActualChunkSize() > 0)
			builder.actualChunkSize(attributes.getActualChunkSize());
		if (attributes.getAverageKey() != null)
			builder.averageKey(attributes.getAverageKey());
		if (attributes.getAverageValue() != null)
			builder.averageValue(attributes.getAverageValue());
		File persistedFile = attributes.getPersistedFile();
		if (persistedFile != null) {
			try {
				if (!persistedFile.exists()) {
					File parentFile = persistedFile.getParentFile();
					if (!parentFile.exists())
						parentFile.mkdirs();
					return builder.createPersistedTo(persistedFile);
				} else {
					if (attributes.isRecover())
						return builder.createOrRecoverPersistedTo(persistedFile);
					else
						return builder.createPersistedTo(persistedFile);
				}
			} catch (IOException e) {
				throw new ChronicleIOException(e);
			}
		} else
			return builder.create();
	}

	private String generateKey(String filename, ChronicleMapAttributes<K, V> setter) {
		StringBuilder builder = new StringBuilder(128).append(filename).append("[KeyTyep:")
				.append(setter.getKeyClass().getSimpleName()).append("][ValueTyep:")
				.append(setter.getValueClass().getSimpleName()).append("]");
		return setter.getPersistedFile() == null ? builder.toString()
				: builder.append("[FileAbsolutePath:").append(setter.getPersistedFile().getAbsolutePath()).append("]")
						.toString();
	}

}
