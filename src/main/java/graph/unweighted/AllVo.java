package graph.unweighted;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AllVo {
    Boolean directed;
    //弱连通（有向转无向后的连通）
    Boolean undirectedCyclic;
    //强连通
    Boolean directedCyclic;

    int childGraphCnt;

    //一下可以再算一次计算属性 平铺称一维的线性结构

    final List<List<VParam>> bfsListList;

    final List<List<Integer>> dfsDiscoverListList;
    final List<List<Integer>> dfsFinishListList;
    final List<List<String>> dfsDiscoverFinishListList;
    final List<Map<Integer, VDfsDo>> dfsVDfsDoMapList;

    public AllVo() {
        bfsListList = new LinkedList<>();

        dfsDiscoverListList = new LinkedList<>();
        dfsFinishListList = new LinkedList<>();
        dfsDiscoverFinishListList = new LinkedList<>();
        dfsVDfsDoMapList = new LinkedList<>();
    }

}
