package tree.kwaytree;

import java.util.List;
import java.util.Set;

//其实图也一样定义，图还可以线性结构的三元组，边集合，依赖顶点集合 这种定义更本质，但算法友好度差，查慢
// 这里的这种定义更本质
//是节点 顶点的结构关系，可以直接操作节点，直观
//遍历加visited
//这个比较特殊，和图的邻接节点的定义是一样的，但是在规则、语义上不一样，限制不同，可以用图的算法来写树，但是不能用树的算法来写图
//这里可以先写下，因为太像了，作为基础，然后衍生拓展
class LinkKwayTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//递归嵌套定义
class LinkTreeNode {
    int key;
    //这里直接用list吧，指描述结构定义，不描述具体实现
    Set<LinkTreeNode> children; //也可以用java.util.List
//    List<LinkTreeNode> children;

    LinkTreeNode(int key) {
        this.key = key;
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

    void visit(LinkTreeNode node) {
//        visitCurrent(node);
        System.out.println(node.key+",");
    }

//    void visitCurrent(LinkTreeNode node) {
//        System.out.println(node.key+",");
//    }

    //间接递归，不直观，但是抽方法的思想是好的
//    间接递归是“设计结果”而不是“实现偷懒”
    //框架强制 尾递归优化 避免暴露内部 API
//    void visitChildren(LinkTreeNode node) {
//        for (int i = 0; i < node.children.size(); i++) {
//            LinkTreeNode child = node.children.get(i);
//            innerTraverseDfsPreOrder(child);
//        }
//    }

    //DFS
    //pre order
    void innerTraverseDfsPreOrder(LinkTreeNode node) {
        if (node == null) {
            return;
        }
        //先访问当前节点，适用于初始化，序列化，克隆拷贝等场景
        visit(node);
        //在访问子节点列表
        for (int i = 0; i < node.children.size(); i++) {
            LinkTreeNode child = node.children.get(i);
            innerTraverseDfsPreOrder(child);
        }
    }
    void traverseDfsPreOrder() {
        innerTraverseDfsPreOrder(root);
        System.out.println();
    }
//    void traverseDfsPreOrderStack() {}
    //post order
//    void innerTraverseDfsPostOrder(LinkTreeNode node) {
//        if (node.children != null && node.children.length > 0) {
//            for (int i = 0; i < node.children.length; i++) {
//                LinkTreeNode child = node.children[i];
//                innerTraverseDfsPostOrder(child);
//            }
//        }
//        System.out.print(node.val+",");
//    }
//    void traverseDfsPostOrder() {
//        innerTraverseDfsPostOrder(root);
//        System.out.println();
//    }
//    void traverseDfsPostOrderStack() {}

    //no in order



}
