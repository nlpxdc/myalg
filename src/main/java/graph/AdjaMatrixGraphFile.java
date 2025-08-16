package graph;

//简单图 二维ary 二维vector 冗余视图结构
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
}
