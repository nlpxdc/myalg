package structure.logic.vertexedge.relate.graph.common.weighted.directed;

public class Vertex {
    int vertexNo;
    int outArcCnt;
    Arc[] outArcAry;

    public Vertex(int vertexNo) {
        this.vertexNo = vertexNo;
        this.outArcCnt = 0;
        this.outArcAry = new Arc[100000];
    }

}
