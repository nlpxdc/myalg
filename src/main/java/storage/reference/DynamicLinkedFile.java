package storage.reference;

//指针 引用 传统动态
class DynamicLinkedFile {
    public static void main(String[] args) {
        System.out.println("aa");

        DynamicNode myNode = new DynamicNode();
        DynamicNode myNode1 = new DynamicNode();
        DynamicNode myNode2 = new DynamicNode();
        myNode.next = myNode1;
        myNode1.next = myNode2;
        myNode2.next = null;

        //头插O(1)，尾插 单链O(n) 双链O(1)

    }
}

class DynamicNode {
    Object val;
    DynamicNode next;
}
