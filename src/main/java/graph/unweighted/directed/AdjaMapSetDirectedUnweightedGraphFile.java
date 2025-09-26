package graph.unweighted.directed;

import graph.unweighted.*;

import java.util.*;

//树和分治更有关联，关心节点中key值的大小，在这个上面做文章，
//图的话和分治关系不大？更关心节点间的关联关系，以及含有边权数据的计算问题，不太关心节点key的大小关系排序？
// 所以在做文章方面和树不太一样，所以两者各有不同用途

//第三全局视角 不是矩阵，如果是稀疏，可以转成稀疏矩阵，可以直接用（稀疏）矩阵的计算能力，解全局问题
//有向图
//图处理 连通性 路径 流 割 环 匹配 覆盖 等关联问题 网络问题
//主要处理边权 还能处理点权 需要额外数组 或者 复杂对象支持
//高维可以看到低维，二维看到一维
//没有以身入局，全局，按照二维处理，根据两个节点降维处理，索引处理，有邻接的优势，两次获取
//还有逆邻接表，表示的都是入度，正常表示的都是出度
//这里节点默认使用了Integer来定义，简单节点，反正不关心点权，这样定义图会简单，但是如果需要点权，那么节点复杂节点必须定义
//这里的第二维，的一维数组其实就是邻接节点的定义，第一维的数组，其实就是节点顶点列表，用来抓住整个图
//这里其实依旧使用了数组，没有用到列表，但是利用了Map映射算法，内部可以用数组带hash打散进行存储表示或者是树treemap都可以，不限制，这样节省了空间
//第一个维度的顶点列表，节省了描述顶点集合的空间，第二维的邻接点的邻接边（有向图）或点（无向图），节省了邻接边点的空间，超出后再扩容
//依赖hash散列，减小存储空间
class AdjaMapSetDirectedUnweightedGraphApp {
    public static void main(String[] args) {
        AdjaMapSetDirectedUnweightedGraph graph = new AdjaMapSetDirectedUnweightedGraph(9);

//        graph.addArc(0,1);
//        graph.addArc(0,2);
//        graph.addArc(1,2);
//
//        graph.addArc(0,3);
//        graph.addArc(0,4);
//        graph.addArc(3,4);
//
//        graph.addArc(1,5);
//        graph.addArc(1,6);
//        graph.addArc(5,6);
//
//        graph.addArc(2,7);
//        graph.addArc(2,8);
//        graph.addArc(7,8);

        graph.addArc(0,1);
        graph.addArc(0,2);

        graph.addArc(1,3);
        graph.addArc(1,4);
        graph.addArc(1,5);

        graph.addArc(2,6);
        graph.addArc(2,7);
        graph.addArc(2,8);

        graph.addArc(5,6);
        graph.addArc(7,6);
//        graph.addArc(6, 0);

        //bfs
        graph.traverseByBfs();

        //dfs
        graph.traverseByDfs();

//        List<Integer> topoSortList = graph.topoSort();
//        System.out.println(topoSortList);

    }
}

//无向无权图 最基础 用有向结构表示，数据必须要对称维护
//节点无需减半维护，利用对称性有好处？有去有回，方便找节点？回溯？
//或者默认前后的节点是无序的，要注意，那可以维护一端？不行，这样就减半了，不能减半
//相当于多颗树森林的遍历
class AdjaMapSetDirectedUnweightedGraph extends GraphMeta {
//    int n;
    Map<Integer, Set<Integer>> adjaMapSet;

    AdjaMapSetDirectedUnweightedGraph(int n) {
        if (n <= 0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMapSet = new HashMap<>();
        for (int v = 0; v < n; v++) {
            adjaMapSet.put(v, new HashSet<>());
        }
    }
    void addArc(int from, int to) {
        Set<Integer> uAdjaSet = adjaMapSet.get(from);
        adjaMapSet.put(from, uAdjaSet);
        uAdjaSet.add(to);
    }

    boolean hasUVArc(int from, int to) {
        Set<Integer> fromAdjaSet = adjaMapSet.get(from);
        if (fromAdjaSet != null) {
            return fromAdjaSet.contains(to);
        }
        return false;
    }

    AllVo traverseByBfs() {
        AllVo allVo = GraphUtil.traverse(this, this::bfs);
        return allVo;
    }

    AllVo traverseByDfs() {
        AllVo allVo = GraphUtil.traverse(this, this::dfs);
        return allVo;
    }

    //bfs
    SingleVo bfs(final SingleStartParam singleStartParam, final AllTemp allTemp) {
        SingleVo singleVo = new SingleVo();
        //临时队列
        Queue<VParam> queue = new LinkedList<>();

        //这里可以是任意startV n
        allTemp.visited[singleStartParam.startV] = true;
        VParam startVParam = new VParam(singleStartParam.startV);
        startVParam.bfsVLevel = 0;
        queue.offer(startVParam);

        while (!queue.isEmpty()) {
            //先访问当前自己
            VParam vParam = queue.poll();
            GraphUtil.bfsVisitV(vParam, singleVo);
            //再按层访问邻接顶点 这里没有递归，所以访问写在前后无所谓，最终都是在前
            //这里就按照顺序从小到大，从左到右即可，反过来也行，但没什么本质区别
            Set<Integer> adjaUSet = adjaMapSet.get(vParam.v);
            for (Integer adjaU : adjaUSet) {
                ArcParam arcParam = new ArcParam(vParam.v, adjaU);


                if (!allTemp.visited[adjaU]) {
                    arcParam.bfsArcType = BfsArcType.BFS_TREE_ARC;
                    allTemp.visited[adjaU] = true;
                    VParam uParam = new VParam(adjaU);
                    uParam.bfsVLevel = vParam.bfsVLevel+1;
                    queue.offer(uParam);
                } else {
                    arcParam.bfsArcType = BfsArcType.BFS_NON_TREE_ARC;
                }
                GraphUtil.bfsVisitArc(arcParam);
            }
        }
        return singleVo;
    }
    //dfs
    SingleVo dfs(final SingleStartParam singleStartParam, final AllTemp allTemp) {
        SingleVo singleVo = new SingleVo();
        VParam vParam = new VParam(singleStartParam.startV);
        vParam.dfsVDepth = 0;
        dfsRecur(vParam, allTemp, singleVo);
        return singleVo;
    }

    void dfsRecur(final VParam vParam, final AllTemp allTemp, SingleVo singleVo) {
        allTemp.visited[vParam.v] = true;
        //前序遍历
        GraphUtil.dfsDiscoverV(vParam, singleVo);
        Set<Integer> adjaUSet = adjaMapSet.get(vParam.v);
        for (Integer adjaU : adjaUSet) {
            ArcParam arcParam = new ArcParam(vParam.v, adjaU);


            if (!allTemp.visited[adjaU]) {
                arcParam.dfsArcType = DfsArcType.DFS_TREE_ARC;
                VParam uParam = new VParam(adjaU);
                uParam.dfsVDepth = vParam.dfsVDepth+1;
                dfsRecur(uParam, allTemp, singleVo);
            } else {

            }
            GraphUtil.dfsVisitArc(arcParam);
        }
        //后序遍历
//        GraphUtil.finish(v);
        GraphUtil.dfsFinishV(vParam, singleVo);
    }

    List<Integer> topoSort() {
        //计算所有节点的入度
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        for (int v = 0; v < n; v++) {
            inDegreeMap.put(v, 0);
        }
        for (int v = 0; v < n; v++) {
            Set<Integer> inUSet = adjaMapSet.get(v);
            for (Integer inU : inUSet) {
                inDegreeMap.put(inU, inDegreeMap.get(inU)+1);
            }
        }
        //所有入度为0的节点入队
        Queue<Integer> zeroInCntUQueue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            Integer uInCnt = inDegreeMap.get(u);
            if (uInCnt <= 0) {
                zeroInCntUQueue.offer(u);
            }
        }

        List<Integer> topoSortList = new LinkedList<>();
        for (int i = 0; i < n && !zeroInCntUQueue.isEmpty(); i++) {
            Integer v = zeroInCntUQueue.poll();
            topoSortList.add(v);
            //所有后继顶点入度减一
            Set<Integer> adjaUSet = adjaMapSet.get(v);
            for (Integer adjaU : adjaUSet) {
                inDegreeMap.put(adjaU, inDegreeMap.get(adjaU)-1);
                if (inDegreeMap.get(adjaU) == 0) {
                    zeroInCntUQueue.offer(adjaU);
                }
            }
        }

        if (topoSortList.size() != n) {
            return new LinkedList<>();
        }

        return topoSortList;
    }

    List<Integer> topoSortV2() {
        //计算所有节点的入度
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        adjaMapSet.forEach((v, us) -> {
            us.forEach(u -> {
                inDegreeMap.merge(u, 1, Integer::sum);
            });
        });
        //所有入度为0的节点入队
        Queue<Integer> zeroInCntUQueue = new LinkedList<>();
        inDegreeMap.forEach((u, cnt) -> {

        });
        for (int u = 0; u < n; u++) {
            Integer uInCnt = inDegreeMap.get(u);
            if (uInCnt <= 0) {
                zeroInCntUQueue.offer(u);
            }
        }

        List<Integer> topoSortList = new LinkedList<>();
        for (int i = 0; i < n && !zeroInCntUQueue.isEmpty(); i++) {
            Integer v = zeroInCntUQueue.poll();
            topoSortList.add(v);
            //所有后继顶点入度减一
            Set<Integer> adjaUSet = adjaMapSet.get(v);
            for (Integer adjaU : adjaUSet) {
                inDegreeMap.put(adjaU, inDegreeMap.get(adjaU)-1);
                if (inDegreeMap.get(adjaU) == 0) {
                    zeroInCntUQueue.offer(adjaU);
                }
            }
        }

        if (topoSortList.size() != n) {
            return new LinkedList<>();
        }

        return topoSortList;
    }

}

////有向无权图 这个重要，因为这个是结构相关的，权不影响结构
//class AdjaMapSetDirectedUnweightedGraph {
//    Map<Integer, Set<Integer>> mapSet;
//
//    //遍历 traverse
//    //BFS visited bool数组
//    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
//    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
//    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？
//    //三色标记，只判断是否，不记录顺序 这个要入度的
//    //拓扑序要记录顺序的 这个也要入度的
//
//    //单向连通 双向连通，无向连通
//    //无向底图 无向基图 弱连通 dfs bfs
//    //有向图 单向连通 单连通环 dag 单链？
//    //有向图 强连通 强连通环
//
//}

////无向有权图
//class AdjaMapMapUndirectedWeightedGraph {
//    Map<Integer, Map<Integer, Integer>> mapMap;
//}
//
////有向有权图 这个对于图来说最重要？带边权的计算
////默认都是出度
//class AdjaMapMapDirectedWeightedGraph {
//    Map<Integer, Map<Integer, Integer>> mapMap;
//
//    //遍历 traverse
//    //BFS visited bool数组
//    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
//    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
//    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？
//
//}
