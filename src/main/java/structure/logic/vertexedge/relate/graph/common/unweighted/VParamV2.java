package structure.logic.vertexedge.relate.graph.common.unweighted;

public class VParamV2 {
    public Character parent;
    public Character v;
    public Integer bfsVLevel;
    public Integer dfsVDepth;

    public VParamV2(Character parent, Character v) {
        this.parent = parent;
        this.v = v;
    }
}
