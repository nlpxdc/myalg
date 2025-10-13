package graph.unweighted;

import graph.unweighted.directed.ArcParam;
import graph.unweighted.undirected.EdgeParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.Set;

public class SingleVo {
    //real param from out
//    Integer startV;
//    public final int n;
    public final Long dfsVStartNanoTime;

    //as result temp var to record content
//    public final boolean[] visited;
//    private Set<Integer> visited; 起始List也可以？用distinct？但是依赖算法可以O(1)

    public final List<VParam> bfsVList;
    //时间序
    public final List<Integer> dfsVDiscoverList;
    public final List<Integer> dfsVFinishList;
    public final List<String> dfsVDiscoverFinishList;
    //点维度聚合角度，根据内部字段算出上面三种序
    public final Map<Integer, VDfsDo> dfsVVDfsDoMap;

    public final List<EdgeParam> bfsEdgeList;
    public final List<EdgeParam> dfsEdgeList;
    public final List<ArcParam> bfsArcList;
    public final List<ArcParam> dfsArcList;

    public SingleVo() {
//        this.n = n;
//        visited = new boolean[n];
        dfsVStartNanoTime = System.nanoTime();

        bfsVList = new LinkedList<>();
        dfsVDiscoverList = new LinkedList<>();
        dfsVFinishList = new LinkedList<>();
        dfsVDiscoverFinishList = new LinkedList<>();

        dfsVVDfsDoMap = new HashMap<>();

        bfsEdgeList = new LinkedList<>();
        dfsEdgeList = new LinkedList<>();
        bfsArcList = new LinkedList<>();
        dfsArcList = new LinkedList<>();
    }

}
