//package graph.weighted.directed;
//
//class ArcAryGraphApp {
//    public static void main(String[] args) {
//        ArcAryGraph arcAryGraph = new ArcAryGraph(9);
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
//class ArcAryGraph {
//    int vN;
//    int arcN;
//    Arc[] arcs;
//
//    public ArcAryGraph(int n) {
//        this.vN = n;
//        this.arcN = 0;
//        this.arcs = new Arc[1000];
//    }
//
//    void addArc(Arc arc) {
//        arcs[arcN++] = arc;
//    }
//
//}
