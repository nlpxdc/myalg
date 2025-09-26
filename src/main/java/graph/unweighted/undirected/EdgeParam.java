package graph.unweighted.undirected;

public class EdgeParam {
    public Integer smallV;
    public Integer bigV;

    public EdgeParam(Integer v, Integer u) {
        if (v == null || u == null) {
            throw new RuntimeException("v or u null");
        }
        if (v < u) {
            this.smallV = v;
            this.bigV = u;
        } else if (v > u){
            this.smallV = u;
            this.bigV = v;
        } else {
            throw new RuntimeException("v cannot equals u");
        }
    }

    @Override
    public String toString() {
        return String.format("Edge:%d->%d", smallV, bigV);
    }

}
