package graph.unweighted.undirected;

import graph.unweighted.*;

import java.util.*;

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
class AdjaMatrixUndirectedUnweightedGraphApp {

    public static void main(String[] args) {
        AdjaMatrixUndirectedUnweightedGraph graph = new AdjaMatrixUndirectedUnweightedGraph(9);
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);

        graph.addEdge(0,3);
        graph.addEdge(0,4);
//        graph.addEdge(3,4);

        graph.addEdge(1,5);
        graph.addEdge(1,6);
//        graph.addEdge(5,6);

        graph.addEdge(2,7);
        graph.addEdge(2,8);
        graph.addEdge(7,8);

        //bfs
        AllVo allVo = graph.traverseByBfs();

        //dfs
        AllVo allVo1 = graph.traverseByDfs();

    }

}

//无权图在意节点，不在意边，边只是连通性
//顶点遍历类似树

//无向无权图 这个依赖邻接表 对称 用有向表示双向维护，所以对称
class AdjaMatrixUndirectedUnweightedGraph extends GraphMeta {
    //顶点数
//    int n;
    boolean[][] adjaMatrix;

    AdjaMatrixUndirectedUnweightedGraph(int n) {
        if (n <=0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMatrix = new boolean[n][n];
    }
    void addEdge(int u, int v) {
        if (u == v) {
            throw new RuntimeException("不能有自环边");
        }
        if (adjaMatrix[u][v] != adjaMatrix[v][u]) {
            throw new RuntimeException("数据错误，请检查");
        }
        if (!adjaMatrix[u][v]) {
            adjaMatrix[u][v] = true;
            adjaMatrix[v][u] = true;
//            adjaMatrix[u][v] = adjaMatrix[v][u] = true;
        }
    }


    boolean beNullGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjaMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean beAllVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
    void resetVisited(boolean[] visited) {
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        Arrays.fill(visited, false);
    }

    //计算连通子图的个数，连通分量，任意一个订单开始遍历，然后标记访问列表，然后再取一个顶点，从没被标记过的顶点中选，再标记，直到所有节点被标记过为止
    //那遍历了多少次，就是有多少个连通子图，也就是连通分量，用是否访问标记进行判断，被访问过和是否连通是两个事情
    //多扫一遍顶点、数连通分量
    //判断连通子图个数，连通分量

    public AllVo traverseByBfs() {
        SingleVo singleVo = new SingleVo();
        AllVo allVo = new AllVo();
        GraphUtil.allTraverse(this, this::bfs, singleVo, allVo);
        return allVo;
    }

    public AllVo traverseByDfs() {
        SingleVo singleVo = new SingleVo();
        AllVo allVo = new AllVo();
        GraphUtil.allTraverse(this, this::dfs, singleVo, allVo);
        return allVo;
    }

    @Override
    public Map<Integer, Integer> calcInDegreeMap() {
        return null;
    }

    @Override
    public List<Integer> topoOrderByBfs() {
        return null;
    }

    @Override
    public List<Integer> topoOrderByDfs() {
        return null;
    }

    //bfs和dfs是一种策略

    //bfs 按广度（层）连通
    //因为这个函数是非递归写法，所以这里的startV代表起始顶点，不是当前节点，有点和dfs区别
    //这里遍历连通图，不是遍历单个顶点的意思，这也和dfs有别，这里直接强调整个图。连通图而不是当前顶点
    void bfs(final SingleStartParam singleStartParam, final AllTemp allTemp, SingleVo singleVo, AllVo allVo) {
//        singleVo.bfsList = new LinkedList<>();
//        SingleVo singleVo = new SingleVo(false);
        singleVo.directed = false;

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
                    EdgeParam edgeParam = new EdgeParam(vParam.v, adjaU);

                    //无向图独有
                    if (adjaU.equals(vParam.parent)) {
                        continue;
                    }

                    if (!allTemp.visited[adjaU]) {
                        edgeParam.bfsEdgeType = BfsEdgeType.BFS_TREE_EDGE;
                        GraphUtil.bfsVisitEdge(edgeParam, singleVo);
                        allTemp.visited[adjaU] = true;
                        VParam uParam = new VParam(vParam.v, adjaU);
                        allTemp.parents[adjaU] = vParam.v;
                        uParam.bfsVLevel = vParam.bfsVLevel+1;
                        queue.offer(uParam);
                    } else {
                        edgeParam.bfsEdgeType = BfsEdgeType.BFS_NON_TREE_EDGE;
                        GraphUtil.bfsVisitEdge(edgeParam, singleVo);
                    }
                }
            }
//            GraphUtil.visit(v);
        }
//        return singleVo;
    }

    //dfs 按深度连通
    //因为这个函数是递归写法，所以这里的v代表当前v顶点，不是起点，有点和bfs区别
    //虽然命名是递归，以当前顶点为重，但是因为递归的特性，可以遍历到所有连通顶点，所以也是遍历连通图
    //可以再加一个额外变量记录访问的节点总数，然后整体来限制递归访问的总数，一面错误导致爆掉
    //bfs因为不是递归，所以在自身逻辑中即可依赖递推迭代循环自身来控制总数限制，这是核心有别的地方
    //这里使用递归写法的时候，第一个参数v代表当前顶点v，不能代表起始顶点
    //如果使用显式栈的时候，那么可以和bfs的队列保持一致了，是可以代表startV
//    void dfs(final int v, final boolean[] visited) {
    void dfs(final SingleStartParam singleStartParam, final AllTemp allTemp, SingleVo singleVo, AllVo allVo) {
//        SingleVo singleVo = new SingleVo(false);
        singleVo.directed = false;
        VParam vParam = new VParam(null, singleStartParam.startV);
        allTemp.parents[singleStartParam.startV] = null;
        vParam.dfsVDepth = 0;
        dfsRecur(vParam, allTemp, singleVo);
//        return singleVo;
    }

    void dfsRecur(final VParam vParam, final AllTemp allTemp, SingleVo singleVo) {
//        visited[v] = true;
        allTemp.visited[vParam.v] = true;
        //前序遍历

        GraphUtil.dfsDiscoverV(vParam, allTemp, singleVo);
        for (int u = 0; u < n; u++) {
            if (adjaMatrix[vParam.v][u]) {
                Integer adjaU = u;
                EdgeParam edgeParam = new EdgeParam(vParam.v, adjaU);

                //无向图独有
                if (adjaU.equals(vParam.parent)) {
                    continue;
                }

                if (!allTemp.visited[adjaU]) {
                    edgeParam.dfsEdgeType = DfsEdgeType.DFS_TREE_EDGE;
                    GraphUtil.dfsVisitEdge(edgeParam, singleVo);
                    //这里有递归，所以访问v顶点因此有前后之别，先后之别
                    VParam uParam = new VParam(vParam.v, adjaU);
                    allTemp.parents[adjaU] = vParam.v;
                    uParam.dfsVDepth = vParam.dfsVDepth+1;
                    dfsRecur(uParam, allTemp, singleVo);
                } else {
                    edgeParam.dfsEdgeType = DfsEdgeType.DFS_NON_TREE_EDGE;
                    GraphUtil.dfsVisitEdge(edgeParam, singleVo);
                }


            }
        }
        //后序遍历
        GraphUtil.dfsFinishV(vParam, allTemp, singleVo);

    }

}

//前后 先后 顺逆 正反

////有向无权图 这个依赖邻接表 不对称
//class AdjaDirectedUnWeightedMatrixGraph {
//    //顶点数
//    int n;
//    boolean[][] adjaMatrix;
//}
//
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
