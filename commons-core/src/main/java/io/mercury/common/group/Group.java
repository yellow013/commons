package io.mercury.common.group;

import java.util.List;

public interface Group<K, V> {

	V getMember(K k);
	
	List<V> getMembers();

}
