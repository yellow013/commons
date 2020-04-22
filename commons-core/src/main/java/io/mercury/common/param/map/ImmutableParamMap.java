package io.mercury.common.param.map;

import java.util.Map;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;
import io.mercury.common.util.Assertor;

public final class ImmutableParamMap<K extends ParamKey> {

	private ImmutableMap<K, String> immutableMap;

	/**
	 * 根据传入的Key获取Map中的相应字段
	 * 
	 * @param keys
	 * @param maps
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public ImmutableParamMap(@Nonnull K[] keys, @Nonnull Map<?, ?>... maps)
			throws NullPointerException, IllegalArgumentException {
		Assertor.requiredLength(maps, 1, "maps");
		Assertor.requiredLength(keys, 1, "keys");
		MutableMap<K, String> unifiedMap = MutableMaps.newUnifiedMap();
		for (Map<?, ?> map : maps)
			for (K key : keys)
				if (map.containsKey(key.paramName()))
					unifiedMap.put(key, map.get(key.paramName()).toString());
		this.immutableMap = unifiedMap.toImmutable();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(K key) throws IllegalArgumentException, NullPointerException {
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
	public int getInt(K key) throws IllegalArgumentException, NullPointerException {
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
	public double getDouble(K key) throws IllegalArgumentException, NullPointerException {
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
	public String getString(K key) throws IllegalArgumentException, NullPointerException {
		if (key.paramType() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not STRING. paramType==" + key.paramType());
		return Assertor.nonNull(immutableMap.get(key.paramName()), key.paramName());
	}

}
