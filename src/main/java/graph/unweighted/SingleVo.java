package graph.unweighted;

import graph.unweighted.directed.ArcParam;
import graph.unweighted.directed.BfsArcType;
import graph.unweighted.directed.DfsArcType;
import graph.unweighted.undirected.BfsEdgeType;
import graph.unweighted.undirected.DfsEdgeType;
import graph.unweighted.undirected.EdgeParam;

import java.util.*;
//import java.util.Set;

public class SingleVo {
//    public final Boolean directed;
    public Boolean directed;
    //real param from out
//    Integer startV;
//    public final int n;
//    public final Long dfsVStartNanoTime;

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

//    public SingleVo(Boolean directed) {
    public SingleVo() {
//        this.directed = directed;
//        this.n = n;
//        visited = new boolean[n];
//        dfsVStartNanoTime = System.nanoTime();
//        dfsVStartNanoTime = 0L;

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

    public Boolean undigraphBeCyclic() {
        if (directed == null) {
            throw new RuntimeException("server error");
        }
        if (directed) {
//            throw new RuntimeException("server error");
            return null;
        } else {
            if (bfsEdgeList != null && !bfsEdgeList.isEmpty()) {
                for (EdgeParam edgeParam : bfsEdgeList) {
                    if (!edgeParam.bfsEdgeType.equals(BfsEdgeType.BFS_TREE_EDGE)) {
                        return true;
                    }
                }
                return false;
            }
            if (dfsEdgeList != null && !dfsEdgeList.isEmpty()) {
                for (EdgeParam edgeParam : dfsEdgeList) {
                    if (!edgeParam.dfsEdgeType.equals(DfsEdgeType.DFS_TREE_EDGE)) {
                        return true;
                    }
                }
                return false;
            }
            return null;
        }
    }

    public Boolean digraphBeCyclic() {
        if (directed == null) {
            throw new RuntimeException("server error");
        }
        if (directed) {
            //bfs区分不了
//            for (ArcParam arcParam : bfsArcList) {
//                if (!arcParam.bfsArcType.equals(BfsArcType.BFS_TREE_ARC)) {
//                    return true;
//                }
//            }
            if (dfsArcList != null && !dfsArcList.isEmpty()) {
                for (ArcParam arcParam : dfsArcList) {
//                if (!arcParam.dfsArcType.equals(DfsArcType.DFS_TREE_ARC)) {
                    if (arcParam.dfsArcType.equals(DfsArcType.DFS_BACKWARD_ARC)) {
                        return true;
                    }
                }
                return false;
            }
            return null;
        } else {
//            throw new RuntimeException("server error");
            return null;
        }
    }

}
