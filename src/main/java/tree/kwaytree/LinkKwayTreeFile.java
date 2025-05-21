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
//    LinkTreeNode[] children; //也可以用java.util.List
    List<LinkTreeNode> children;

    LinkTreeNode(int val) {
        this.val = val;
    }
}

//身在其中，第四视角
class LinkKwayTree {
    //一个根节点root即可确定 最关键
    //带上size元信息，最重要
    //适合小量全部的内存
}
