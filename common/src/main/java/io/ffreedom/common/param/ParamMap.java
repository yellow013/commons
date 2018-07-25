package io.ffreedom.common.param;

import java.time.temporal.Temporal;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.ffreedom.common.functional.Initializer;

public final class ParamMap<K extends ParamKey> {

	private MutableIntBooleanMap booleanParamMap = new IntBooleanHashMap(16);
	private MutableIntIntMap integerParamMap = new IntIntHashMap(16);
	private MutableIntDoubleMap doubleParamMap = new IntDoubleHashMap(16);
	private MutableIntObjectMap<String> stringParamMap = new IntObjectHashMap<>(16);
	private MutableIntObjectMap<Temporal> temporalParamMap = new IntObjectHashMap<>(16);

	public ParamMap(Initializer<MutableMap<K, Object>> initializer) {
		MutableMap<K, Object> initMap = initializer.initialize();
		initMap.keySet().forEach(key -> {
			switch (key.getParamType()) {
			case BOOLEAN:
				putBoolean(key, (boolean) initMap.get(key));
				break;
			case INTEGER:
				putInteger(key, (int) initMap.get(key));
				break;
			case DOUBLE:
				putDouble(key, (double) initMap.get(key));
				break;
			case STRING:
				putString(key, (String) initMap.get(key));
				break;
			default:
				putTemporal(key, (Temporal) initMap.get(key));
				break;
			}
		});
	}

	public Boolean getBoolean(K key) {
		return booleanParamMap.get(key.getKeyId());
	}

	public Integer getInteger(K key) {
		return integerParamMap.get(key.getKeyId());
	}

	public Double getDouble(K key) {
		return doubleParamMap.get(key.getKeyId());
	}

	public String getString(K key) {
		return stringParamMap.get(key.getKeyId());
	}

	private void putBoolean(K key, boolean value) {
		booleanParamMap.put(key.getKeyId(), value);
	}

	private void putInteger(K key, int value) {
		integerParamMap.put(key.getKeyId(), value);
	}

	private void putDouble(K key, double value) {
		doubleParamMap.put(key.getKeyId(), value);
	}

	private void putString(K key, String value) {
		stringParamMap.put(key.getKeyId(), value);
	}

	private void putTemporal(K key, Temporal value) {
		temporalParamMap.put(key.getKeyId(), value);
	}

}
