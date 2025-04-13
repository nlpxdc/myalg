class MyDLinkApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class MyDLinkNode {
    int val;
    MyDLinkNode pre;
    MyDLinkNode next;
}

class MyDLinkList {
    MyDLinkNode head;
    MyDLinkNode last;
    int size;

    MyDLinkList() {
        head = null;
        last = null;
        size = 0;
    }
}
