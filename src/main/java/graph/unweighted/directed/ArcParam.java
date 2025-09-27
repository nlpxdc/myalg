package graph.unweighted.directed;

public class ArcParam {
    public Integer fromV;
    public Integer toV;
    public BfsArcType bfsArcType;
    public DfsArcType dfsArcType;

    public ArcParam(Integer fromV, Integer toV) {
        this.fromV = fromV;
        this.toV = toV;
    }

    @Override
    public String toString() {
        return String.format("Arc:%d->%d %s %s",
                fromV, toV,
                bfsArcType == null ? "null" : bfsArcType.name(),
                dfsArcType == null ? "null" : dfsArcType.name());
    }
}
