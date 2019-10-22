package io.ffreedom.commons.chronicle.map;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import io.ffreedom.common.annotations.lang.MayThrowsRuntimeException;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

@ThreadSafe
public class ChronicleMapKeeper<K, V> {

	private ConcurrentMutableMap<String, ChronicleMap<K, V>> savedMap = ConcurrentHashMap.newMap(64);

	private ChronicleMapAttributes<K, V> attributes;

	public ChronicleMapKeeper(@Nonnull ChronicleMapAttributes<K, V> attributes) {
		if (attributes == null)
			throw new IllegalArgumentException("attributes can not be null");
		this.attributes = attributes;
	}

	@MayThrowsRuntimeException
	public ChronicleMap<K, V> acquire(String filename) throws ChronicleIOException {
		return savedMap.getIfAbsentPutWith(filename, this::newChronicleMap, filename);
	}

	private ChronicleMap<K, V> newChronicleMap(String filename) {
		ChronicleMapBuilder<K, V> builder = ChronicleMapBuilder.of(attributes.getKeyClass(), attributes.getValueClass())
				.putReturnsNull(attributes.isPutReturnsNull()).removeReturnsNull(attributes.isRemoveReturnsNull())
				.entries(attributes.getEntries());
		if (attributes.getActualChunkSize() > 0)
			builder.actualChunkSize(attributes.getActualChunkSize());
		if (attributes.getAverageKey() != null)
			builder.averageKey(attributes.getAverageKey());
		if (attributes.getAverageValue() != null)
			builder.averageValue(attributes.getAverageValue());
		if (attributes.isPersistent()) {
			File persistedFile = new File(attributes.getSavePath(), filename);
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

}
