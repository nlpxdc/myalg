package graph;

import java.util.Set;

class VEGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class Edge {
    Integer from;
    Integer to;
    Integer weight;
}

class VeGraph<V> {
    Set<V> vertexSet;
    Set<Edge> edgeSet;
}
