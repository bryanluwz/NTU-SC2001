import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.io.*;

public class DijkstraApp {
	static long counterArray = 0;
	static long counterMinHeap = 0;

	int minDist(int dist[], boolean visited[], int V) {
		int min = Integer.MAX_VALUE, minIndex = -1;

		for (int v = 0; v < V; v++) {
			if (visited[v] == false && dist[v] <= min) {
				min = dist[v];
				minIndex = v;
			}
		}

		return minIndex;
	}

	void dijkstraArray(int graph[][], int source, int V) {
		int dist[] = new int[V];
		boolean visited[] = new boolean[V];

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		dist[source] = 0;

		for (int i = 0; i < V; i++) {
			int u = minDist(dist, visited, V);
			visited[u] = true;

			for (int v = 0; v < V; v++) {
				if (visited[v] == false && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
				counterArray++;
			}

		}
		// uncomment to check if this is working
		// for (int j=0; j<V; j++)
		// System.out.println("Vertex: " + j + " Minimum Distance from Source: " +
		// dist[j]);
	}

	public static void dijkstraMinHeap(int V, ArrayList<ArrayList<Node>> graph, int source) {
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
				counterMinHeap++;
			}
		}
		// uncomment to check if this is working
		// for (int j = 0; j < V; j++)
		// System.out.println("Vertex: " + j + " Minimum Distance from Source: " +
		// dist[j]);
	}

	public static void main(String[] args) {
		int V;
		int max = 20;

		try {
			// Vary E by settin min to different value
			for (int min = -40; min <= -10; min += 10) {
				// Doing stuff for the csv thingy
				List<String[]> dataLines = new ArrayList<>();
				String[] header = { "V", "Array with Adjacency Matrix Number of Comparisons",
						"MinHeap with Adjacency List Number of Comparisons", "Difference (array - heap)" };
				dataLines.add(header);

				System.out.printf("\nChances of edge created = %.2f\n", (float)(max - 1) / (float)(max - min) * 100);

				// Vary V
				for (V = 10; V <= 200; V += 5) {
					ArrayList<ArrayList<Node>> graph = new ArrayList<>();
					int[][] intGraph = new int[V][V];

					intGraph = RandomArray.random2DSquareArray(V, min, max);
					if (V == 15 && min == -40)
						PlotGraphUtil.squareMatrixToCSV(intGraph, "./data/graph.csv");

					int source = 0;
					System.out.printf("V = %d, E = %d\n", V, RandomArray.countEdge(intGraph));

					// array with adjacency matrix
					DijkstraApp object = new DijkstraApp();
					object.dijkstraArray(intGraph, source, V);
					// System.out.println("Array with Adjacency Matrix");
					// System.out.println("Number of comparisons made: " + counterArray);

					RandomArray.to2DGraph(intGraph, graph); // convert adj matrix to adj list

					// minheap with adjacency list
					dijkstraMinHeap(V, graph, source);
					// System.out.println();
					// System.out.println("MinHeap with Adjacency List");
					// System.out.println("Number of comparisons made: " + counterMinHeap);
					// System.out.println();
					// System.out.println(
					// "Difference in number of comparisons made (array - minheap): "
					// + (counterArray - counterMinHeap));
					// System.out.println("=============================================");
					// System.out.println();

					String[] data = { Integer.toString(V), Long.toString(counterArray), Long.toString(counterMinHeap),
							Long.toString(counterArray - counterMinHeap) };

					dataLines.add(data);

					counterArray = 0;
					counterMinHeap = 0;
				}

				CSVthingy.givenDataArray_whenConvertToCSV_thenOutputCreated(String.format("./data/min=%d.csv", min),
						dataLines);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
