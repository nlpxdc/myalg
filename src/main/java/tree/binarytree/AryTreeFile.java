package tree.binarytree;

class AryTreeApp {
    public static void main(String[] args) {
        Node nodea1 = new Node(1);

        Node nodeb1 = new Node(2);
        Node nodeb2 = new Node(3);

        Node nodec1 = new Node(4);
        Node nodec2 = new Node(5);
        Node nodec3 = new Node(6);
        Node nodec4 = new Node(7);

        AryTree aryTree = new AryTree();
        aryTree.size = 7;

        aryTree.nodeAry[0] = nodea1;

        aryTree.nodeAry[1] = nodeb1;
        aryTree.nodeAry[2] = nodeb2;

        aryTree.nodeAry[3] = nodec1;
        aryTree.nodeAry[4] = nodec2;
        aryTree.nodeAry[5] = nodec3;
        aryTree.nodeAry[6] = nodec4;

        aryTree.traverseBfs();
    }
}

class Node {
    int val;
    //depth? no

    Node(int val) {
        this.val = val;
    }
}

class AryTree {
    //size? no? yes?
    //maxDepth? no?
    Node[] nodeAry;
    int size;

    public AryTree() {
        nodeAry = new Node[1000];
        size = 0;
    }

    void traverseBfs() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
    }

}
