package structure.logic.ve.graph.common.unweighted.directed;

import structure.logic.ve.graph.common.unweighted.*;

import java.util.*;

class ChainForwardStarGraphAppV2 {
    public static void main(String[] args) {
        ChainForwardStarGraphV2 graph = new ChainForwardStarGraphV2(9);

        graph.addArc('H','E');

        graph.addArc('I','H');
        graph.addArc('H','I');
        graph.addArc('C','I');
        graph.addArc('C','H');

        graph.addArc('F','G');
        graph.addArc('B','G');
        graph.addArc('B','F');

        graph.addArc('E','A');
        graph.addArc('D','E');
        graph.addArc('A','D');

        graph.addArc('C','B');
        graph.addArc('B','C');
        graph.addArc('A','C');
        graph.addArc('A','B');

        //--------------------------------------

        AllVoV2 allVo = graph.allTraverseByBfs();

//        AllVo allVo = graph.allTraverseByDfs();

    }

}

class ChainForwardStarGraphV2 extends GraphMetaV2 {
    int outArcCnt;
    int[] vertexOutArcsHeadIdxAry;
//    OutArc[] outArcAry;
    char[] toAry;
    int[] nextIdxAry;

    public ChainForwardStarGraphV2(int n) {
        this.n = n;
        this.vertexOutArcsHeadIdxAry = new int[n];
        Arrays.fill(this.vertexOutArcsHeadIdxAry, -1);
//        this.outArcAry = new OutArc[100000];
        int max = 100000;
        toAry = new char[max];
        nextIdxAry = new int[max];
    }

    @Override
    public void addArc(Character from, Character to) {
        outArcCnt++;
        int origFromHeadIdx = vertexOutArcsHeadIdxAry[from];
//        OutArc outArc = new OutArc(to, origFromHeadIdx);
//        outArcAry[outArcCnt-1] = outArc;
        toAry[outArcCnt-1] = to;
        nextIdxAry[outArcCnt-1] = origFromHeadIdx;
        vertexOutArcsHeadIdxAry[from] = outArcCnt-1;
    }

    @Override
    public void addEdge(Character u, Character v) {
        throw new RuntimeException("not supported");
    }

    @Override
    public AllVoV2 allTraverseByBfs() {
        AllVoV2 allVo = new AllVoV2();
        GraphUtilV2.allTraverse(this, this::singleTraverseByBfs, allVo);
        return allVo;
    }

    @Override
    public AllVoV2 allTraverseByDfs() {
        AllVoV2 allVo = new AllVoV2();
        GraphUtilV2.allTraverse(this, this::singleTraverseByDfs, allVo);
        return allVo;
    }

    @Override
    public Map<Character, Integer> calcInDegreeMap() {
        return null;
    }

    @Override
    public List<Character> allTopoOrderByBfs() {
        return null;
    }

    @Override
    public List<Character> allTopoOrderByDfs() {
        return null;
    }

    //写下bfs和dfs，应该可以继承元图抽象类
    //bfs
    void singleTraverseByBfs(final VParamV2 singleStartVParam,
                             final SingleTemp singleTemp,
                             final AllTempV2 allTemp,
                             final SingleVoV2 singleVo,
                             final AllVoV2 allVo) {
        singleVo.directed = true;
        //临时队列
        Queue<VParamV2> queue = new LinkedList<>();

        //这里可以是任意startV n
        allTemp.visited[singleStartVParam.v] = true;
        allTemp.parents[singleStartVParam.v] = null;
        singleStartVParam.bfsVLevel = 0;
        queue.offer(singleStartVParam);

        while (!queue.isEmpty()) {
            //先访问当前自己
            VParamV2 vParam = queue.poll();
            GraphUtilV2.bfsVisitV(vParam, singleTemp, allTemp, singleVo, allVo);
            //再按层访问邻接顶点 这里没有递归，所以访问写在前后无所谓，最终都是在前
            //这里就按照顺序从小到大，从左到右即可，反过来也行，但没什么本质区别

            //根据顶点v获取v的所有邻接节点(出顶点)（通过邻接出边弧arc）
//            Set<Integer> adjaUSet = adjaMapSet.get(vParam.v);
            int vOutArcsHeadIdx = vertexOutArcsHeadIdxAry[vParam.v];
            if (vOutArcsHeadIdx < 0) {
                continue;
            }
//            OutArc currOutArc = outArcAry[vOutArcsHeadIdx];
            char currTo = toAry[vOutArcsHeadIdx];
            int currNextIdx = nextIdxAry[vOutArcsHeadIdx];
            for (int i = 0;
//                 i < outArcCnt && currOutArc != null;
                 i < outArcCnt && currTo>0;
//                 i++, currOutArc = currOutArc.nextIdx < 0 ? null : outArcAry[currOutArc.nextIdx]) {
                 i++, currNextIdx = nextIdxAry[currNextIdx], currTo = toAry[currNextIdx]) {

//                Integer adjaU = currOutArc.to;
                Character adjaU = currTo;
                ArcParamV2 arcParam = new ArcParamV2(vParam.v, adjaU);

                if (!allTemp.visited[adjaU]) {
                    arcParam.bfsArcType = BfsArcType.BFS_TREE_ARC;
                    GraphUtilV2.bfsVisitArc(arcParam, singleVo);
                    allTemp.visited[adjaU] = true;
                    VParamV2 uParam = new VParamV2(vParam.v, adjaU);
                    allTemp.parents[adjaU] = vParam.v;
                    uParam.bfsVLevel = vParam.bfsVLevel+1;
                    queue.offer(uParam);
                } else {
                    arcParam.bfsArcType = BfsArcType.BFS_NON_TREE_ARC;
                    GraphUtilV2.bfsVisitArc(arcParam, singleVo);
                }

            }

        }
    }

    //dfs
    void singleTraverseByDfs(final VParamV2 singleStartVParam,
                             final SingleTemp singleTemp,
                             final AllTempV2 allTemp,
                             final SingleVoV2 singleVo,
                             final AllVoV2 allVo) {
        singleVo.directed = true;
        allTemp.parents[singleStartVParam.v] = null;
        singleStartVParam.dfsVDepth = 0;
        //这个等价于循环迭代
        singleTraverseByDfsRecur(singleStartVParam, singleTemp, allTemp, singleVo, allVo);
    }

    //这个等价于循环迭代
    void singleTraverseByDfsRecur(final VParamV2 vParam,
                                  final SingleTemp singleTemp,
                                  final AllTempV2 allTemp,
                                  final SingleVoV2 singleVo,
                                  final AllVoV2 allVo) {
        allTemp.visited[vParam.v] = true;
        //前序遍历
        GraphUtilV2.dfsDiscoverV(vParam, singleTemp, allTemp, singleVo, allVo);
        allTemp.vStatuses[vParam.v] = VStatus.GRAY;

        //根据顶点v获取v的所有邻接节点(出顶点)（通过邻接出边弧arc）
//            Set<Integer> adjaUSet = adjaMapSet.get(vParam.v);
        int vOutArcsHeadIdx = vertexOutArcsHeadIdxAry[vParam.v];
        if (vOutArcsHeadIdx < 0) {
            return;
        }
//        OutArc currOutArc = outArcAry[vOutArcsHeadIdx];
        char currTo = toAry[vOutArcsHeadIdx];
        int currNextIdx = nextIdxAry[vOutArcsHeadIdx];
        for (int i = 0;
//             i < outArcCnt && currOutArc != null;
             i < outArcCnt && currTo>0;
//             i++, currOutArc = currOutArc.nextIdx < 0 ? null : outArcAry[currOutArc.nextIdx]) {
             i++, currNextIdx = nextIdxAry[currNextIdx], currTo = toAry[currNextIdx]) {

//            Integer adjaU = currOutArc.to;
            Character adjaU = currTo;
            ArcParamV2 arcParam = new ArcParamV2(vParam.v, adjaU);

            if (!allTemp.visited[adjaU]) {
                arcParam.dfsArcType = DfsArcType.DFS_TREE_ARC;
                GraphUtilV2.dfsVisitArc(arcParam, singleVo);
                VParamV2 uParam = new VParamV2(vParam.v, adjaU);
                allTemp.parents[adjaU] = vParam.v;
                uParam.dfsVDepth = vParam.dfsVDepth+1;
                singleTraverseByDfsRecur(uParam, singleTemp, allTemp, singleVo, allVo);
            } else {
                if (allTemp.vStatuses[adjaU] == VStatus.GRAY) {
                    arcParam.dfsArcType = DfsArcType.DFS_BACKWARD_ARC;
                    GraphUtilV2.dfsVisitArc(arcParam, singleVo);
                } else if (allTemp.vStatuses[adjaU] == VStatus.BLACK) {
                    int vDiscoverTime = singleVo.dfsVVDfsDoMap.get(vParam.v).discoverTimeNo;
                    int adjUDiscoverTime = singleVo.dfsVVDfsDoMap.get(adjaU).discoverTimeNo;
                    if (vDiscoverTime < adjUDiscoverTime) {
                        arcParam.dfsArcType = DfsArcType.DFS_FORWARD_ARC;
                        GraphUtilV2.dfsVisitArc(arcParam, singleVo);
                    } else if (vDiscoverTime > adjUDiscoverTime){
                        arcParam.dfsArcType = DfsArcType.DFS_CROSS_ARC;
                        GraphUtilV2.dfsVisitArc(arcParam, singleVo);
                    } else {
                        throw new RuntimeException("impossible1");
                    }
                } else {
                    throw new RuntimeException("impossible2");
                }
            }

        }

        //后序遍历
//        GraphUtil.finish(v);
        GraphUtilV2.dfsFinishV(vParam, singleTemp, allTemp, singleVo, allVo);
        allTemp.vStatuses[vParam.v] = VStatus.BLACK;
    }

}
