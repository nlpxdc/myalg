package tree.binarytree;

class AryTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
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

    }

}
