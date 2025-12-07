package structure.logic.ve.valcal.tree.common.kwaytree;

import java.util.LinkedList;

class DeLinkKwayApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class DeLinkTreeNode {
    int val;
//    DeLinkTreeNode[] children;
    LinkedList<DeLinkTreeNode> children;
    DeLinkTreeNode parent; //可回溯
}

class DeLinkKwayTree {
    //一个根节点root即可确定
    //带上size元信息，最重要
    //适合小量全部的内存
}
