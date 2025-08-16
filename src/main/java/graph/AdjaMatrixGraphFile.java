package graph;

//简单图 多重图？ 二维ary 二维vector 冗余视图结构
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

    //遍历 traverse
    //BFS
    //DFS 无前中后，就一种
    //判断有环

}
