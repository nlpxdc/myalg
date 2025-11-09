package graph.unweighted.directed;

import graph.unweighted.AllVo;
import graph.unweighted.GraphMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ChainForwardStarGraphApp {
    public static void main(String[] args) {
        ChainForwardStarGraph chainForwardStarGraph = new ChainForwardStarGraph(3);

        chainForwardStarGraph.addArc(0,1);
        chainForwardStarGraph.addArc(0,2);
        chainForwardStarGraph.addArc(1,2);
        chainForwardStarGraph.addArc(2,1);
    }

}

class ChainForwardStarGraph extends GraphMeta {
    int outArcCnt;
    int[] vertexOutArcsHeadIdxAry;
    OutArc[] outArcAry;

    public ChainForwardStarGraph(int n) {
        this.n = n;
        this.vertexOutArcsHeadIdxAry = new int[n];
        Arrays.fill(this.vertexOutArcsHeadIdxAry, -1);
        this.outArcAry = new OutArc[100000];
    }

    void addArc(int from, int to) {
        outArcCnt++;
        int origFromHeadIdx = vertexOutArcsHeadIdxAry[from];
        OutArc outArc = new OutArc(to, origFromHeadIdx);
        outArcAry[outArcCnt-1] = outArc;
        vertexOutArcsHeadIdxAry[from] = outArcCnt-1;
    }

    @Override
    public AllVo allTraverseByBfs() {
        return null;
    }

    @Override
    public AllVo allTraverseByDfs() {
        return null;
    }

    @Override
    public Map<Integer, Integer> calcInDegreeMap() {
        return null;
    }

    @Override
    public List<Integer> allTopoOrderByBfs() {
        return null;
    }

    @Override
    public List<Integer> allTopoOrderByDfs() {
        return null;
    }

    //写下bfs和dfs，应该可以继承元图抽象类

}
