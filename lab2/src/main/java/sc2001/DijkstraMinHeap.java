package sc2001;
import java.util.*;

public class DijkstraMinHeap {
	public static void dijkstra(int V, ArrayList<ArrayList<Node>> graph, int source) {
		int[] dist = new int[V];

		for (int i = 0; i < V; i++)
			dist[i] = Integer.MAX_VALUE;

		dist[source] = 0;

		PriorityQueue<Node> minHeap = new PriorityQueue<>((v1, v2) -> v1.getCost() - v2.getCost());
		minHeap.add(new Node(source, 0));

		while (minHeap.size() > 0) {
			Node current = minHeap.poll();

			for (Node node : graph.get(current.getVertex())) {
				if (dist[current.getVertex()] + node.getCost() < dist[node.getVertex()]) {
					dist[node.getVertex()] = dist[current.getVertex()] + node.getCost();
					minHeap.add(new Node(node.getVertex(), dist[node.getVertex()]));
				}
			}
		}

		for (int j = 0; j < V; j++)
			System.out.println("Vertex: " + j + " Minimum Distance from Source: " + dist[j]);

	}

	public static void main(String[] args) {
		int V = 100;
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		int[][] intGraph;

		// Should have negative numbers at min so that some nodes are not connected
		// If min is positive then all nodes are connected to each other le
		// Unless that is what you want la
		intGraph = RandomArray.random2DSquareArray(V, -5, 10);

		RandomArray.print2DArray(intGraph);

		int source = 0;

		RandomArray.to2DGraph(intGraph, graph);

		long startTime = System.nanoTime();
		dijkstra(V, graph, source);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Duration: " + duration + "ms");
	}
}
