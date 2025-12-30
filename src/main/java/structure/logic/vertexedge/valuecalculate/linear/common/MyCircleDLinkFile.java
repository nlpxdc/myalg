package structure.logic.vertexedge.valuecalculate.linear.common;

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
        myCircleDLinkList.traverseFromLast();

        myCircleDLinkList.addHeadEl(new MyCircleDLinkNode(0));
        myCircleDLinkList.addLastEl(new MyCircleDLinkNode(9));

        myCircleDLinkList.traverseFromHead();
        myCircleDLinkList.traverseFromLast();

//        myCircleDLinkList.traverseFromHeadAllTheTime();
        myCircleDLinkList.traverseFromLastAllTheTime();
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

    void addHeadEl(MyCircleDLinkNode node) {
        if (head == null ) {
            node.next = node;
            node.prev = node;
            head = node;
            last = node;
            size = 1;
        }
        node.next = head;
        head.prev = node;
        head = node;
        node.prev = last;
        last.next = node;
        size++;
    }

    void addLastEl(MyCircleDLinkNode node) {
        if (last == null) {
            node.next = node;
            node.prev = node;
            head = node;
            last = node;
            size = 1;
        }
        last.next = node;
        node.prev = last;
        last = node;
        node.next = head;
        head.prev = node;
        size++;
    }

    void traverseFromHead() {
        MyCircleDLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traverseFromHeadAllTheTime() {
        MyCircleDLinkNode current = head;
        for (;  current != null; current = current.next) {
            System.out.print(current.val+"->");
            try {
                Thread.sleep(27_0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    void traverseFromLast() {
        MyCircleDLinkNode current = last;
        for (int i = 0; i < size && current != null; i++, current=current.prev) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traverseFromLastAllTheTime() {
        MyCircleDLinkNode current = last;
        for (;  current != null;  current=current.prev) {
            System.out.print(current.val+"->");
            try {
                Thread.sleep(27_0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }
}
