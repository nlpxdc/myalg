package graph.unweighted;

public class VDfsDo {
    Integer parent;
    Integer v;

    public Long discoverTime;
    public Long finishTime;

    public VDfsDo(Integer parent, Integer v) {
        this.parent = parent;
        this.v = v;
    }
}
