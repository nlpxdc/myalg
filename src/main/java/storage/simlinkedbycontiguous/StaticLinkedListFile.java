package storage.simlinkedbycontiguous;

class StaticLinkedListFileApp {
    public static void main(String[] args) {
        System.out.println("aa");

        MyNode[] myNodes = new MyNode[100000];

        int headIdx = 123;

        MyNode myNode0 = new MyNode();
        myNodes[123] = myNode0;
        myNode0.nextIdx = 456;

        MyNode myNode1 = new MyNode();
        myNodes[456] = myNode1;
        myNode1.nextIdx = 789;

        MyNode myNode2 = new MyNode();
        myNodes[789] = myNode2;
        myNode2.nextIdx = -1;
    }
}

class MyNode {
    Object val;
    int nextIdx;
}
