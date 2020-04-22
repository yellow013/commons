package io.mercury.common.param.map;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;

public final class MutableParamMap<K extends ParamKey> {

	private MutableIntBooleanMap booleanParamMap = new IntBooleanHashMap();
	private MutableIntIntMap integerParamMap = new IntIntHashMap();
	private MutableIntDoubleMap doubleParamMap = new IntDoubleHashMap();
	private MutableIntObjectMap<String> stringParamMap = new IntObjectHashMap<>();
	private MutableIntObjectMap<Temporal> temporalParamMap = new IntObjectHashMap<>();

	public MutableParamMap(Supplier<Map<K, Object>> initializer) {
		Map<K, Object> initMap = initializer.get();
		initMap.forEach((K key, Object value) -> {
			switch (key.paramType()) {
			case BOOLEAN:
				putParam(key, (boolean) value);
				break;
			case INT:
				putParam(key, (int) value);
				break;
			case DOUBLE:
				putParam(key, (double) value);
				break;
			case STRING:
				putParam(key, (String) value);
				break;
			case DATETIME:
				putParam(key, (LocalDateTime) value);
				break;
			case DATE:
				putParam(key, (LocalDate) value);
				break;
			case TIME:
				putParam(key, (LocalTime) value);
				break;
			default:
				throw new IllegalArgumentException("paramId -> " + key.paramId() + " illegal argument");
			}
		});
	}

	public boolean getBoolean(K key) {
		if (key.paramType() != ParamType.BOOLEAN)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not BOOLEAN. paramType()==" + key.paramType());
		return booleanParamMap.get(key.paramId());
	}

	public int getInteger(K key) {
		if (key.paramType() != ParamType.INT)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not INT. paramType()==" + key.paramType());
		return integerParamMap.get(key.paramId());
	}

	public double getDouble(K key) {
		if (key.paramType() != ParamType.DOUBLE)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not DOUBLE. paramType()==" + key.paramType());
		return doubleParamMap.get(key.paramId());
	}

	public String getString(K key) {
		if (key.paramType() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not STRING. paramType()==" + key.paramType());
		return stringParamMap.get(key.paramId());
	}

	public LocalDateTime getDateTime(K key) {
		if (key.paramType() != ParamType.DATETIME)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not DATETIME. paramType()==" + key.paramType());
		return (LocalDateTime) temporalParamMap.get(key.paramId());
	}

	public LocalDate getDate(K key) {
		if (key.paramType() != ParamType.DATE)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not DATE. paramType()==" + key.paramType());
		return (LocalDate) temporalParamMap.get(key.paramId());
	}

	public LocalTime getTime(K key) {
		if (key.paramType() != ParamType.TIME)
			throw new IllegalArgumentException(
					"Key -> " + key + " paramType is not TIME. getParamType()==" + key.paramType());
		return (LocalTime) temporalParamMap.get(key.paramId());
	}

	private void putParam(K key, boolean value) {
		booleanParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, int value) {
		integerParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, double value) {
		doubleParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, String value) {
		stringParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, LocalDateTime value) {
		temporalParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, LocalDate value) {
		temporalParamMap.put(key.paramId(), value);
	}

	private void putParam(K key, LocalTime value) {
		temporalParamMap.put(key.paramId(), value);
	}

}
