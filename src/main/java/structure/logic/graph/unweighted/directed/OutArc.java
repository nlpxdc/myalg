package structure.logic.graph.unweighted.directed;

public class OutArc {
    int to;
    int nextIdx;

    public OutArc(int to, int nextIdx) {
        this.to = to;
        this.nextIdx = nextIdx;
    }
}
