import java.util.*;

public class DijkstraMinHeap {

	static class Node {
		int vertex, cost;
		
		Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
		
		int getVertex() {
			return vertex;
		}
		
		int getCost() {
			return cost;
		}
	}
	
	
	public static void dijkstra(int V, ArrayList<ArrayList<Node>> graph, int source) {
		int[] dist = new int [V];
		
		for (int i=0; i<V; i++) 
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
		
		for (int j=0; j<V; j++) 
			System.out.println("Vertex: " + j + " Minimum Distance from Source: " + dist[j]);

	}
	
	public static void main(String[] args) {
		int V = 100;
		ArrayList<ArrayList<Node> > graph = new ArrayList<>();
		int [][] intGraph = new int[V][V];
		
		intGraph = RandomArray.random2DSquareArray(V, 1, 10);
		
		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				System.out.print(intGraph[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < V; i++) 
            graph.add(new ArrayList<>());
		
		int source = 0;
		
		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				if (intGraph[i][j] != 0)
				{
					graph.get(i).add(new Node(j, intGraph[i][j]));
				}
			}
		}
		long startTime = System.nanoTime();
		dijkstra(V, graph, source);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Duration: " + duration + "ms");
	}
}
