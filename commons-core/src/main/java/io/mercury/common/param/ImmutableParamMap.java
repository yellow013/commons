package io.mercury.common.param;

import java.util.Properties;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.param.api.ParamKey;
import io.mercury.common.param.api.ParamType;
import io.mercury.common.util.Assertor;

public final class ImmutableParamMap<K extends ParamKey> {

	private ImmutableMap<K, String> immutableMap;

	@SafeVarargs
	public ImmutableParamMap(@Nonnull Properties properties, K... keys) {
		Assertor.nonNull(properties, "properties");
		Assertor.requiredLength(keys, 1, "keys");
		MutableMap<K, String> unifiedMap = MutableMaps.newUnifiedMap();
		for (K key : keys)
			unifiedMap.put(key, properties.getProperty(key.paramName()));
		this.immutableMap = unifiedMap.toImmutable();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(K key) {
		if (key.paramType() != ParamType.BOOLEAN)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not BOOLEAN. paramType==" + key.paramType());
		return Boolean.parseBoolean(Assertor.nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(K key) {
		if (key.paramType() != ParamType.INT)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not INT. paramType==" + key.paramType());
		return Integer.parseInt(Assertor.nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(K key) {
		if (key.paramType() != ParamType.DOUBLE)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not DOUBLE. paramType==" + key.paramType());
		return Double.parseDouble(Assertor.nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getString(K key) {
		if (key.paramType() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not STRING. paramType==" + key.paramType());
		return Assertor.nonNull(immutableMap.get(key.paramName()), key.paramName());
	}

}
