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
			case DATE:
			case TIME:
				putParam(key, (Temporal) initMap.get(key));
				break;
			default:
				throw new IllegalArgumentException("");
			}
		});
	}

	public boolean getBoolean(K key) {
		return booleanParamMap.get(key.getKeyId());
	}

	public int getInteger(K key) {
		return integerParamMap.get(key.getKeyId());
	}

	public double getDouble(K key) {
		return doubleParamMap.get(key.getKeyId());
	}

	public String getString(K key) {
		return stringParamMap.get(key.getKeyId());
	}

	public LocalDateTime getDateTime(K key) {
		if (key.getParamType() == ParamType.DATETIME) {
			return (LocalDateTime) temporalParamMap.get(key.getKeyId());
		}
		throw new IllegalArgumentException(key.fullName() + " -> ParamType is not DATETIME");
	}

	public LocalDate getDate(K key) {
		if (key.getParamType() == ParamType.DATE) {
			return (LocalDate) temporalParamMap.get(key.getKeyId());
		}
		throw new IllegalArgumentException(key.fullName() + " -> ParamType is not DATE");
	}

	public LocalTime getTime(K key) {
		if (key.getParamType() == ParamType.TIME) {
			return (LocalTime) temporalParamMap.get(key.getKeyId());
		}
		throw new IllegalArgumentException(key.fullName() + " -> ParamType is not TIME");
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

	private void putParam(K key, Temporal value) {
		temporalParamMap.put(key.getKeyId(), value);
	}

}
