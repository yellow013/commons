package io.ffreedom.common.graph;

import java.util.Set;

import org.eclipse.collections.api.set.MutableSet;
import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.BreadthFirstIterator;

import io.ffreedom.common.collections.MutableSets;
import io.ffreedom.common.graph.base.Edge;

public final class DirectedGraph<V> {

	private final Graph<V, Edge> graph;

	private DirectedGraph(Class<V> VertexClass) {
		this.graph = GraphTypeBuilder.directed().vertexClass(VertexClass).edgeSupplier(Edge.EdgeSupplier).buildGraph();
	}

	public static <V> DirectedGraph<V> buildOf(Class<V> VertexClass) {
		return new DirectedGraph<>(VertexClass);
	}

	public DirectedGraph<V> addVertex(V vertex) {
		if (vertex != null)
			graph.addVertex(vertex);
		return this;
	}

	public DirectedGraph<V> addEdge(V sourceVertex, V targetVertex) {
		graph.addVertex(sourceVertex);
		graph.addVertex(targetVertex);
		graph.addEdge(sourceVertex, targetVertex);
		return this;
	}

	public boolean contains(V v) {
		return graph.containsVertex(v);
	}

	public boolean contains(Edge e) {
		return graph.containsEdge(e);
	}

	public boolean containsEdge(V source, V target) {
		return graph.containsEdge(source, target);
	}

	public MutableSet<V> allVertex() {
		return MutableSets.newUnifiedSet(graph.vertexSet());
	}

	public MutableSet<Edge> allEdge() {
		return MutableSets.newUnifiedSet(graph.edgeSet());
	}

	public MutableSet<V> allChildVertex(V vertex) {
		return MutableSets.newUnifiedSet(new BreadthFirstIterator<>(graph, vertex));
	}

	public Graph<V, Edge> getGraph() {
		return graph;
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

		System.out.println(graph.getGraph().getType());

	}

}
