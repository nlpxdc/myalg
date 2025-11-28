package structure.logic.graph.unweighted;

import java.util.List;
import java.util.Map;

public abstract class GraphMeta {
    //可以用完美哈希mphf，放置于紧凑数组中，用于string做为key
    public int n;
    public boolean weighted;
    public boolean directed;

//    public int edgeN;
//    public int arcN;

    public abstract void addArc(int from, int to);

    public abstract void addEdge(int u, int v);

    public abstract AllVo allTraverseByBfs();

    public abstract AllVo allTraverseByDfs();

    public abstract Map<Integer, Integer> calcInDegreeMap();

    public abstract List<Integer> allTopoOrderByBfs();

    public abstract List<Integer> allTopoOrderByDfs();

}
