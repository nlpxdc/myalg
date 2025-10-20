package graph.unweighted;

public class VDfsDo {
    Integer parent;
    Integer v;

    public int discoverTime;
    public int finishTime;

    public VDfsDo(Integer parent, Integer v) {
        this.parent = parent;
        this.v = v;
    }
}
