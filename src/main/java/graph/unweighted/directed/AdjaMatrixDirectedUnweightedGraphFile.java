package graph.unweighted.directed;

import graph.unweighted.*;

import java.util.LinkedList;
import java.util.Queue;

//第三他人全局视角 是矩阵 如果是稠密，直接用，不用转稀疏矩阵，直接矩阵计算，解全局问题
//二维数组的表示，表示图
//简单图 多重图？ 二维ary 二维vector 冗余视图结构
//有向图
//高维可以看到低维，二维看到一维
//没有以身入局，全局，按照二维处理，根据两个节点降维处理，索引处理，有邻接的优势，两次获取
//逆邻接矩阵，就是邻接矩阵的转置
//总体用的少
//这里节点默认使用了Integer来定义，简单节点，反正不关心点权，这样定义图会简单，但是如果需要点权，那么节点复杂节点必须定义
//这里的第二维，的一维数组其实就是邻接节点的定义，第一维的数组，其实就是节点顶点列表，用来抓住整个图
//是方阵 每行每列必定代表一个顶点
class AdjaMatrixDirectedUnweightedGraphApp {

    public static void main(String[] args) {
//        AdjaDirectedUnWeightedMatrixGraph graph = new AdjaDirectedUnWeightedMatrixGraph(9);
        AdjaMatrixDirectedUnweightedGraph graph = new AdjaMatrixDirectedUnweightedGraph(9);
        graph.addArc(0,1);
        graph.addArc(0,2);
        graph.addArc(1,2);
        graph.addArc(2,1);

        graph.addArc(0,3);
        graph.addArc(0,4);
        graph.addArc(3,4);

        graph.addArc(1,5);
        graph.addArc(1,6);
        graph.addArc(5,6);

        graph.addArc(2,7);
        graph.addArc(2,8);
        graph.addArc(7,8);

        //bfs
//        graph.traverseByBfs();

        //dfs
        graph.traverseByDfs();
    }

}

////无权图在意节点，不在意边，边只是连通性
////顶点遍历类似树
//
////无向无权图 这个依赖邻接表 对称 用有向表示双向维护，所以对称
//class AdjaUnDirectedUnWeightedMatrixGraph {
//
//}

//有向无权图 这个依赖邻接表 不对称
class AdjaMatrixDirectedUnweightedGraph extends GraphMeta {
    //顶点数
//    int n;
    boolean[][] adjaMatrix;

    AdjaMatrixDirectedUnweightedGraph(int n) {
        if (n <=0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMatrix = new boolean[n][n];
    }
    void addArc(int from, int to) {
        if (!adjaMatrix[from][to]) {
            adjaMatrix[from][to] = true;
        }
    }

    //计算连通子图的个数，连通分量，任意一个订单开始遍历，然后标记访问列表，然后再取一个顶点，从没被标记过的顶点中选，再标记，直到所有节点被标记过为止
    //那遍历了多少次，就是有多少个连通子图，也就是连通分量，用是否访问标记进行判断，被访问过和是否连通是两个事情
    //多扫一遍顶点、数连通分量
    //判断连通子图个数，连通分量

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
        VParam startVParam = new VParam(null, singleStartParam.startV);
        allTemp.parents[singleStartParam.startV] = null;
        startVParam.bfsVLevel = 0;
        queue.offer(startVParam);

        while (!queue.isEmpty()) {
            //先访问当前自己
            VParam vParam = queue.poll();
            GraphUtil.bfsVisitV(vParam, singleVo);
            //再按层访问邻接顶点 这里没有递归，所以访问写在前后无所谓，最终都是在前
            //这里就按照顺序从小到大，从左到右即可，反过来也行，但没什么本质区别
            for (int u = 0; u < n; u++) {
                if (adjaMatrix[vParam.v][u]) {
                    Integer adjaU = u;
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
//            GraphUtil.visit(v);
        }
        return singleVo;
    }
    //dfs
    SingleVo dfs(final SingleStartParam singleStartParam, final AllTemp allTemp) {
        SingleVo singleVo = new SingleVo();
        VParam vParam = new VParam(null, singleStartParam.startV);
        allTemp.parents[singleStartParam.startV] = null;
        vParam.dfsVDepth = 0;
        dfsRecur(vParam, allTemp, singleVo);
        return singleVo;
    }
    void dfsRecur(final VParam vParam, final AllTemp allTemp, SingleVo singleVo) {
        allTemp.visited[vParam.v] = true;
        //前序遍历
        GraphUtil.dfsDiscoverV(vParam, singleVo);
        allTemp.vStatuses[vParam.v] = VStatus.GRAY;
        for (int u = 0; u < n; u++) {
            if (adjaMatrix[vParam.v][u]) {
                Integer adjaU = u;
                ArcParam arcParam = new ArcParam(vParam.v, adjaU);


                if (!allTemp.visited[adjaU]) {
                    arcParam.dfsArcType = DfsArcType.DFS_TREE_ARC;
                    GraphUtil.dfsVisitArc(arcParam, singleVo);
                    //这里有递归，所以访问v顶点因此有前后之别，先后之别
                    VParam uParam = new VParam(vParam.v, adjaU);
                    allTemp.parents[adjaU] = vParam.v;
                    uParam.dfsVDepth = vParam.dfsVDepth+1;
                    dfsRecur(uParam, allTemp, singleVo);
                } else {
                    if (allTemp.vStatuses[adjaU] == VStatus.GRAY) {
                        arcParam.dfsArcType = DfsArcType.DFS_BACKWARD_ARC;
                        GraphUtil.dfsVisitArc(arcParam, singleVo);
                    } else if (allTemp.vStatuses[adjaU] == VStatus.BLACK) {
                        Long vDiscoverTime = singleVo.dfsVVDfsDoMap.get(vParam.v).discoverTime;
                        Long adjUDiscoverTime = singleVo.dfsVVDfsDoMap.get(adjaU).discoverTime;
                        if (vDiscoverTime < adjUDiscoverTime) {
                            arcParam.dfsArcType = DfsArcType.DFS_FORWARD_ARC;
                            GraphUtil.dfsVisitArc(arcParam, singleVo);
                        } else if (vDiscoverTime > adjUDiscoverTime){
                            arcParam.dfsArcType = DfsArcType.DFS_CROSS_ARC;
                            GraphUtil.dfsVisitArc(arcParam, singleVo);
                        } else {
                            throw new RuntimeException("impossible1");
                        }
                    } else {
                        throw new RuntimeException("impossible2");
                    }
                }

            }
        }
        //后序遍历
//        GraphUtil.finish(v);
        GraphUtil.dfsFinishV(vParam, singleVo);
        allTemp.vStatuses[vParam.v] = VStatus.BLACK;
    }

}

////边遍历就算了不关心
////有权图在意边 不在意节点 顶点连通性 共用顶点
////有都在意的吗？
//
////无向有权图 这个更重要？因为看边权计算，这个更好 对称 用有向表示双向维护，所以对称
//class AdjaUndirectedWeightedMatrixGraph {
//    //顶点数
//    int n;
//    int[][] adjaMatrix;
//
//    //全源最短？
//
//}
//
////有向有权图 这个更重要？因为看边权计算，这个更好 不对称
//class AdjaDirectedWeightedMatrixGraph {
//    //顶点数
//    int n;
//    int[][] adjaMatrix;
//
//    //全源最短？
//
//}
