package io.ffreedom.common.graph;

public abstract class Node {

	private int id;
	private int parentId;

	protected Node(int id) {
		this(id, id);
	}

	protected Node(int id, int parentId) {
		this.id = id;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public int getParentId() {
		return parentId;
	}

	@Override
	final public int hashCode() {
		return id;
	}

}
