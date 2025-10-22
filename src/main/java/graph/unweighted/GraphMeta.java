package graph.unweighted;

import java.util.List;
import java.util.Map;

public abstract class GraphMeta {
    public int n;
//    public int edgeN;
//    public int arcN;

    public abstract AllVo traverseByBfs();

    public abstract AllVo traverseByDfs();

    public abstract Map<Integer, Integer> calcInDegreeMap();

    public abstract List<Integer> topoOrderByBfs();

    public abstract List<Integer> topoOrderByDfs();

}
