package graph.unweighted.undirected;

public class EdgeParam {
    public Integer v;
    public Integer u;

    public EdgeParam(Integer v, Integer u) {
        this.v = v;
        this.u = u;
    }

    @Override
    public String toString() {
        return String.format("Edge:%d<->%d", v, u);
    }

}
