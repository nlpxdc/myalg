package graph.weighted.directed;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ArcAryGraphApp3 {
    public static void main(String[] args) {
        ArcAryGraph3 arcAryGraph = new ArcAryGraph3();

        arcAryGraph.addVertex(0);
        arcAryGraph.addVertex(1);
        arcAryGraph.addVertex(2);

        arcAryGraph.addArc(0,1, 7);
        arcAryGraph.addArc(0, 2, 9);
        arcAryGraph.addArc(1, 2, 19);
        arcAryGraph.addArc(2, 1, 10);

    }

}

class ArcAryGraph3 {
    int vertexCnt;
    int arcCnt;
    Vertex[] vertexAry;

    public ArcAryGraph3() {
        this.vertexCnt = 0;
        this.arcCnt = 0;
        this.vertexAry = new Vertex[100000];
    }

    void addVertex(int vertexNo) {
        vertexCnt++;
        Vertex vertex = new Vertex(vertexNo);
        vertexAry[vertexCnt-1] = vertex;
    }

    void addArc(int from, int to, int weight) {
        arcCnt++;
        List<Vertex> list = Arrays.stream(vertexAry)
                .filter(Objects::nonNull)
                .filter(vertex -> vertex.vertexNo == from)
                .collect(Collectors.toList());
        Vertex vertex = list.get(0);
        vertex.outArcCnt++;
        Arc arc = new Arc(from, to, weight);
        vertex.outArcAry[vertex.outArcCnt-1] = arc;
    }

}
