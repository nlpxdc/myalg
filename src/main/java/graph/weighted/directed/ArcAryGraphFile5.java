//package graph.weighted.directed;
//
//import java.util.Arrays;
//
//class ArcAryGraphApp5 {
//    public static void main(String[] args) {
//        ArcAryGraph5 arcAryGraph = new ArcAryGraph5(3);
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
//class ArcAryGraph5 {
//    int n;
//    int outArcCnt;
//    int[] vertexArcsHeadIdxAry;
//    Arc[] outArcAry;
//
//    public ArcAryGraph5(int n) {
//        this.n = n;
//        this.vertexArcsHeadIdxAry = new int[n];
//        Arrays.fill(this.vertexArcsHeadIdxAry, -1);
//        this.outArcAry = new Arc[100000];
//    }
//
//    void addArc(int from, int to, int weight) {
//        outArcCnt++;
//        int origFromHeadIdx = vertexArcsHeadIdxAry[from];
//        Arc arc = new Arc(from, to, weight, origFromHeadIdx);
//        outArcAry[outArcCnt-1] = arc;
//        vertexArcsHeadIdxAry[from] = outArcCnt-1;
//    }
//
//}
