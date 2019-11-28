package io.mercury.common.param;

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

public abstract class JointKeyParamMap {

	private MutableLongBooleanMap booleanParamMap = new LongBooleanHashMap();
	private MutableLongLongMap longParamMap = new LongLongHashMap();
	private MutableLongDoubleMap doubleParamMap = new LongDoubleHashMap();
	private MutableLongObjectMap<String> stringParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalDate> localDateParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalTime> localTimeParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<LocalDateTime> localDateTimeParamMap = new LongObjectHashMap<>();

	public JointKeyParamMap() {

	}

	public void put(int key1, int key2, boolean param) {
		booleanParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, long param) {
		longParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, double param) {
		doubleParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, String param) {
		stringParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, LocalDate param) {
		localDateParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, LocalTime param) {
		localTimeParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public void put(int key1, int key2, LocalDateTime param) {
		localDateTimeParamMap.put(JointIdUtil.jointId(key1, key2), param);
	}

	public boolean getBoolean(int key1, int key2) {
		return booleanParamMap.get(JointIdUtil.jointId(key1, key2));
	}

}
