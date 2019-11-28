package io.mercury.common.param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.function.Supplier;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

public final class ParamKeyMap<K extends ParamKey> {

	private MutableIntBooleanMap booleanParamMap = new IntBooleanHashMap();
	private MutableIntIntMap integerParamMap = new IntIntHashMap();
	private MutableIntDoubleMap doubleParamMap = new IntDoubleHashMap();
	private MutableIntObjectMap<String> stringParamMap = new IntObjectHashMap<>();
	private MutableIntObjectMap<Temporal> temporalParamMap = new IntObjectHashMap<>();

	public ParamKeyMap(Supplier<ImmutableMap<K, Object>> initializer) {
		ImmutableMap<K, Object> initMap = initializer.get();
		initMap.forEachKeyValue((K key, Object value) -> {
			switch (key.getParamType()) {
			case BOOLEAN:
				putParam(key, (boolean) value);
				break;
			case INTEGER:
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
				throw new IllegalArgumentException("keyId -> " + key.getKeyId() + " illegal argument");
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
