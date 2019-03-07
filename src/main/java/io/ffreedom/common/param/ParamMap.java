package io.ffreedom.common.param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
				putParam(key, (boolean) initMap.get(key));
				break;
			case INTEGER:
				putParam(key, (int) initMap.get(key));
				break;
			case DOUBLE:
				putParam(key, (double) initMap.get(key));
				break;
			case STRING:
				putParam(key, (String) initMap.get(key));
				break;
			case DATETIME:
				putParam(key, (LocalDateTime) initMap.get(key));
				break;
			case DATE:
				putParam(key, (LocalDate) initMap.get(key));
				break;
			case TIME:
				putParam(key, (LocalTime) initMap.get(key));
				break;
			default:
				throw new IllegalArgumentException("");
			}
		});
	}

	public boolean getBoolean(K key) {
		if (key.getParamType() != ParamType.BOOLEAN)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not BOOLEAN. getParamType()==" + key.getParamType());
		return booleanParamMap.get(key.getKeyId());
	}

	public int getInteger(K key) {
		if (key.getParamType() != ParamType.INTEGER)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not INTEGER. getParamType()==" + key.getParamType());
		return integerParamMap.get(key.getKeyId());
	}

	public double getDouble(K key) {
		if (key.getParamType() != ParamType.DOUBLE)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not DOUBLE. getParamType()==" + key.getParamType());
		return doubleParamMap.get(key.getKeyId());
	}

	public String getString(K key) {
		if (key.getParamType() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not STRING. getParamType()==" + key.getParamType());
		return stringParamMap.get(key.getKeyId());
	}

	public LocalDateTime getDateTime(K key) {
		if (key.getParamType() != ParamType.DATETIME)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not DATETIME. getParamType()==" + key.getParamType());
		return (LocalDateTime) temporalParamMap.get(key.getKeyId());
	}

	public LocalDate getDate(K key) {
		if (key.getParamType() != ParamType.DATE)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not DATE. getParamType()==" + key.getParamType());
		return (LocalDate) temporalParamMap.get(key.getKeyId());
	}

	public LocalTime getTime(K key) {
		if (key.getParamType() != ParamType.TIME)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not TIME. getParamType()==" + key.getParamType());
		return (LocalTime) temporalParamMap.get(key.getKeyId());
	}

	private void putParam(K key, boolean value) {
		booleanParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, int value) {
		integerParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, double value) {
		doubleParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, String value) {
		stringParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, LocalDateTime value) {
		temporalParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, LocalDate value) {
		temporalParamMap.put(key.getKeyId(), value);
	}

	private void putParam(K key, LocalTime value) {
		temporalParamMap.put(key.getKeyId(), value);
	}

}
