package graph.unweighted.directed;

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
        AdjaMatrixDirectedUnweightedGraph graph = new AdjaMatrixDirectedUnweightedGraph(3);
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

//        //bfs
//        graph.bfs();
//
//        //dfs
//        graph.dfs();
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
class AdjaMatrixDirectedUnweightedGraph {
    //顶点数
    int n;
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

    //bfs

    //dfs

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
