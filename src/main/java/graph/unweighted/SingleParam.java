package graph.unweighted;

public class SingleParam {
    //bfs&dfs
    public final Integer startV;
    //dfs
    public Integer v;
    public int maxConnectedChildGraphCnt;

    public SingleParam(Integer startV, int maxConnectedChildGraphCnt) {
        this.startV = startV;
        this.v = startV;
        this.maxConnectedChildGraphCnt = maxConnectedChildGraphCnt;
    }

}
