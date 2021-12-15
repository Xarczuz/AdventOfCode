package days;

import classes.XY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Day15 {
    int[][] mazeMatrix = new int[0][];
    HashMap<Integer, Integer> fastPath = new HashMap<>();

    public long part1(ArrayList<String> strings) {
        mazeMatrix = parse(strings);
        findPath1(-1, 0, 0, 0, 0);
        return min;
    }

    public long part12(ArrayList<String> strings) {
        mazeMatrix = parse(strings);
       // https://github.com/mebeim/aoc/blob/master/2021/README.md#day-15---chiton
        dijkstra(mazeMatrix, 0);
        return min;
    }


    int min = 800;

    private void findPath1(int tot, int x, int y, int depth, int strikes) {
        if (x == mazeMatrix[0].length - 1 && y == mazeMatrix.length - 1) {
            tot = tot + mazeMatrix[y][x];
            if (tot < min) {
                min = tot;
                System.out.println(min);
            }
            return;
        }
        int tot1 = tot + mazeMatrix[y][x];
        if (tot1 > min) {
            return;
        }
        yPlus(tot1, x, y, depth, strikes);
        xPlus(tot1, x, y, depth, strikes);
        yMinus(tot1, x, y, depth, strikes);
        xMinus(tot1, x, y, depth, strikes);
    }

    private void xPlus(int tot, int x, int y, int depth, int strikes) {
        int i = x + 1;
        if (i > mazeMatrix[0].length - 1) {
            return;
        }
        findPath1(tot, i, y, depth + 1, strikes);
    }

    private void yPlus(int tot, int x, int y, int depth, int strikes) {
        int i = y + 1;
        if (i > mazeMatrix.length - 1) {
            return;
        }
        findPath1(tot, x, i, depth + 1, strikes);
    }

    private void xMinus(int tot, int x, int y, int depth, int strikes) {
        int i = x - 1;
        if (i < 0) {
            return;
        }
        findPath1(tot, i, y, depth + 1, strikes);
    }

    private void yMinus(int tot, int x, int y, int depth, int strikes) {
        int i = y - 1;
        if (i < 0) {
            return;
        }
        findPath1(tot, x, i, depth + 1, strikes);
    }

    private int[][] parse(ArrayList<String> strings) {
        int[][] maze = new int[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.get(0).length(); j++) {
                maze[i][j] = strings.get(i).charAt(j) - 48;
            }
        }
        return maze;
    }
    static final int V = 9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist);
    }

}
