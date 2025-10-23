package graph.unweighted;

import java.util.List;
import java.util.Map;

public abstract class GraphMeta {
    public int n;
    public boolean weighted;
    public boolean directed;

//    public int edgeN;
//    public int arcN;

    public abstract AllVo allTraverseByBfs();

    public abstract AllVo allTraverseByDfs();

    public abstract Map<Integer, Integer> calcInDegreeMap();

    public abstract List<Integer> allTopoOrderByBfs();

    public abstract List<Integer> allTopoOrderByDfs();

}
