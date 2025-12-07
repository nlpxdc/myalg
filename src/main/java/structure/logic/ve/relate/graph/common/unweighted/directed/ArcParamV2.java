package structure.logic.ve.relate.graph.common.unweighted.directed;

import java.util.Objects;

public class ArcParamV2 {
    public Character fromV;
    public Character toV;
    public BfsArcType bfsArcType;
    public DfsArcType dfsArcType;

    public ArcParamV2(Character fromV, Character toV) {
        this.fromV = fromV;
        this.toV = toV;
    }

    @Override
    public String toString() {
        return String.format("Arc:%s->%s %s %s",
                fromV, toV,
                bfsArcType == null ? "null" : bfsArcType.name(),
                dfsArcType == null ? "null" : dfsArcType.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArcParamV2 arcParam = (ArcParamV2) o;
        return fromV.equals(arcParam.fromV) && toV.equals(arcParam.toV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromV, toV);
    }

}
