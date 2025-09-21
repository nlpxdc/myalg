package graph.unweighted;

public class GraphTempVo {
    public boolean[] visited;

    public GraphTempVo(GraphMeta graphMeta) {
        visited = new boolean[graphMeta.n];
    }
}
