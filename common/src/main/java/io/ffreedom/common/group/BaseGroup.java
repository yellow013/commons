package io.ffreedom.common.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class BaseGroup<K, V> implements Group<K, V> {

	protected Map<K, V> group = new ConcurrentHashMap<>();

	@Override
	public synchronized V getMember(K k) {
		if (group.containsKey(k)) {
			return group.get(k);
		}
		V member = createMember(k);
		if (member != null) {
			group.put(k, member);
		} else {
			throw new RuntimeException("Call method -> createMember(" + k + ") return null, throw RuntimeException.");
		}
		return getMember(k);
	}

	@Override
	public List<V> getMembers() {
		return new ArrayList<>(group.values());
	}

	protected boolean isExisted(K k) {
		return group.containsKey(k);
	}

	protected abstract V createMember(K k);

}
