package graph.unweighted.undirected;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class EdgeParam {
    public Integer v;
    public Integer u;
    public BfsEdgeType bfsEdgeType;
    public DfsEdgeType dfsEdgeType;

    public EdgeParam(Integer v, Integer u) {
        this.v = v;
        this.u = u;
    }

    @Override
    public String toString() {
        return String.format("Edge:%d<->%d %s %s",
                v, u,
                bfsEdgeType == null ? "null" : bfsEdgeType.name(),
                dfsEdgeType == null ? "null" : dfsEdgeType.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeParam edgeParam = (EdgeParam) o;
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
