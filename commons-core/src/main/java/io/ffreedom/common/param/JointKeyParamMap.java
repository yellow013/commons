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

public abstract class JointKeyParamMap<K1 extends JointKey, K2 extends JointKey> {

	private MutableLongBooleanMap booleanParamMap = new LongBooleanHashMap();
	private MutableLongIntMap integerParamMap = new LongIntHashMap();
	private MutableLongDoubleMap doubleParamMap = new LongDoubleHashMap();
	private MutableLongObjectMap<String> stringParamMap = new LongObjectHashMap<>();
	private MutableLongObjectMap<Temporal> temporalParamMap = new LongObjectHashMap<>();

	public JointKeyParamMap() {

	}

	public void put(K1 key1, K2 key2, ParamType type, Object value) {

	}

	public boolean get(K1 key1, K2 key2) {
		int key = key1.id() * 1000000000 + key2.id();
		return booleanParamMap.get(key);
	}

}
