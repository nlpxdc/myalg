package graph;

import java.util.Set;

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

