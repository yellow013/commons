package io.ffreedom.common.graph;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.BreadthFirstIterator;

import io.ffreedom.common.graph.base.Edge;

public final class DirectedGraph<V> {

	private final Graph<V, Edge> graph;

	private DirectedGraph(Class<V> VertexClass) {
		this.graph = GraphTypeBuilder.directed()
				.vertexClass(VertexClass).edgeSupplier(Edge.SUPPLIER).buildGraph();
	}

	public DirectedGraph<V> addVertex(V vertex) {
		if (vertex != null)
			graph.addVertex(vertex);
		return this;
	}

	public static <V> DirectedGraph<V> buildOf(Class<V> VertexClass) {
		return new DirectedGraph<>(VertexClass);
	}

	public DirectedGraph<V> addEdge(V sourceVertex, V targetVertex) {
		graph.addVertex(sourceVertex);
		graph.addVertex(targetVertex);
		graph.addEdge(sourceVertex, targetVertex);
		return this;
	}

	public Set<V> allVertex() {
		return graph.vertexSet();
	}

	public Set<Edge> allEdge() {
		return graph.edgeSet();
	}

	public Set<V> allChildVertex(V vertex) {
		BreadthFirstIterator<V, Edge> breadthFirstIterator = new BreadthFirstIterator<>(graph, vertex);
		Set<V> hashSet = new HashSet<>();
		while (breadthFirstIterator.hasNext())
			hashSet.add(breadthFirstIterator.next());
		return hashSet;
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
