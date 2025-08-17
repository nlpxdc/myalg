package graph;

import java.util.Set;

//第一自己视角 和树节点一样的定义
//节点定义
class NodeGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class Node<K> {
    K k;
    Set<Node<K>> nodeSet;
}
