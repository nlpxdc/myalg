package graph.unweighted.directed;

import graph.unweighted.*;

import java.util.*;

class ChainForwardStarGraphApp {
    public static void main(String[] args) {
        ChainForwardStarGraph graph = new ChainForwardStarGraph(9);

        graph.addArc(0,1);
        graph.addArc(0,2);
        graph.addArc(1,2);
        graph.addArc(2,1);

        graph.addArc(0,3);
//        graph.addArc(0,4);
        graph.addArc(3,4);
        graph.addArc(4,0);

        graph.addArc(1,5);
        graph.addArc(1,6);
        graph.addArc(5,6);

        graph.addArc(2,7);
        graph.addArc(2,8);
        graph.addArc(7,8);
        graph.addArc(8,7);

        graph.addArc(7,4);

        AllVo allVo = graph.allTraverseByBfs();

    }

}

class ChainForwardStarGraph extends GraphMeta {
    int outArcCnt;
    int[] vertexOutArcsHeadIdxAry;
    OutArc[] outArcAry;

    public ChainForwardStarGraph(int n) {
        this.n = n;
        this.vertexOutArcsHeadIdxAry = new int[n];
        Arrays.fill(this.vertexOutArcsHeadIdxAry, -1);
        this.outArcAry = new OutArc[100000];
    }

    void addArc(int from, int to) {
        outArcCnt++;
        int origFromHeadIdx = vertexOutArcsHeadIdxAry[from];
        OutArc outArc = new OutArc(to, origFromHeadIdx);
        outArcAry[outArcCnt-1] = outArc;
        vertexOutArcsHeadIdxAry[from] = outArcCnt-1;
    }

    @Override
    public AllVo allTraverseByBfs() {
        AllVo allVo = new AllVo();
        GraphUtil.allTraverse(this, this::singleTraverseByBfs, allVo);
        return allVo;
    }

    @Override
    public AllVo allTraverseByDfs() {
        return null;
    }

    @Override
    public Map<Integer, Integer> calcInDegreeMap() {
        return null;
    }

    @Override
    public List<Integer> allTopoOrderByBfs() {
        return null;
    }

    @Override
    public List<Integer> allTopoOrderByDfs() {
        return null;
    }

    //写下bfs和dfs，应该可以继承元图抽象类
    //bfs
    void singleTraverseByBfs(final VParam singleStartVParam,
                             final SingleTemp singleTemp,
                             final AllTemp allTemp,
                             final SingleVo singleVo,
                             final AllVo allVo) {
        singleVo.directed = true;
        //临时队列
        Queue<VParam> queue = new LinkedList<>();

        //这里可以是任意startV n
        allTemp.visited[singleStartVParam.v] = true;
        allTemp.parents[singleStartVParam.v] = null;
        singleStartVParam.bfsVLevel = 0;
        queue.offer(singleStartVParam);

        while (!queue.isEmpty()) {
            //先访问当前自己
            VParam vParam = queue.poll();
            GraphUtil.bfsVisitV(vParam, singleTemp, allTemp, singleVo, allVo);
            //再按层访问邻接顶点 这里没有递归，所以访问写在前后无所谓，最终都是在前
            //这里就按照顺序从小到大，从左到右即可，反过来也行，但没什么本质区别

            //根据顶点v获取v的所有邻接节点(出顶点)（通过邻接出边弧arc）
//            Set<Integer> adjaUSet = adjaMapSet.get(vParam.v);
            int vOutArcsHeadIdx = vertexOutArcsHeadIdxAry[vParam.v];
            if (vOutArcsHeadIdx < 0) {
                continue;
            }
            OutArc currOutArc = outArcAry[vOutArcsHeadIdx];
            for (int i = 0;
                 i < outArcCnt && currOutArc != null;
                 i++, currOutArc = currOutArc.nextIdx < 0 ? null : outArcAry[currOutArc.nextIdx]) {

                Integer adjaU = currOutArc.to;
                ArcParam arcParam = new ArcParam(vParam.v, adjaU);

                if (!allTemp.visited[adjaU]) {
                    arcParam.bfsArcType = BfsArcType.BFS_TREE_ARC;
                    GraphUtil.bfsVisitArc(arcParam, singleVo);
                    allTemp.visited[adjaU] = true;
                    VParam uParam = new VParam(vParam.v, adjaU);
                    allTemp.parents[adjaU] = vParam.v;
                    uParam.bfsVLevel = vParam.bfsVLevel+1;
                    queue.offer(uParam);
                } else {
                    arcParam.bfsArcType = BfsArcType.BFS_NON_TREE_ARC;
                    GraphUtil.bfsVisitArc(arcParam, singleVo);
                }

            }

        }
    }

}
