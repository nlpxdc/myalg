package structure.logic.graph.weighted.directed;

import java.util.Arrays;

class ArcAryGraphApp6 {
    public static void main(String[] args) {
        ArcAryGraph6 arcAryGraph = new ArcAryGraph6(3);

        arcAryGraph.addArc(0,1, 7);
        arcAryGraph.addArc(0, 2, 9);
        arcAryGraph.addArc(1, 2, 19);
        arcAryGraph.addArc(2, 1, 10);

    }

}

class ArcAryGraph6 {
    int n;
    int outArcCnt;
    int[] vertexOutArcsHeadIdxAry;
    OutArc[] outArcAry;

    public ArcAryGraph6(int n) {
        this.n = n;
        this.vertexOutArcsHeadIdxAry = new int[n];
        Arrays.fill(this.vertexOutArcsHeadIdxAry, -1);
        this.outArcAry = new OutArc[100000];
    }

    void addArc(int from, int to, int weight) {
        outArcCnt++;
        int origFromHeadIdx = vertexOutArcsHeadIdxAry[from];
        OutArc outArc = new OutArc(to, weight, origFromHeadIdx);
        outArcAry[outArcCnt-1] = outArc;
        vertexOutArcsHeadIdxAry[from] = outArcCnt-1;
    }

    //写下bfs和dfs，应该可以继承元图抽象类

}
