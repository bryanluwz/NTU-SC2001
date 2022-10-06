/**
 * Thanks Aloysius for thy class definition
 */
package sc2001;

public class Node {
    private int vertex, cost;

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    public int getVertex() {
        return vertex;
    }

    public int getCost() {
        return cost;
    }
}
