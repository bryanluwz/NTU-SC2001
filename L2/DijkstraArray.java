public class DijkstraArray {
	static final int V = 100;

	int minDist(int dist[], boolean visited[]) {
		int min = Integer.MAX_VALUE, minIndex = -1;

		for (int v = 0; v < V; v++) {
			if (visited[v] == false && dist[v] <= min) {
				min = dist[v];
				minIndex = v;
			}
		}

		return minIndex;
	}

	void dijkstra(int graph[][], int source) {
		int dist[] = new int[V];
		boolean visited[] = new boolean[V];

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		dist[source] = 0;

		for (int i = 0; i < V; i++) {
			int u = minDist(dist, visited);
			visited[u] = true;

			for (int v = 0; v < V; v++)
				if (visited[v] == false && graph[u][v] != -1 && dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}

		for (int j = 0; j < V; j++)
			System.out.println("Vertex: " + j + " Minimum Distance from Source: " + dist[j]);
	}

	public static void main(String[] args) {
		int source = 0;
		int[][] graph;

		graph = RandomArray.random2DSquareArray(V, 1, 10);
		
		// i = 0, the characters need their own personal space
		for (int i=0; i<V; i++) {
			for (int j=0; j<V; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}

		DijkstraArray myObj = new DijkstraArray();
		long startTime = System.nanoTime();
		myObj.dijkstra(graph, source);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000; // in milliseconds
		System.out.println("Duration: " + duration + "ms");
	}
}
