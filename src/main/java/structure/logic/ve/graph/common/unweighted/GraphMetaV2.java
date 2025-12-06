package structure.logic.ve.graph.common.unweighted;

import java.util.List;
import java.util.Map;

public abstract class GraphMetaV2 {
    //可以用完美哈希mphf，放置于紧凑数组中，用于string做为key
    public int n;
    public boolean weighted;
    public boolean directed;

//    public int edgeN;
//    public int arcN;

    public abstract void addArc(Character from, Character to);

    public abstract void addEdge(Character u, Character v);

    public abstract AllVoV2 allTraverseByBfs();

    public abstract AllVoV2 allTraverseByDfs();

    public abstract Map<Character, Integer> calcInDegreeMap();

    public abstract List<Character> allTopoOrderByBfs();

    public abstract List<Character> allTopoOrderByDfs();

}
