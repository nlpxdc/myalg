package graph;

//第三全局视角 是矩阵 如果是稠密，直接用，不用转稀疏矩阵，直接矩阵计算，解全局问题
//简单图 多重图？ 二维ary 二维vector 冗余视图结构
//有向带权图
class AdjaMatrixGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class AdjaMatrixGraph {
    int[][] adjaMatrix;

    AdjaMatrixGraph() {
        int vertexMaxSize = 100;
        this.adjaMatrix = new int[vertexMaxSize][vertexMaxSize];
    }

    //全源最短？

}
