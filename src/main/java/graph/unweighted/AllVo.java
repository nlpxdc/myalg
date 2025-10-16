package graph.unweighted;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AllVo {


    int childGraphCnt;

    //一下可以再算一次计算属性 平铺称一维的线性结构

    final List<List<VParam>> bfsListList;

    final List<List<Integer>> dfsDiscoverListList;
    final List<List<Integer>> dfsFinishListList;
    final List<List<String>> dfsDiscoverFinishListList;
    final List<Map<Integer, VDfsDo>> dfsVDfsDoMapList;

    final List<Boolean> directedList;
    //弱连通环（有向转无向后的连通）
    final List<Boolean> undirectedCyclicList;
    //强连通环
    final List<Boolean> directedCyclicList;

    public AllVo() {
        bfsListList = new LinkedList<>();

        dfsDiscoverListList = new LinkedList<>();
        dfsFinishListList = new LinkedList<>();
        dfsDiscoverFinishListList = new LinkedList<>();
        dfsVDfsDoMapList = new LinkedList<>();

        directedList = new LinkedList<>();
        undirectedCyclicList = new LinkedList<>();
        directedCyclicList = new LinkedList<>();
    }

    public Boolean directed() {
        long cnt = directedList.stream().distinct().count();
        if (cnt > 1) {
            throw new RuntimeException("server error, pls check");
        }
        if (cnt < 1) {
            throw new RuntimeException("server error, pls check");
        }
        return directedList.get(0);
    }

}
