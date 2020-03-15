package io.mercury.common.param;

import static io.mercury.common.util.BytesUtil.mergeInt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.eclipse.collections.api.map.primitive.MutableLongBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongLongHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.mercury.common.param.api.JointKey;

public class JointKeyParamMap<K extends JointKey> {

	private MutableLongBooleanMap booleanParamMap = new LongBooleanHashMap();
	private MutableLongLongMap longParamMap = new LongLongHashMap();
	private MutableLongDoubleMap doubleParamMap = new LongDoubleHashMap();
	private MutableLongObjectMap<String> stringParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalDate> localDateParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalTime> localTimeParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalDateTime> localDateTimeParamMap = new LongObjectHashMap<>();

	public JointKeyParamMap() {

	}

	public void put(K key, boolean param) {
		booleanParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, long param) {
		longParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, double param) {
		doubleParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, String param) {
		stringParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, LocalDate param) {
		localDateParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, LocalTime param) {
		localTimeParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public void put(K key, LocalDateTime param) {
		localDateTimeParamMap.put(mergeInt(key.key0(), key.key1()), param);
	}

	public boolean getBoolean(K key) {
		return booleanParamMap.get(mergeInt(key.key0(), key.key1()));
	}

}
