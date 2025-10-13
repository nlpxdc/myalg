package graph.unweighted;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.Set;

public class SingleVo {
    //real param from out
//    Integer startV;
//    public final int n;
    public final Long vStartNanoTime;

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

//    public final List<EdgeParam> bfsEdgeList;

    public SingleVo() {
//        this.n = n;
//        visited = new boolean[n];
        vStartNanoTime = System.nanoTime();

        bfsVList = new LinkedList<>();
        dfsVDiscoverList = new LinkedList<>();
        dfsVFinishList = new LinkedList<>();
        dfsVDiscoverFinishList = new LinkedList<>();

        dfsVVDfsDoMap = new HashMap<>();
    }

}
