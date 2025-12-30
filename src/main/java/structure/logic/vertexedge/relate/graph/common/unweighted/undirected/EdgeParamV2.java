package structure.logic.vertexedge.relate.graph.common.unweighted.undirected;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class EdgeParamV2 {
    public Character v;
    public Character u;
    public BfsEdgeType bfsEdgeType;
    public DfsEdgeType dfsEdgeType;

    public EdgeParamV2(Character v, Character u) {
        this.v = v;
        this.u = u;
    }

    @Override
    public String toString() {
        return String.format("Edge:%s<->%s %s %s",
                v, u,
                bfsEdgeType == null ? "null" : bfsEdgeType.name(),
                dfsEdgeType == null ? "null" : dfsEdgeType.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeParamV2 edgeParam = (EdgeParamV2) o;
        int[] ary1 = IntStream.of(v,u).sorted().toArray();
        int[] ary2 = IntStream.of(edgeParam.v,edgeParam.u).sorted().toArray();
        boolean beEq = Arrays.equals(ary1, ary2);
        return beEq;
    }

    @Override
    public int hashCode() {
//        int[] ary = {v,u};
//        Arrays.sort(ary);
        int[] ary = IntStream.of(v, u).sorted().toArray();
        return Objects.hash(ary);
    }

}
