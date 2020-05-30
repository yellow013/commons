package io.mercury.common.param.map;

import static io.mercury.common.util.Assertor.nonEmpty;
import static io.mercury.common.util.Assertor.nonNull;
import static io.mercury.common.util.Assertor.requiredLength;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.util.Map;
import java.util.Properties;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;

public final class ImmutableParamMap<K extends ParamKey> {

	private final ImmutableIntObjectMap<String> immutableMap;

	/**
	 * 根据传入的Key获取Map中的相应字段
	 * 
	 * @param keys
	 * @param maps
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public ImmutableParamMap(@Nonnull K[] keys, @Nonnull Map<String, ?> map)
			throws NullPointerException, IllegalArgumentException {
		requiredLength(keys, 1, "keys");
		nonEmpty(map, "map");
		MutableIntObjectMap<String> mutableMap = MutableMaps.newIntObjectHashMap();
		for (K key : keys) {
			if (map.containsKey(key.key()))
				mutableMap.put(key.id(), map.get(key.key()).toString());
		}
		this.immutableMap = mutableMap.toImmutable();
	}

	/**
	 * 根据传入的Key获取Map中的相应字段
	 * 
	 * @param keys
	 * @param maps
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public ImmutableParamMap(@Nonnull K[] keys, @Nonnull Properties properties)
			throws NullPointerException, IllegalArgumentException {
		requiredLength(keys, 1, "keys");
		nonNull(properties, "properties");
		MutableIntObjectMap<String> map = MutableMaps.newIntObjectHashMap();
		for (K key : keys) {
			if (properties.containsKey(key.key())) 
				map.put(key.id(), properties.get(key.key()).toString());
		}
		this.immutableMap = map.toImmutable();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(K key) throws IllegalArgumentException, NullPointerException {
		if (key.type() != ParamType.BOOLEAN)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not BOOLEAN. paramType==" + key.type());
		return parseBoolean(nonNull(immutableMap.get(key.id()), key.key()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(K key) throws IllegalArgumentException, NullPointerException {
		if (key.type() != ParamType.INT)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [INT]. paramType==" + key.type());
		return parseInt(nonNull(immutableMap.get(key.id()), key.key()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(K key) throws IllegalArgumentException, NullPointerException {
		if (key.type() != ParamType.DOUBLE)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [DOUBLE], paramType==" + key.type());
		return parseDouble(nonNull(immutableMap.get(key.id()), key.key()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getString(K key) throws IllegalArgumentException, NullPointerException {
		if (key.type() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [STRING], paramType==" + key.type());
		return nonNull(immutableMap.get(key.id()), key.key());
	}

}
