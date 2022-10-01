#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#define V 5

// Finds the vertex with the min distance value in the set of vertices that are not yet in the min spanning tree
int minDistance(int dist[], int visited[])
{
    int min = INT_MAX, min_index;

    for (int v = 0; v < V; v++)
        if (visited[v] == 0 && dist[v] <= min)
            min = dist[v], min_index = v;

    return min_index;
}

void dijkstra(int graph[V][V], int src)
{
    int dist[V];
    int visited[V]; // will be 1 if vertex is already in the min spanning tree or if its shortest distance from the source vertex is finalized

    // initializing everything to infinity and the visited array
    for (int i = 0; i < V; i++)
    {
        dist[i] = INT_MAX;
        visited[i] = 0;
    }

    dist[src] = 0;

    for (int i = 0; i < V - 1; i++)
    {
        int u = minDistance(dist, visited); // selecting the min distance vertex from the set of vertices not yet visited (u is the source in the first iteration)

        visited[u] = 1;
        // update the value in the dist array if its not yet visited and there is a edge from u to v and the total cost of the path from source to v through u is smaller than the current cost that is in dist[v]
        for (int v = 0; v < V; v++)
            if (visited[v] == 0 && graph[u][v] && dist[u] != INT_MAX && dist[u] + graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
    }

    for (int i = 0; i < V; i++)
        printf("Vertex: %d Distance from Source: %d\n", i, dist[i]);
}

int main()
{
    // this graph is the NTU slides one lmaote
    int graph[V][V] = {
        {0, 5, 10, 0, 0},
        {0, 0, 3, 9, 2},
        {0, 2, 0, 1, 0},
        {0, 0, 0, 0, 4},
        {7, 0, 0, 6, 0}};

    dijkstra(graph, 0);
    return 0;
}
