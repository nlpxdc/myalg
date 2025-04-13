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
    int size;

    MyDLinkList() {
        head = null;
        size = 0;
    }
}

