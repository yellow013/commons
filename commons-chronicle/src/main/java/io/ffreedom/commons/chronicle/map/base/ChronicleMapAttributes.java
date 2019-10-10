package io.ffreedom.commons.chronicle.map.base;

import static io.ffreedom.common.utils.StringUtil.notPath;

import java.io.File;

import io.ffreedom.common.env.SysProperty;

public final class ChronicleMapAttributes<K, V> {

	private Class<K> keyClass;
	private Class<V> valueClass;
	private K averageKey;
	private V averageValue;
	private File persistedFile;

	private boolean putReturnsNull = false;
	private boolean removeReturnsNull = false;
	private boolean recover = false;

	private int actualChunkSize;
	private long entries = DefaultEntries;
	private String rootPath = DefaultRootPath;
	private String folder = DefaultFolder;

	public static final long DefaultEntries = 32 << 16;
	public static final String DefaultRootPath = SysProperty.JAVA_IO_TMPDIR + "/";
	public static final String DefaultFolder = "default/";

	private static final String Chronicle_Map = "chronicle-map/";

	private ChronicleMapAttributes(Class<K> keyClass, Class<V> valueClass) {
		this(keyClass, valueClass, DefaultRootPath, DefaultFolder);
	}

	private ChronicleMapAttributes(Class<K> keyClass, Class<V> valueClass, String rootPath, String folder) {
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

	public int getActualChunkSize() {
		return actualChunkSize;
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

	public boolean isPutReturnsNull() {
		return putReturnsNull;
	}

	public boolean isRemoveReturnsNull() {
		return removeReturnsNull;
	}

	public boolean isRecover() {
		return recover;
	}

	public String getRootPath() {
		return rootPath;
	}

	public String getFolder() {
		return folder;
	}

	public File getPersistedFile() {
		return persistedFile;
	}

	public ChronicleMapAttributes<K, V> setActualChunkSize(int actualChunkSize) {
		this.actualChunkSize = actualChunkSize;
		return this;
	}

	public ChronicleMapAttributes<K, V> setEntries(long entries) {
		this.entries = entries;
		return this;
	}

	public ChronicleMapAttributes<K, V> setAverageKey(K averageKey) {
		this.averageKey = averageKey;
		return this;
	}

	public ChronicleMapAttributes<K, V> setAverageValue(V averageValue) {
		this.averageValue = averageValue;
		return this;
	}

	public ChronicleMapAttributes<K, V> setPutReturnsNull(boolean putReturnsNull) {
		this.putReturnsNull = putReturnsNull;
		return this;
	}

	public ChronicleMapAttributes<K, V> setRemoveReturnsNull(boolean removeReturnsNull) {
		this.removeReturnsNull = removeReturnsNull;
		return this;
	}

	public ChronicleMapAttributes<K, V> setRecover(boolean recover) {
		this.recover = recover;
		return this;
	}

	public ChronicleMapAttributes<K, V> setRootPath(String rootPath) {
		this.rootPath = rootPath;
		if (notPath(rootPath))
			this.rootPath = rootPath + "/";
		return this;
	}

	public ChronicleMapAttributes<K, V> setFolder(String folder) {
		this.folder = folder;
		if (notPath(folder))
			this.folder = folder + "/";
		return this;
	}

	public ChronicleMapAttributes<K, V> setPersistedFile(String fileName) {
		this.persistedFile = new File(rootPath + Chronicle_Map + folder, fileName);
		return this;
	}

}
