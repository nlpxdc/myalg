package structure.logic.vertexedge.relate.graph.common.unweighted;

public class VDfsDoV2 {
    Character parent;
    Character v;

    public int discoverTimeNo;
    public int finishTimeNo;

    public VDfsDoV2(Character parent, Character v) {
        this.parent = parent;
        this.v = v;
    }
}
