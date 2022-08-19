import java.util.*;

class Vertex implements Comparable<Vertex> {
    String label;
    Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public int compareTo(Vertex o) {
        return -label.compareTo(o.label);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }
}
public class Graph {
    private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();
    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }
    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }
    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    static Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addVertex("v3");
        graph.addVertex("v4");
        graph.addVertex("v5");
        graph.addVertex("v6");
        graph.addVertex("v7");
        graph.addVertex("v8");

        graph.addEdge("v1", "v3");
        graph.addEdge("v1", "v4");
        graph.addEdge("v4", "v5");
        graph.addEdge("v4", "v6");
        graph.addEdge("v5", "v6");
        graph.addEdge("v1", "v7");
        graph.addEdge("v1", "v8");
        graph.addEdge("v1", "v2");
        graph.addEdge("v8", "v7");
        graph.addEdge("v8", "v2");
        graph.addEdge("v2", "v7");
        return graph;
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    static Set<String> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                Collections.sort(graph.getAdjVertices(vertex));
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }

    static Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }
    static Set<String> visited = new LinkedHashSet<>();
    static Set<String> recursiveDepth(Graph graph, String root) {
        visited.add(root);
        Collections.sort(graph.getAdjVertices(root));
        Collections.reverse(graph.getAdjVertices(root));
        for (Vertex v : graph.getAdjVertices(root)) {
            if (!visited.contains(v.label)) {
                visited.add(v.label);
                //System.out.println(v.label);
                recursiveDepth(graph, v.label);
            }
        }
        return visited;
    }
}
