package io.ffreedom.common.param;

import java.time.temporal.Temporal;

import org.eclipse.collections.api.map.primitive.MutableLongBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableLongDoubleMap;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongBooleanHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongDoubleHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongIntHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

public abstract class JointKeyParamMap<JK1 extends JointKey, JK2 extends JointKey> {

	private MutableLongBooleanMap booleanParamMap = new LongBooleanHashMap();
	private MutableLongIntMap integerParamMap = new LongIntHashMap();
	private MutableLongDoubleMap doubleParamMap = new LongDoubleHashMap();
	private MutableLongObjectMap<String> stringParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<Temporal> temporalParamMap = new LongObjectHashMap<>();

	public JointKeyParamMap() {

	}

	public void put(JK1 key1, JK2 key2, ParamType type, Object value) {

	}

	public boolean get(JK1 key1, JK2 key2) {
		int key = key1.portionId() * 1000000000 + key2.portionId();
		return booleanParamMap.get(key);
	}

}
