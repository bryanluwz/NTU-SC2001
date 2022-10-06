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
		int V = 10;
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		int[][] intGraph = new int[V][V];

		intGraph = RandomArray.random2DSquareArray(V, -10, 10);

		RandomArray.print2DArray(intGraph);

		int source = 0;

		RandomArray.to2DGraph(intGraph, graph);

		/*
		 * graph.get(0).add(new Node(1, 5));
		 * graph.get(0).add(new Node(2, 10));
		 * graph.get(1).add(new Node(2, 3));
		 * graph.get(1).add(new Node(3, 9));
		 * graph.get(1).add(new Node(4, 2));
		 * graph.get(2).add(new Node(1, 2));
		 * graph.get(2).add(new Node(3, 1));
		 * graph.get(3).add(new Node(4, 4));
		 * graph.get(4).add(new Node(0, 7));
		 * graph.get(4).add(new Node(3, 6));
		 */

		dijkstra(V, graph, source);
	}
}
