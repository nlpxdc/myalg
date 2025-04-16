class MyCircleDLinkApp {

    public static void main(String[] args) {
        MyCircleDLinkNode node0 = new MyCircleDLinkNode(1);
        MyCircleDLinkNode node1 = new MyCircleDLinkNode(3);
        MyCircleDLinkNode node2 = new MyCircleDLinkNode(6);

        node0.next = node1;
        node1.prev = node0;

        node1.next = node2;
        node2.prev = node1;

        node2.next = node0;
        node0.prev = node2;

        MyCircleDLinkList myCircleDLinkList = new MyCircleDLinkList();
        myCircleDLinkList.head = node0;
        myCircleDLinkList.last = node2;
        myCircleDLinkList.size = 3;

        myCircleDLinkList.traverseFromHead();
    }

}

class MyCircleDLinkNode {
    int val;
    MyCircleDLinkNode prev;
    MyCircleDLinkNode next;

    MyCircleDLinkNode(int val) {
        this.val = val;
    }
}

class MyCircleDLinkList {
    MyCircleDLinkNode head;
    MyCircleDLinkNode last;
    int size;

    MyCircleDLinkList() {
        head = null;
        last = null;
        size = 0;
    }

    void traverseFromHead() {
        MyCircleDLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }
}
