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

    //as result temp var to record content
//    public final boolean[] visited;
//    private Set<Integer> visited; 起始List也可以？用distinct？但是依赖算法可以O(1)

    public List<VParam> bfsList;
    //时间序
    public final List<Integer> dfsDiscoverList;
    public final List<Integer> dfsFinishList;
    public final List<String> dfsDiscoverFinishList;
    //点维度聚合角度，根据内部字段算出上面三种序
    public final Map<Integer, VDfsDo> dfsVDfsDoMap;

    public SingleVo() {
//        this.n = n;
//        visited = new boolean[n];

        bfsList = new LinkedList<>();
        dfsDiscoverList = new LinkedList<>();
        dfsFinishList = new LinkedList<>();
        dfsDiscoverFinishList = new LinkedList<>();

        dfsVDfsDoMap = new HashMap<>();
    }

}
