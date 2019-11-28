package io.mercury.common.graph.base;

import java.util.function.Supplier;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static Supplier<Edge> EdgeSupplier = Edge::new;

}
