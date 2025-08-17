package graph;

import java.util.Set;

//第一自己视角 和树节点一样的定义
//节点定义 类似树节点，这里只关心节点顶点，不关心边和权重，因为不影响整体结构
class NodeGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//自递归定义 定义无向工程有向，因为递归自身？简单图 权重是边的权重，点没有权重
class UnWeightedNode {
    int key;
    //无向，但是定义接口儿子，有点有向意思，可以有指回去的指针，但是不代表有向，只是容易回溯的手段而已
    Set<UnWeightedNode> neighbourSet;
}

// 不是无向，是有向！ 简单图
class FromWeightedNode {
    int from;
    Set<ToWeightedEdge> toWeightedEdgeSet;
}

class ToWeightedEdge {
    int to;
    int weight;
}
