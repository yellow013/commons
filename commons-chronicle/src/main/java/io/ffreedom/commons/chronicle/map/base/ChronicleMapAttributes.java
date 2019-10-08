package io.ffreedom.commons.chronicle.map.base;

import static io.ffreedom.common.utils.StringUtil.isPath;

import java.io.File;

import io.ffreedom.common.env.SysProperty;

public final class ChronicleMapAttributes<K, V> {

	private Class<K> keyClass;
	private Class<V> valueClass;
	private long entries = DefaultEntries;
	private K averageKey;
	private V averageValue;
	private File persistedFile;

	public static final long DefaultEntries = 32 << 16;
	public static final String DefaultRootPath = SysProperty.JAVA_IO_TMPDIR + "/chronicle-map/";
	public static final String DefaultFolder = "default/";

	private ChronicleMapAttributes(Class<K> keyClass, Class<V> valueClass) {
		this.keyClass = keyClass;
		this.valueClass = valueClass;
	}

	public static <K, V> ChronicleMapAttributes<K, V> buildOf(Class<K> keyClass, Class<V> valueClass) {
		return new ChronicleMapAttributes<>(keyClass, valueClass);
	}

	public Class<K> getKeyClass() {
		return keyClass;
	}

	public Class<V> getValueClass() {
		return valueClass;
	}

	public long getEntries() {
		return entries;
	}

	public K getAverageKey() {
		return averageKey;
	}

	public V getAverageValue() {
		return averageValue;
	}

	public File getPersistedFile() {
		return persistedFile;
	}

	public void setEntries(long entries) {
		this.entries = entries;
	}

	public void setAverageKey(K averageKey) {
		this.averageKey = averageKey;
	}

	public void setAverageValue(V averageValue) {
		this.averageValue = averageValue;
	}

	public void setPersistedFile(String rootPath, String folder, String fileName) {
		if (isPath(rootPath))
			rootPath = rootPath + "/";
		if (isPath(folder))
			folder = folder + "/";
		this.persistedFile = new File(rootPath + folder, fileName);
	}

	public void setPersistedFile(String fileName) {
		setPersistedFile(DefaultRootPath, DefaultFolder, fileName);
	}

	public void setPersistedFileAndRootPath(String rootPath, String fileName) {
		setPersistedFile(rootPath, DefaultFolder, fileName);
	}

	public void setPersistedFileAndFolder(String folder, String fileName) {
		setPersistedFile(DefaultRootPath, folder, fileName);
	}

}
