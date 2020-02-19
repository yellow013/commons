package io.mercury.common.graph;

import java.util.Set;

import org.eclipse.collections.api.set.MutableSet;
import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.BreadthFirstIterator;

import io.mercury.common.collections.MutableSets;

public final class DirectedGraph<V> {

	private final Graph<V, Edge> internalGraph;

	private DirectedGraph(Class<V> vertexClass) {
		this.internalGraph = GraphTypeBuilder.directed().vertexClass(vertexClass).edgeSupplier(Edge.EdgeSupplier)
				.buildGraph();
	}

	public static <V> DirectedGraph<V> buildOf(Class<V> vertexClass) {
		return new DirectedGraph<>(vertexClass);
	}

	public DirectedGraph<V> addVertex(V vertex) {
		if (vertex != null)
			internalGraph.addVertex(vertex);
		return this;
	}

	public DirectedGraph<V> addEdge(V source, V target) {
		internalGraph.addVertex(source);
		internalGraph.addVertex(target);
		internalGraph.addEdge(source, target);
		return this;
	}

	public boolean containsVertex(V v) {
		return internalGraph.containsVertex(v);
	}

	public boolean containsEdge(Edge e) {
		return internalGraph.containsEdge(e);
	}

	public boolean containsEdge(V source, V target) {
		return internalGraph.containsEdge(source, target);
	}

	public MutableSet<V> allVertex() {
		return MutableSets.newUnifiedSet(internalGraph.vertexSet());
	}

	public MutableSet<Edge> allEdge() {
		return MutableSets.newUnifiedSet(internalGraph.edgeSet());
	}

	public MutableSet<V> allChildVertex(V vertex) {
		return MutableSets.newUnifiedSet(new BreadthFirstIterator<>(internalGraph, vertex));
	}

	public Graph<V, Edge> internalGraph() {
		return internalGraph;
	}

	public static void main(String[] args) {
		DirectedGraph<Integer> graph = DirectedGraph.buildOf(Integer.class);

		graph.addVertex(1);
		graph.addVertex(11);
		graph.addVertex(12);
		graph.addVertex(13);
		graph.addVertex(22);
		graph.addVertex(221);
		graph.addVertex(222);
		graph.addVertex(223);

		graph.addEdge(1, 11);
		graph.addEdge(1, 12);
		// graph.addEdge(12, 1);
		graph.addEdge(1, 13);
		graph.addEdge(12, 22);
		graph.addEdge(22, 221);
		graph.addEdge(22, 222);
		graph.addEdge(22, 223);

		// System.out.println(graph.inDegreeOf(11));
		// System.out.println(graph.outDegreeOf(1));

		System.out.println("===================");

		graph.addVertex(2221);
		graph.addVertex(2222);
		graph.addEdge(22, 221);
		graph.addEdge(222, 2221);
		graph.addEdge(222, 2222);

		Set<Integer> allChildVertex = graph.allChildVertex(12);

		System.out.println(allChildVertex);

		System.out.println(graph.internalGraph().getType());

	}

}
