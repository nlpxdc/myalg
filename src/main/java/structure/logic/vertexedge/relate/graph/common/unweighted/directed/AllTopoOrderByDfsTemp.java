package structure.logic.vertexedge.relate.graph.common.unweighted.directed;

public class AllTopoOrderByDfsTemp {
    public boolean[] visited;
    public int[] vStatuses;
    public int timeNo;
    public int[] discoverVTimes;
    public int[] finishVTimes;

    public AllTopoOrderByDfsTemp(int n) {
        this.visited = new boolean[n];
        this.vStatuses = new int[n];
        this.timeNo = 0;
        this.discoverVTimes = new int[n];
        this.finishVTimes = new int[n];
    }

}
