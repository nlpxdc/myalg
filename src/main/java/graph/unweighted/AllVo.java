package graph.unweighted;

import java.util.LinkedList;
import java.util.List;

public class AllVo {
    int childGraphCnt;

    //一下可以再算一次计算属性 平铺称一维的线性结构

    List<List<Integer>> bfsListList;

    List<List<Integer>> dfsDiscoverListList;
    List<List<Integer>> dfsFinishListList;
    List<List<String>> dfsDiscoverFinishListList;

    public AllVo() {
        bfsListList = new LinkedList<>();

        dfsDiscoverListList = new LinkedList<>();
        dfsFinishListList = new LinkedList<>();
        dfsDiscoverFinishListList = new LinkedList<>();
    }

}
