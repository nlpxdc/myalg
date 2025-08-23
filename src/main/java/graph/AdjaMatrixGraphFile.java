package graph;

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
class AdjaMatrixGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//无向无权图 这个依赖邻接表 对称 用有向表示双向维护，所以对称
class AdjaUnDirectedUnWeightedMatrixGraph {
    boolean[][] adjaMatrix;

    //遍历，实质就是第三他人视角 2维数组的循环，2层外里嵌套循环
    //注意这里访问的是边，然后要转成点

    //bfs

    //dfs

}

//有向无权图 这个依赖邻接表 不对称
class AdjaDirectedUnWeightedMatrixGraph {
    boolean[][] adjaMatrix;
}

//无向有权图 这个更重要？因为看边权计算，这个更好 对称 用有向表示双向维护，所以对称
class AdjaUndirectedWeightedMatrixGraph {
    int[][] adjaMatrix;

    //全源最短？

}

//有向有权图 这个更重要？因为看边权计算，这个更好 不对称
class AdjaDirectedWeightedMatrixGraph {
    int[][] adjaMatrix;

    //全源最短？

}
