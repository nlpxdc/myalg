package graph;

import java.util.Set;

//第0视角，非第一自己第三全局视角
//适合按顺序批次流式的初始化init E是V²即VxV的子集
class VEGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class Edge {
    Integer from;
    Integer to;
    Integer weight;
}

class VeGraph<V> {
    Set<V> vertexSet;
    Set<Edge> edgeSet;
}
