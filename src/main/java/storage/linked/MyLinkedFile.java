package storage.linked;

class MyLinkedFile {
    public static void main(String[] args) {
        System.out.println("aa");

        MyNode myNode = new MyNode();
        MyNode myNode1 = new MyNode();
        MyNode myNode2 = new MyNode();
        myNode.next = myNode1;
        myNode1.next = myNode2;
        myNode2.next = null;

    }
}

class MyNode {
    Object val;
    MyNode next;
}
