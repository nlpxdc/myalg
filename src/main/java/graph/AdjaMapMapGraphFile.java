package graph;

import java.util.HashMap;
import java.util.Map;

//第三全局视角 不是矩阵，如果是稀疏，可以转成稀疏矩阵，可以直接用（稀疏）矩阵的计算能力，解全局问题
//有向带权图
class AdjaMapMapGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class AdjaMapMapGraph {
    Map<Integer, Map<Integer, Integer>> adjaMapMap;

    AdjaMapMapGraph() {
        this.adjaMapMap = new HashMap<>();
    }

    //遍历 traverse
    //BFS
    //DFS 无中，有前后？默认约定前序 后序 逆后序？ 唯一等价？
    //判断有环无环 dag 拓扑排序多个？
}
