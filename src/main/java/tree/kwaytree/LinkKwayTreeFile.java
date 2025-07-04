package tree.kwaytree;

import java.util.List;

class LinkKwayTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//递归嵌套定义
class LinkTreeNode {
    int val;
    LinkTreeNode[] children; //也可以用java.util.List
//    List<LinkTreeNode> children;

    LinkTreeNode(int val) {
        this.val = val;
    }
}

//身在其中，第四视角
class LinkKwayTree {
    //一个根节点root即可确定 最关键
    //带上size元信息，最重要
    //适合小量全部的内存
    LinkTreeNode root;
    int size;

    //BFS
    void traverseBfs() {

    }

    //DFS
    //pre order
    void innerTraverseDfsPreOrder(LinkTreeNode node) {
        System.out.print(node.val+",");
        if (node.children != null && node.children.length > 0) {
            for (int i = 0; i < node.children.length; i++) {
                LinkTreeNode child = node.children[i];
                innerTraverseDfsPreOrder(child);
            }
        }
    }
    void traverseDfsPreOrder() {
        innerTraverseDfsPreOrder(root);
        System.out.println();
    }
    void traverseDfsPreOrderStack() {}
    //post order
    void innerTraverseDfsPostOrder(LinkTreeNode node) {
        if (node.children != null && node.children.length > 0) {
            for (int i = 0; i < node.children.length; i++) {
                LinkTreeNode child = node.children[i];
                innerTraverseDfsPostOrder(child);
            }
        }
        System.out.print(node.val+",");
    }
    void traverseDfsPostOrder() {
        innerTraverseDfsPostOrder(root);
        System.out.println();
    }
    void traverseDfsPostOrderStack() {}

    //no in order



}
