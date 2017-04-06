package org.beam.common.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseGroup<K, V> implements Group<K, V> {

	protected Map<K, V> group = new ConcurrentHashMap<>();

	@Override
	public V getMember(K k) {
		if (group.containsKey(k)) {
			return group.get(k);
		}
		group.put(k, createMember(k));
		return getMember(k);
	}

	@Override
	public List<V> getMembers() {
		return new ArrayList<>(group.values());
	}

	protected abstract V createMember(K k);

}
