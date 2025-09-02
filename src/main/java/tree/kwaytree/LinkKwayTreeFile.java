package tree.kwaytree;

import java.util.*;

//其实图也一样定义，图还可以线性结构的三元组，边集合，依赖顶点集合 这种定义更本质，但算法友好度差，查慢
// 这里的这种定义更本质
//是节点 顶点的结构关系，可以直接操作节点，直观
//遍历加visited
//这个比较特殊，和图的邻接节点的定义是一样的，但是在规则、语义上不一样，限制不同，可以用图的算法来写树，但是不能用树的算法来写图
//这里可以先写下，因为太像了，作为基础，然后衍生拓展
class LinkKwayTreeApp {
    public static void main(String[] args) {
//        System.out.println("aa");

        LinkTreeNode node1 = new LinkTreeNode(1);

        LinkTreeNode node2 = new LinkTreeNode(2);
        LinkTreeNode node3 = new LinkTreeNode(3);
//        LinkTreeNode[] nodeAry = {node2, node3};
//        node1.children = new HashSet<>(Arrays.asList(nodeAry));
        node1.children = new LinkedHashSet<>(Arrays.asList(node2, node3));

        LinkTreeNode node4 = new LinkTreeNode(4);
        LinkTreeNode node5 = new LinkTreeNode(5);
        LinkTreeNode node6 = new LinkTreeNode(6);
        node2.children = new LinkedHashSet<>(Arrays.asList(node4,node5,node6));

        LinkTreeNode node7 = new LinkTreeNode(7);
        LinkTreeNode node8 = new LinkTreeNode(8);
        LinkTreeNode node9 = new LinkTreeNode(9);
        node3.children = new LinkedHashSet<>(Arrays.asList(node7,node8,node9));

        LinkKwayTree tree = new LinkKwayTree(node1);
//        tree.traverseDfsPreOrder();
//        tree.traverseDfsPostOrder();
        tree.traverseBfs();
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
    //这个可以算一个计算属性不要，但是要了会有很多好处
    int size;

    LinkKwayTree(LinkTreeNode node) {
        this.root = node;
    }

    //BFS
    void traverseBfs() {
        if (root == null) {
            return;
        }
        //初始化借助队列
        LinkedList<LinkTreeNode> queue = new LinkedList<>();

        //有个初始化？
        //循环迭代处理
        queue.offer(root);
        while (!queue.isEmpty()) {
            //出队列获取需要处理的节点
            LinkTreeNode node = queue.poll();
            //打印当前节点，也就是当前队列，打印
            visit(node);
            //打印完以后把下一层，子节点全部进队列，注意是一个个处理的
            if (node.children != null && !node.children.isEmpty()) {
                for (LinkTreeNode child : node.children) {
                    queue.offer(child);
                }
            }
            //没有区别
//            visit(node);
        }

        System.out.println();
    }

    void visit(LinkTreeNode node) {
//        visitCurrent(node);
        System.out.print(node.key+",");
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
        //再访问子节点列表
//        for (int i = 0; i < node.children.size(); i++) {
//            LinkTreeNode child = node.children.get(i);
//            innerTraverseDfsPreOrder(child);
//        }
        if (node.children != null && !node.children.isEmpty()) {
            for (LinkTreeNode child : node.children) {
                innerTraverseDfsPreOrder(child);
            }
        }
    }
    void traverseDfsPreOrder() {
        innerTraverseDfsPreOrder(root);
        System.out.println();
    }
//    void traverseDfsPreOrderStack() {}
    //post order
    void innerTraverseDfsPostOrder(LinkTreeNode node) {
        if (node == null) {
            return;
        }
        //先访问子节点列表
//        for (int i = 0; i < node.children.size(); i++) {
//            LinkTreeNode child = node.children.get(i);
//            innerTraverseDfsPreOrder(child);
//        }
        if (node.children != null && !node.children.isEmpty()) {
            for (LinkTreeNode child : node.children) {
                innerTraverseDfsPostOrder(child);
            }
        }

        //再访问当前节点，适用于垃圾回收，maven依赖分析，调用依赖分析等需要提前计算子节点数据后给当前节点用的
        visit(node);
    }
    void traverseDfsPostOrder() {
        innerTraverseDfsPostOrder(root);
        System.out.println();
    }
//    void traverseDfsPostOrderStack() {}

    //no in order



}
