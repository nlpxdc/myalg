package graph;

//简单图
class MatrixGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class MatrixGraph {
    int[][] matrix;
    int vertexSize;

    MatrixGraph(int vertexSize) {
        this.vertexSize = vertexSize;
        this.matrix = new int[vertexSize][vertexSize];
    }

}
