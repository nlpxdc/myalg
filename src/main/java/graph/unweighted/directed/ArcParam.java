package graph.unweighted.directed;

public class ArcParam {
    public Integer fromV;
    public Integer toV;

    public ArcParam(Integer fromV, Integer toV) {
        this.fromV = fromV;
        this.toV = toV;
    }

    @Override
    public String toString() {
        return String.format("Arc:%d->%d", fromV, toV);
    }
}
