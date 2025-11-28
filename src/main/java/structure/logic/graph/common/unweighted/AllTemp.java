package structure.logic.graph.common.unweighted;

import structure.logic.graph.common.unweighted.directed.VStatus;

import java.util.Arrays;

public class AllTemp {
    public boolean[] visited;
    public VStatus[] vStatuses;
    public Integer[] parents;
    public int allDfsVTimeNo;

    public AllTemp(GraphMeta graphMeta) {
        visited = new boolean[graphMeta.n];
        vStatuses = new VStatus[graphMeta.n];
        Arrays.fill(vStatuses, VStatus.WHITE);
        parents = new Integer[graphMeta.n];
        allDfsVTimeNo = 0;
    }
}
