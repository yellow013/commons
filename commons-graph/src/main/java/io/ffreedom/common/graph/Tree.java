package io.ffreedom.common.graph;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collections.MutableMaps;

public final class Tree<N extends Node> {

	private MutableIntObjectMap<N> nodeMap;

	private Tree(int initialCapacity) {
		this.nodeMap = MutableMaps.newIntObjectHashMap(initialCapacity);
	}

	public static <N extends Node> Tree<N> newTree() {
		return new Tree<>(64);
	}

	public static <N extends Node> Tree<N> newTree(int initialCapacity) {
		return new Tree<>(64);
	}

	public boolean addNode(N n) {
		if (n == null || nodeMap.containsKey(n.getId()))
			return false;
		nodeMap.put(n.getId(), n);
		return true;
	}

}
