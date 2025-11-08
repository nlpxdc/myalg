package graph.weighted.directed;

public class Arc {
    String from;
    String to;
    int weight;

    public Arc(String from , String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
