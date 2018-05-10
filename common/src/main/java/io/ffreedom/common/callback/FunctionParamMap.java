package io.ffreedom.common.callback;

import java.util.Map;
import java.util.HashMap;

public final class FunctionParamMap<K extends FunctionParamType> {

	private Map<K, FunctionParam> map = new HashMap<>();

	public FunctionParamMap<K> setParam(K key, FunctionParam value) {
		map.put(key, value);
		return this;
	}

	public FunctionParam getParam(K key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}
		throw new RuntimeException(key + " : is not macth.");
	}

}
