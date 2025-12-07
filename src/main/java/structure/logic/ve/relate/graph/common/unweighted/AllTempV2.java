package structure.logic.ve.relate.graph.common.unweighted;

import structure.logic.ve.relate.graph.common.unweighted.directed.VStatus;

import java.util.Arrays;

public class AllTempV2 {
    public boolean[] visited;
    public VStatus[] vStatuses;
    public Character[] parents;
    public int allDfsVTimeNo;

    public AllTempV2(GraphMetaV2 graphMeta) {
        visited = new boolean[graphMeta.n];
        vStatuses = new VStatus[graphMeta.n];
        Arrays.fill(vStatuses, VStatus.WHITE);
        parents = new Character[graphMeta.n];
        allDfsVTimeNo = 0;
    }
}
