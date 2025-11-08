//package graph.weighted.directed;
//
//class ArcAryGraphApp4 {
//    public static void main(String[] args) {
//        ArcAryGraph4 arcAryGraph = new ArcAryGraph4();
//
//        arcAryGraph.addVertex(0);
//        arcAryGraph.addVertex(1);
//        arcAryGraph.addVertex(2);
//
//        arcAryGraph.addArc(0,1, 7);
//        arcAryGraph.addArc(0, 2, 9);
//        arcAryGraph.addArc(1, 2, 19);
//        arcAryGraph.addArc(2, 1, 10);
//
//    }
//
//}
//
//class ArcAryGraph4 {
//    int vertexCnt;
//    int outArcCnt;
////    Vertex[] vertexAry;
//    int[] allVertexNoAry;
//    Arc[] allOutArcAry;
//
//    public ArcAryGraph4() {
//        this.vertexCnt = 0;
//        this.outArcCnt = 0;
//        this.allVertexNoAry = new int[100000];
//        this.allOutArcAry = new Arc[100000];
//    }
//
//    void addVertex(int vertexNo) {
//        vertexCnt++;
//        allVertexNoAry[vertexCnt-1] = vertexNo;
//    }
//
//    void addArc(int from, int to, int weight) {
//        outArcCnt++;
//        Arc arc = new Arc(from, to, weight);
//        allOutArcAry[outArcCnt-1] = arc;
//    }
//
//}
