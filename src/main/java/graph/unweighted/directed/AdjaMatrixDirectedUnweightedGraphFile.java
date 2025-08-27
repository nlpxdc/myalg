package graph.unweighted.directed;

import java.util.Arrays;
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
class AdjaMatrixDirectedUnweightedGraphApp {

    public static void main(String[] args) {
        AdjaDirectedUnWeightedMatrixGraph graph = new AdjaDirectedUnWeightedMatrixGraph(9);
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);

        graph.addEdge(0,3);
        graph.addEdge(0,4);
        graph.addEdge(3,4);

        graph.addEdge(1,5);
        graph.addEdge(1,6);
        graph.addEdge(5,6);

        graph.addEdge(2,7);
        graph.addEdge(2,8);
        graph.addEdge(7,8);

        //byEdge
        graph.traverseVertexByEdge();

        //bfs
        graph.bfs();

        //dfs
        graph.preOrderDfs();
        graph.postOrderDfs();
        graph.dfs();
    }

}

//无权图在意节点，不在意边，边只是连通性
//顶点遍历类似树

//无向无权图 这个依赖邻接表 对称 用有向表示双向维护，所以对称
class AdjaUnDirectedUnWeightedMatrixGraph {

}

//有向无权图 这个依赖邻接表 不对称
class AdjaDirectedUnWeightedMatrixGraph {
    //顶点数
    int n;
    boolean[][] adjaMatrix;

    AdjaDirectedUnWeightedMatrixGraph(int n) {
        if (n <=0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMatrix = new boolean[n][n];
    }
    void addEdge(int from, int to) {
        if (!adjaMatrix[from][to]) {
            adjaMatrix[from][to] = true;
        }
    }

    Integer getFirstUnVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return null;
    }
    void visit(int v) {
        System.out.print(v+",");
    }
    void discover(int v) {
        System.out.println(String.format("discover %d", v));
    }
    void finish(int v) {
        System.out.println(String.format("finish %d", v));
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

    //遍历，实质就是第三他人视角 2维数组的循环，2层外里嵌套循环
    //注意这里访问的是边，然后要转成点 这个逻辑上没啥作用
    void traverseVertexByEdge() {
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjaMatrix[i][j]) {
                    if (!visited[i]) {
                        visited[i] = true;
                        visit(i);
                    }
                    if (!visited[j]) {
                        visited[j] = true;
                        visit(j);
                    }
                }
            }
        }

        System.out.println();
        System.out.println();
    }

    //bfs
    //假设有且只有一个连通子图 访问这个顶点的连通子图的所有顶点
    void singleBfs(int startV) {
        boolean[] visited = new boolean[n];

        innerBfs(startV, visited);

        System.out.println();
    }
    int bfs() {
        //初始化临时数组，记录访问状态
        boolean[] visited = new boolean[n];

        int childrenGraphCount = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                childrenGraphCount++;
                innerBfs(firstUnVisited, visited);
            } else {
                break;
            }
        }

        System.out.println();

        return childrenGraphCount;
    }
    void innerBfs(int startV, boolean[] visited) {
        //临时队列
        Queue<Integer> queue = new LinkedList<>();

        //这里可以是任意startV n
        queue.offer(startV);
        visited[startV] = true;
        while (!queue.isEmpty()) {
            Integer currentV = queue.poll();
            visit(currentV);
            for (int adjaV = 0; adjaV < n; adjaV++) {
                if (adjaMatrix[currentV][adjaV] && !visited[adjaV]) {
                    visited[adjaV] = true;
                    queue.offer(adjaV);
                }
            }
        }

        System.out.println();
    }

    //dfs preOrder & postOrder
    void singleDfs(int startV) {
        boolean[] visited = new boolean[n];

        innerDfs(startV, visited);

        System.out.println();
    }
    int dfs() {
        boolean[] visited = new boolean[n];

        int childrenGraphCount = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                innerDfs(firstUnVisited, visited);
                System.out.println();
            } else {
                break;
            }
        }

        System.out.println();

        return childrenGraphCount;
    }
    void innerDfs(int v, boolean[] visited) {
        visited[v] = true;
        discover(v);
        for (int u = 0; u < n; u++) {
            if (adjaMatrix[v][u] && !visited[u]) {
                innerDfs(u, visited);
            }
        }
        finish(v);
    }

    //dfs preOrder
    void singlePreOrderDfs(int startV) {
        boolean[] visited = new boolean[n];

        innerPreOrderDfs(startV, visited);

        System.out.println();
    }
    int preOrderDfs() {
        boolean[] visited = new boolean[n];

        int childrenGraphCount = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                innerPreOrderDfs(firstUnVisited, visited);
                System.out.println();
            } else {
                break;
            }
        }

        System.out.println();

        return childrenGraphCount;
    }
    void innerPreOrderDfs(int v, boolean[] visited) {
        visited[v] = true;
        visit(v);
        for (int u = 0; u < n; u++) {
            if (adjaMatrix[v][u] && !visited[u]) {
                innerPreOrderDfs(u, visited);
            }
        }
    }

    //dfs postOrder
    void singlePostOrderDfs(int startV) {
        boolean[] visited = new boolean[n];

        innerPostOrderDfs(startV, visited);

        System.out.println();
    }
    int postOrderDfs() {
        boolean[] visited = new boolean[n];

        int childrenGraphCount = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                innerPostOrderDfs(firstUnVisited, visited);
                System.out.println();
            } else {
                break;
            }
        }

        System.out.println();

        return childrenGraphCount;
    }
    void innerPostOrderDfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int u = 0; u < n; u++) {
            if (adjaMatrix[v][u] && !visited[u]) {
                innerPostOrderDfs(u, visited);
            }
        }
        visit(v);
    }
}

//边遍历就算了不关心
//有权图在意边 不在意节点 顶点连通性 共用顶点
//有都在意的吗？

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
