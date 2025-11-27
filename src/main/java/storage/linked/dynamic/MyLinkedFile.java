package storage.linked.dynamic;

//指针 引用 传统动态
class MyLinkedFile {
    public static void main(String[] args) {
        System.out.println("aa");

        MyNode myNode = new MyNode();
        MyNode myNode1 = new MyNode();
        MyNode myNode2 = new MyNode();
        myNode.next = myNode1;
        myNode1.next = myNode2;
        myNode2.next = null;

        //头插O(1)，尾插 单链O(n) 双链O(1)

    }
}

class MyNode {
    Object val;
    MyNode next;
}
