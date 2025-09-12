package graph.unweighted;

import java.util.List;
import java.util.Map;
//import java.util.Set;

public class SingleVo {
    //real param from out
    Integer startV;

    //as result temp var to record content
    boolean[] visited;
//    private Set<Integer> visited; 起始List也可以？用distinct？但是依赖算法可以O(1)

    List<Integer> bfsList;
    //时间序
    List<Integer> dfsDiscoverList;
    List<Integer> dfsFinishList;
    List<String> dfsDiscoverFinishList;
    //点维度聚合角度，根据内部字段算出上面三种序
    Map<Integer, VDfsDo> dfsVDfsDo;

}
