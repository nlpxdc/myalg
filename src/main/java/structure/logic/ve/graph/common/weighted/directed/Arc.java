package structure.logic.ve.graph.common.weighted.directed;

public class Arc {
//    String from;
//    String to;
    int from;
    int to;
    int weight;
    int nextIdx;

//    public Arc(String from , String to, int weight) {
//        this.from = from;
//        this.to = to;
//        this.weight = weight;
//    }

    public Arc(int from , int to, int weight, int nextIdx) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.nextIdx = nextIdx;
    }

}
