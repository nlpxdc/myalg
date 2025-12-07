package structure.logic.ve.relate.graph.common.unweighted;

public class VDfsDo {
    Integer parent;
    Integer v;

    public int discoverTimeNo;
    public int finishTimeNo;

    public VDfsDo(Integer parent, Integer v) {
        this.parent = parent;
        this.v = v;
    }
}
