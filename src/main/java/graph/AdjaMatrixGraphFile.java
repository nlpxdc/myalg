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
