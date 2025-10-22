package graph.unweighted;

public abstract class GraphMeta {
    public int n;
//    public int edgeN;
//    public int arcN;

    public abstract AllVo traverseByBfs();

    public abstract AllVo traverseByDfs();

}
