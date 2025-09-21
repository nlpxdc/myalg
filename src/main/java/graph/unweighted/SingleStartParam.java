package graph.unweighted;

public class SingleStartParam {
    //bfs&dfs
    public final Integer startV;
    //dfs
//    public Integer v;
    public int maxConnectedChildGraphCnt;

    public SingleStartParam(Integer startV, int maxConnectedChildGraphCnt) {
        this.startV = startV;
//        this.v = startV;
        this.maxConnectedChildGraphCnt = maxConnectedChildGraphCnt;
    }

}
