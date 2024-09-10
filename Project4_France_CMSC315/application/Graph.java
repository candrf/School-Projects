/*
 * Name: Andrew France
 * Project Name: Project 4
 * Date: 10/09/2023
 * 
 * Class Description: The Graph class contains the methods to add vertices, connect them,
 * and perform all the checks/analysis on the graph. It contains the core logic of the checks.  
 * 
 */
package application;

import java.util.*;

public class Graph {
    private List<Vertex> vertices;
    private List<List<Integer>> adjacencyList;

    // Constructor to initialize the graph with empty vertex list and adjacency list
    public Graph() {
        this.vertices = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
    }

    // Method to add a vertex to the graph
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        adjacencyList.add(new ArrayList<>());
    }

    // Method to add an edge between two vertices (specified by their indices)
    public void addEdge(int vertex1Index, int vertex2Index) {
        adjacencyList.get(vertex1Index).add(vertex2Index);
        adjacencyList.get(vertex2Index).add(vertex1Index);
    }

 // Method to check whether the graph has cycles using Depth-First Search (DFS)
    public boolean hasCycles() {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> recursionStack = new HashSet<>();

        Vertex startVertex = vertices.get(0); // Assuming 'A' is the first vertex
        if (hasCyclesDFS(startVertex, null, visited, recursionStack)) {
            return true;
        }

        return false;
    }

    // Helper method for DFS to detect cycles and check if it forms a closed loop
    private boolean hasCyclesDFS(Vertex current, Vertex parent, Set<Vertex> visited, Set<Vertex> recursionStack) {
        visited.add(current);
        recursionStack.add(current);

        for (int neighborIndex : adjacencyList.get(vertices.indexOf(current))) {
            Vertex neighbor = vertices.get(neighborIndex);

            if (!visited.contains(neighbor)) {
                if (hasCyclesDFS(neighbor, current, visited, recursionStack)) {
                    return true;
                }
            } else if (!neighbor.equals(parent) && recursionStack.contains(neighbor)) {
                return true; // Closed loop detected
            }
        }

        recursionStack.remove(current);
        return false;
    }


    // Method to check whether the graph is connected using Depth-First Search (DFS)
    public boolean isConnected() {
        Set<Vertex> visited = new HashSet<>();
        depthFirstSearch(vertices.get(0), visited);

        return visited.size() == vertices.size();
    }

    // Helper method for DFS to traverse the graph and mark vertices as visited
    private void depthFirstSearch(Vertex current, Set<Vertex> visited) {
        visited.add(current);

        for (int neighborIndex : adjacencyList.get(vertices.indexOf(current))) {
            Vertex neighbor = vertices.get(neighborIndex);

            if (!visited.contains(neighbor)) {
                depthFirstSearch(neighbor, visited);
            }
        }
    }

    // Method to perform Depth-First Search (DFS) on the graph and return the visited vertices connected to 'A'
    public List<Vertex> depthFirstSearch() {
        List<Vertex> visitedVertices = new ArrayList<>();
        Set<Vertex> visitedSet = new HashSet<>();

        Vertex startVertex = vertices.get(0); // Assuming 'A' is the first vertex
        depthFirstSearch(startVertex, visitedSet, visitedVertices);

        return visitedVertices;
    }

    // Helper method for DFS to traverse the graph and collect visited vertices connected to 'A'
    private void depthFirstSearch(Vertex current, Set<Vertex> visitedSet, List<Vertex> visitedVertices) {
        visitedSet.add(current);
        visitedVertices.add(current);

        for (int neighborIndex : adjacencyList.get(vertices.indexOf(current))) {
            Vertex neighbor = vertices.get(neighborIndex);

            if (!visitedSet.contains(neighbor)) {
                depthFirstSearch(neighbor, visitedSet, visitedVertices);
            }
        }
    }

    // Method to perform Breadth-First Search (BFS) on the graph and return the visited vertices connected to 'A'
    public List<Vertex> breadthFirstSearch() {
        List<Vertex> visitedVertices = new ArrayList<>();
        Set<Vertex> visitedSet = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        if (!vertices.isEmpty()) {
            Vertex startVertex = vertices.get(0); // Assuming 'A' is the first vertex
            visitedSet.add(startVertex);
            queue.add(startVertex);

            while (!queue.isEmpty()) {
                Vertex current = queue.poll();
                visitedVertices.add(current);

                for (int neighborIndex : adjacencyList.get(vertices.indexOf(current))) {
                    Vertex neighbor = vertices.get(neighborIndex);

                    if (!visitedSet.contains(neighbor)) {
                        visitedSet.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return visitedVertices;
    }

    // Getter method for vertices
    public List<Vertex> getVertices() {
        return vertices;
    }

    // Getter method for adjacency list
    public List<List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
