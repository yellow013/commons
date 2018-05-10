package io.ffreedom.common.init;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;

import io.ffreedom.common.init.param.ParamKey;

public final class Initializer<K extends ParamKey> {

	private UnifiedMap<K, Object> paramMap = UnifiedMap.newMap(32);

	public String getString(K key) {
		return (String) paramMap.get(key);
	}

	public Integer getInteger(K key) {
		return (Integer) paramMap.get(key);
	}

	public Double getDouble(K key) {
		return (Double) paramMap.get(key);
	}

	public Initializer<K> putParam(K key, String value) {
		paramMap.put(key, value);
		return this;
	}

	public Initializer<K> putParam(K key, Integer value) {
		paramMap.put(key, value);
		return this;
	}

	public Initializer<K> putParam(K key, Double value) {
		paramMap.put(key, value);
		return this;
	}

}
