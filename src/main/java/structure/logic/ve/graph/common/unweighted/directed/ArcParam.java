package structure.logic.ve.graph.common.unweighted.directed;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArcParam arcParam = (ArcParam) o;
        return fromV.equals(arcParam.fromV) && toV.equals(arcParam.toV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromV, toV);
    }

}
