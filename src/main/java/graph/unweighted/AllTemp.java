package graph.unweighted;

import java.util.Arrays;

public class AllTemp {
    public boolean[] visited;
    public VStatus[] vStatuses;
    public Integer[] parents;
    public int dfsVTime;

    public AllTemp(GraphMeta graphMeta) {
        visited = new boolean[graphMeta.n];
        vStatuses = new VStatus[graphMeta.n];
        Arrays.fill(vStatuses, VStatus.WHITE);
        parents = new Integer[graphMeta.n];
        dfsVTime = 0;
    }
}
