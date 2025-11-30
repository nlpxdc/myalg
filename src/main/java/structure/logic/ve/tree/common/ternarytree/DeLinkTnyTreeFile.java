package structure.logic.ve.tree.common.ternarytree;

class DeLinkTnyTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class DeLinkTreeNode {
    int val;
    DeLinkTreeNode left;
    DeLinkTreeNode middle;
    DeLinkTreeNode right;
    DeLinkTreeNode parent;

    DeLinkTreeNode(int val) {
        this.val = val;
    }
}
