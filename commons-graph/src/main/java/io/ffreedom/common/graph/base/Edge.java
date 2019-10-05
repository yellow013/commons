package io.ffreedom.common.graph.base;

import java.util.function.Supplier;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -365779404810792993L;

	public static Supplier<Edge> SUPPLIER = () -> new Edge();

}
