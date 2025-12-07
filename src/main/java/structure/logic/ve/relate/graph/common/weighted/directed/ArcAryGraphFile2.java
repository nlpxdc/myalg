//package graph.weighted.directed;
//
//class ArcAryGraphApp2 {
//    public static void main(String[] args) {
//        ArcAryGraph2 arcAryGraph = new ArcAryGraph2();
//
//        arcAryGraph.addVertex("0");
//        arcAryGraph.addVertex("1");
//        arcAryGraph.addVertex("2");
//
//        Arc arc01 = new Arc("0","1", 7);
//        arcAryGraph.addArc(arc01);
//        Arc arc02 = new Arc("0", "2", 9);
//        arcAryGraph.addArc(arc02);
//        Arc arc12 = new Arc("1", "2", 19);
//        arcAryGraph.addArc(arc12);
//        Arc arc21 = new Arc("2", "1", 10);
//        arcAryGraph.addArc(arc21);
//
//    }
//
//}
//
//class ArcAryGraph2 {
//    int vertexCnt;
//    int arcCnt;
//    String[] vertexAry;
//    Arc[] arcAry;
//
//    public ArcAryGraph2() {
//        this.vertexCnt = 0;
//        this.arcCnt = 0;
//        this.vertexAry = new String[1000];
//        this.arcAry = new Arc[1000];
//    }
//
//    void addVertex(String vertexNo) {
//        vertexAry[vertexCnt++] = vertexNo;
//    }
//
//    void addArc(Arc arc) {
//        arcAry[arcCnt++] = arc;
//    }
//
//}
