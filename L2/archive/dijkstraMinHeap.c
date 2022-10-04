#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#define V 5
#define E 10

int minHeap[E];
int size = 0;

int getIndex(int item)
{
    for (int i=0; i<size; i++)
        if (item == minHeap[i])
            return i;
    return NULL;
}

int getLeftChildIndex(int parentIndex)
{
    return 2 * parentIndex + 1;
}


int getRightChildIndex(int parentIndex)
{
    return 2 * parentIndex + 2;
}


int getParentIndex(int childIndex)
{
    return (childIndex - 1) / 2;
}


int hasLeftChild(int index)
{
    if (getLeftChildIndex(index) < size)
        return 1;
    return 0;
}

int hasRightChild(int index)
{
    if (getRightChildIndex(index) < size)
        return 1;
    return 0;
}

int hasParent(int index)
{
    if (getParentIndex(index) >= 0)
        return 1;
    return 0;
}

int leftChild(int index)
{
    return minHeap[getLeftChildIndex(index)];
}


int rightChild(int index)
{
    return minHeap[getRightChildIndex(index)];
}

int parent(int index)
{
    return minHeap[getParentIndex(index)];
}


void swap(int index1, int index2)
{
    int temp = minHeap[index1];
    minHeap[index1] = minHeap[index2];
    minHeap[index2] = temp;
}

int peek()
{
    if (size == 0)
        return NULL;
    return minHeap[0];
}

void heapifyUpwards()
{
    int index = size - 1;
    while (hasParent(index) && parent(index) > minHeap[index])
    {
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
    }
}

void heapifyDownwards()
{
    int index = 0;
    while (hasLeftChild(index))
    {
        int smallerChildIndex = getLeftChildIndex(index);
        if (hasRightChild(index) && rightChild(index) < leftChild(index))
            smallerChildIndex = getRightChildIndex(index);

        if (minHeap[index] < minHeap[smallerChildIndex])
            break;
        else
            swap(index, smallerChildIndex);

        index = smallerChildIndex;
    }
}

int pop()
{
    if (size == 0)
        return NULL;
    int min = minHeap[0];
    minHeap[0] = minHeap[size - 1];
    size--;
    heapifyDownwards();
    return min;
}

void add(int item)
{
    minHeap[size] = item;
    size++;
    heapifyUpwards();
}


//finds the vertex with the min distance value in the set of vertices that are not yet in the min spanning tree
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

    // initializing everything to infinity and the visited array
    for (int i=0; i<V; i++)
        dist[i] = INT_MAX;


    dist[src] = 0;
    for (int i=0; i<V-1; i++)
    {
        if (graph[src][i] != 0)
            add(graph[src][i]);
    }

    for (int i=0; i<V-1; i++) {
        int minDist = pop(); // selecting the min distance vertex from the set of vertices not yet visited (u is the source in the first iteration)
        int indexOfMinDist = getIndex(minDist);

        // update the value in the dist array if its not yet visited and there is a edge from u to v and the total cost of the path from source to v through u is smaller than the current cost that is in dist[v]
        for (int v=0; v<V; v++)
            if (graph[indexOfMinDist][v] && dist[indexOfMinDist] != INT_MAX && dist[indexOfMinDist] + graph[indexOfMinDist][v] < dist[v])
                dist[v] = dist[indexOfMinDist] + graph[indexOfMinDist][v];
    }

    for (int i = 0; i < V; i++)
        printf("Vertex: %d Distance from Source: %d\n", i, dist[i]);
}

int main()
{
    // this graph is the NTU slides one lmao
    int graph[V][V] = {
                        {0, 5, 10, 0, 0},
                        {0, 0, 3, 9, 2},
                        {0, 2, 0, 1, 0},
                        {0, 0, 0, 0, 4},
                        {7, 0, 0, 6, 0}
                      };

    dijkstra(graph, 0);
    return 0;
}

