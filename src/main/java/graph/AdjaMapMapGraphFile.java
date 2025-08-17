package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//第三全局视角 不是矩阵，如果是稀疏，可以转成稀疏矩阵，可以直接用（稀疏）矩阵的计算能力，解全局问题
//有向图
class AdjaMapMapGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//有向无权图
class AdjaUnWeightedMapMapGraph {
    Map<Integer, Set<Integer>> adjaMapMap;

    AdjaUnWeightedMapMapGraph() {
        this.adjaMapMap = new HashMap<>();
    }

    //遍历 traverse
    //BFS visited bool数组
    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？

}

//有向带权图
class AdjaWeightedMapMapGraph {
    Map<Integer, Map<Integer, Integer>> adjaMapMap;

    AdjaWeightedMapMapGraph() {
        this.adjaMapMap = new HashMap<>();
    }

    //遍历 traverse
    //BFS visited bool数组
    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？

}
