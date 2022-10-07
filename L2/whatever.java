import java.util.ArrayList;

public class whatever {
    public static void main(String[] args) {
        int V = 5, min = -10, max = 10;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        int[][] intGraph = new int[V][V];

        intGraph = RandomArray.random2DSquareArray(V, min, max);
        RandomArray.print2DArray(intGraph);
        RandomArray.to2DGraph(intGraph, graph);
        RandomArray.print2DGraph(graph);
    }
}
