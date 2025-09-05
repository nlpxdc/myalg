package graph.unweighted.directed;

import graph.unweighted.GraphUtil;

import java.util.*;

//树和分治更有关联，关心节点中key值的大小，在这个上面做文章，
//图的话和分治关系不大？更关心节点间的关联关系，以及含有边权数据的计算问题，不太关心节点key的大小关系排序？
// 所以在做文章方面和树不太一样，所以两者各有不同用途

//第三全局视角 不是矩阵，如果是稀疏，可以转成稀疏矩阵，可以直接用（稀疏）矩阵的计算能力，解全局问题
//有向图
//图处理 连通性 路径 流 割 环 匹配 覆盖 等关联问题 网络问题
//主要处理边权 还能处理点权 需要额外数组 或者 复杂对象支持
//高维可以看到低维，二维看到一维
//没有以身入局，全局，按照二维处理，根据两个节点降维处理，索引处理，有邻接的优势，两次获取
//还有逆邻接表，表示的都是入度，正常表示的都是出度
//这里节点默认使用了Integer来定义，简单节点，反正不关心点权，这样定义图会简单，但是如果需要点权，那么节点复杂节点必须定义
//这里的第二维，的一维数组其实就是邻接节点的定义，第一维的数组，其实就是节点顶点列表，用来抓住整个图
//这里其实依旧使用了数组，没有用到列表，但是利用了Map映射算法，内部可以用数组带hash打散进行存储表示或者是树treemap都可以，不限制，这样节省了空间
//第一个维度的顶点列表，节省了描述顶点集合的空间，第二维的邻接点的邻接边（有向图）或点（无向图），节省了邻接边点的空间，超出后再扩容
//依赖hash散列，减小存储空间
class AdjaMapSetDirectedUnweightedGraphApp {
    public static void main(String[] args) {
        AdjaMapSetDirectedUnweightedGraph graph = new AdjaMapSetDirectedUnweightedGraph(9);
        graph.addArc(0,1);
        graph.addArc(0,2);
        graph.addArc(1,2);

        graph.addArc(0,3);
        graph.addArc(0,4);
        graph.addArc(3,4);

        graph.addArc(1,5);
        graph.addArc(1,6);
        graph.addArc(5,6);

        graph.addArc(2,7);
        graph.addArc(2,8);
        graph.addArc(7,8);

        //bfs
        graph.traverseByBfs();

        //dfs
        graph.traverseByDfs();

    }
}

//无向无权图 最基础 用有向结构表示，数据必须要对称维护
//节点无需减半维护，利用对称性有好处？有去有回，方便找节点？回溯？
//或者默认前后的节点是无序的，要注意，那可以维护一端？不行，这样就减半了，不能减半
//相当于多颗树森林的遍历
class AdjaMapSetDirectedUnweightedGraph {
    int n;
    Map<Integer, Set<Integer>> adjaMapSet;

    AdjaMapSetDirectedUnweightedGraph(int n) {
        if (n <= 0) {
            throw new RuntimeException("n必须大于0");
        }
        this.n = n;
        adjaMapSet = new HashMap<>();
    }
    void addArc(int from, int to) {
        Set<Integer> uAdjaSet = adjaMapSet.getOrDefault(from, new HashSet<>());
        adjaMapSet.put(from, uAdjaSet);
        uAdjaSet.add(to);
    }

    boolean hasUVArc(int from, int to) {
        Set<Integer> fromAdjaSet = adjaMapSet.get(from);
        if (fromAdjaSet != null) {
            return fromAdjaSet.contains(to);
        }
        return false;
    }

    void traverseByBfs() {
        GraphUtil.traverse(n, this::bfs);
    }

    void traverseByDfs() {
        GraphUtil.traverse(n, this::dfs);
    }

    //bfs
    void bfs(int startV, boolean[] visited) {
        //临时队列
        Queue<Integer> queue = new LinkedList<>();

        //这里可以是任意startV n
        visited[startV] = true;
        queue.offer(startV);
        while (!queue.isEmpty()) {
            //先访问当前自己
            Integer v = queue.poll();
            visited[v] = true;
            GraphUtil.visit(v);
            //再按层访问邻接顶点 这里没有递归，所以访问写在前后无所谓，最终都是在前
            //这里就按照顺序从小到大，从左到右即可，反过来也行，但没什么本质区别
            Set<Integer> adjaUSet = adjaMapSet.getOrDefault(v, new HashSet<>());
            for (Integer adjaU : adjaUSet) {
                if (!visited[adjaU]) {
                    visited[adjaU] = true;
                    queue.offer(adjaU);
                }
            }
        }
    }
    //dfs
    void dfs(int v, boolean[] visited) {
        visited[v] = true;
        //前序遍历
//        GraphUtil.discover(v);
        Set<Integer> adjaUSet = adjaMapSet.getOrDefault(v, new HashSet<>());
        for (Integer adjaU : adjaUSet) {
            if (!visited[adjaU]) {
                dfs(adjaU, visited);
            }
        }
        //后序遍历
//        GraphUtil.finish(v);
        GraphUtil.visit(v);
    }

}

////有向无权图 这个重要，因为这个是结构相关的，权不影响结构
//class AdjaMapSetDirectedUnweightedGraph {
//    Map<Integer, Set<Integer>> mapSet;
//
//    //遍历 traverse
//    //BFS visited bool数组
//    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
//    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
//    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？
//    //三色标记，只判断是否，不记录顺序 这个要入度的
//    //拓扑序要记录顺序的 这个也要入度的
//
//    //单向连通 双向连通，无向连通
//    //无向底图 无向基图 弱连通 dfs bfs
//    //有向图 单向连通 单连通环 dag 单链？
//    //有向图 强连通 强连通环
//
//}

////无向有权图
//class AdjaMapMapUndirectedWeightedGraph {
//    Map<Integer, Map<Integer, Integer>> mapMap;
//}
//
////有向有权图 这个对于图来说最重要？带边权的计算
////默认都是出度
//class AdjaMapMapDirectedWeightedGraph {
//    Map<Integer, Map<Integer, Integer>> mapMap;
//
//    //遍历 traverse
//    //BFS visited bool数组
//    //DFS 无中，有前后？但对称，默认约定前序 后序 逆后序？ 唯一等价？
//    //判断有环无环 dag 拓扑排序多个？ bool数组->int数组 三色标记，结合入度为0开始，只有出没有入的点，可能有多个，也可能没有
//    //树边 非树边 回边back 前向边forward 横叉边cross 配合三色标记？
//
//}
