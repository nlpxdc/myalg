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
//如果有向怎么表示？下面那种，固定边权都是1，就不能是自递归定义了，自递归定义就是无向的
//应为不关心边权，所以只关心节点自身的关联，所以可以自递归定义
class UnWeightedNode {
    int key;
    //无向，但是定义接口儿子，有点有向意思，可以有指回去的指针，但是不代表有向，只是容易回溯的手段而已
    Set<UnWeightedNode> neighbourSet;
}

// 不是无向，是有向！ 简单图 非自递归定义，节点含边（这个边也是对应一个节点的带权表示，key依旧是关键，weight次要），不是节点含自身节点
//weight都等于1，就是有向无权图，因为关心边的权，所以就必须有边定义，就不能是自递归定义了
class FromWeightedNode {
    int from;
    Set<ToWeightedEdge> toWeightedEdgeSet;
}

class ToWeightedEdge {
    int to;
    int weight;
}
