package graph;

//第三他人全局视角 是矩阵 如果是稠密，直接用，不用转稀疏矩阵，直接矩阵计算，解全局问题
//二维数组的表示，表示图
//简单图 多重图？ 二维ary 二维vector 冗余视图结构
//有向图
//高维可以看到低维，二维看到一维
//没有以身入局，全局，按照二维处理，根据两个节点降维处理，索引处理，有邻接的优势，两次获取
//逆邻接矩阵，就是邻接矩阵的转置
//总体用的少
class AdjaMatrixGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//有向无权图 这个依赖邻接表
class AdjaUnWeightedMatrixGraph {
    boolean[][] adjaMatrix;

    AdjaUnWeightedMatrixGraph() {
        int vertexMaxSize = 100;
        this.adjaMatrix = new boolean[vertexMaxSize][vertexMaxSize];
    }
}

//有向带权图 这个更重要？因为看边权计算，这个更好
class AdjaWeightedMatrixGraph {
    int[][] adjaMatrix;

    AdjaWeightedMatrixGraph() {
        int vertexMaxSize = 100;
        this.adjaMatrix = new int[vertexMaxSize][vertexMaxSize];
    }

    //全源最短？

}
