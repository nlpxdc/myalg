package graph;

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
class AdjaMatrixGraphApp {
    public static void main(String[] args) {
        AdjaUnDirectedUnWeightedMatrixGraph graph = new AdjaUnDirectedUnWeightedMatrixGraph(3);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.traverseBfs();
    }
}

//无向无权图 这个依赖邻接表 对称 用有向表示双向维护，所以对称
class AdjaUnDirectedUnWeightedMatrixGraph {
    //顶点数
    int n;
    boolean[][] adjaMatrix;

    AdjaUnDirectedUnWeightedMatrixGraph(int n) {
        if (n <=0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMatrix = new boolean[n][n];
    }

    void visit(int v, boolean[] visited) {
        System.out.print(v+",");
        visited[v] = true;
    }

    void addEdge(int u, int v) {
        if (adjaMatrix[u][v] != adjaMatrix[v][u]) {
            throw new RuntimeException("数据错误，请检查");
        }
        if (!adjaMatrix[u][v]) {
            adjaMatrix[u][v] = true;
            adjaMatrix[v][u] = true;
        }
    }

    //遍历，实质就是第三他人视角 2维数组的循环，2层外里嵌套循环
    //注意这里访问的是边，然后要转成点

    //bfs
    void traverseBfs() {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        while (!queue.isEmpty()) {
            Integer currentV = queue.poll();
            visit(currentV, visited);
            for (int adjaV = 0; adjaV < n; adjaV++) {
                if (adjaMatrix[currentV][adjaV] == true && !visited[adjaV]) {
                    queue.offer(adjaV);
                }
            }
        }
    }

    //dfs

}

//有向无权图 这个依赖邻接表 不对称
class AdjaDirectedUnWeightedMatrixGraph {
    //顶点数
    int n;
    boolean[][] adjaMatrix;
}

//无向有权图 这个更重要？因为看边权计算，这个更好 对称 用有向表示双向维护，所以对称
class AdjaUndirectedWeightedMatrixGraph {
    //顶点数
    int n;
    int[][] adjaMatrix;

    //全源最短？

}

//有向有权图 这个更重要？因为看边权计算，这个更好 不对称
class AdjaDirectedWeightedMatrixGraph {
    //顶点数
    int n;
    int[][] adjaMatrix;

    //全源最短？

}
