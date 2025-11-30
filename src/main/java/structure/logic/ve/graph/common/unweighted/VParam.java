package structure.logic.ve.graph.common.unweighted;

public class VParam {
    public Integer parent;
    public Integer v;
    public Integer bfsVLevel;
    public Integer dfsVDepth;

    public VParam(Integer parent, Integer v) {
        this.parent = parent;
        this.v = v;
    }
}
