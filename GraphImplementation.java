import java.util.*;

public class GraphImplementation implements Graph {
    private int[][] adjMatrix;
    private int vertices;

    public GraphImplementation(int vertexes){
        vertices = vertexes;
        adjMatrix = new int[vertexes][vertexes];
    }

    public void addEdge(int src, int tar) {
        adjMatrix[src][tar] = 1;
    }

    public List<Integer> topologicalSort() {
        int[] incident = new int[vertices];
        List<Integer> schedule = new LinkedList<>();

        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                incident[j] += adjMatrix[i][j];
            }
        }

        int[] neighbors;
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (incident[j] == 0) {
                    neighbors = neighbors(j);
                    for(int x = 0; x < neighbors.length; x++) {
                        incident[neighbors[x]] -= 1;
                    }
                    schedule.add(j);
                    incident[j] = -1;
                }
            }
        }
        return schedule;
    }

    public int[] neighbors(int vertex) {
        int edges = 0;
        for(int i = 0; i < vertices; i++) {
            if(adjMatrix[vertex][i] > 0) {
                edges++;
            }
        }
        int[] nextTo = new int[edges];
        for (int j = 0, x = 0; j < vertices; j++) {
            if (adjMatrix[vertex][j] > 0) {
                nextTo[x] = j;
                x++;
            }
        }
        return nextTo;
    }
}