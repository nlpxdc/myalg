package structure.logic.ve.graph.common.weighted.directed;

public class OutArc {
    int to;
    int weight;
    int nextIdx;

    public OutArc(int to, int weight, int nextIdx) {
        this.to = to;
        this.weight = weight;
        this.nextIdx = nextIdx;
    }
}
