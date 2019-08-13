package io.ffreedom.common.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class BaseGroup<K, V> implements Group<K, V> {

	protected Map<K, V> group = new ConcurrentHashMap<>();

	@Override
	public synchronized V getMember(K k) {
		V member = group.get(k);
		if (member != null) {
			return member;
		} else {
			if (registerMember(k))
				return getMember(k);
			else
				return null;
			//throw new RuntimeException("Call method -> createMember(" + k + ") return null, throw RuntimeException.");
		}
	}

	@Override
	public List<V> getMembers() {
		return new ArrayList<>(group.values());
	}

	protected boolean isExisted(K k) {
		return group.containsKey(k);
	}

	protected abstract V createMember(K k);

	public synchronized boolean registerMember(K k) {
		V member = createMember(k);
		if (member != null) {
			group.put(k, member);
			return true;
		} else {
			return false;
		}
	}

}
