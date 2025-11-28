package structure.storage.reference;

//动态 指针 引用 传统动态 够大 碎片化
class LinkedApp {
    public static void main(String[] args) {
        System.out.println("aa");

        Node myNode = new Node();
        Node myNode1 = new Node();
        Node myNode2 = new Node();
        myNode.next = myNode1;
        myNode1.next = myNode2;
        myNode2.next = null;

        //头插O(1)，尾插 单链O(n) 双链O(1)

    }
}

class Node {
    Object val;
    Node next;
}
