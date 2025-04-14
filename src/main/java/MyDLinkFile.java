class MyDLinkApp {
    public static void main(String[] args) {
//        MyDLinkNode node0 = new MyDLinkNode(1);
//        MyDLinkNode node1 = new MyDLinkNode(2);
//        MyDLinkNode node2 = new MyDLinkNode(5);
//        node0.next = node1;
//        node1.next =  node2;
//        node2.next = node0;
//        node2.prev = node1;
//        node1.prev = node0;
//        node0.prev = node2;

        MyDLinkNode node0 = new MyDLinkNode(1);
        MyDLinkNode node1 = new MyDLinkNode(2);
        MyDLinkNode node2 = new MyDLinkNode(5);
        node0.next = node1;
        node1.prev = node0;

        node1.next = node2;
        node2.prev = node1;

        node2.next = node0;
        node0.prev = node2;

        MyDLinkList myDLinkList = new MyDLinkList();
        myDLinkList.head = node0;
        myDLinkList.last = node2;
        myDLinkList.size = 3;

        myDLinkList.addEl(new MyDLinkNode(8));

        myDLinkList.traverseNext();
        myDLinkList.traversePrev();
//        myDLinkList.traverseNextAllTheTime();
        myDLinkList.traversePrevAllTheTime();
    }
}

class MyDLinkNode {
    int val;
    MyDLinkNode prev;
    MyDLinkNode next;

    MyDLinkNode(int val) {
        this.val = val;
    }
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

    void addEl(MyDLinkNode node) {
        if (head == null) {
            head = node;
            last = node;
            size=1;
            return;
        }
        MyDLinkNode origLastNext = last.next;
        last.next = node;
        node.prev = last;
        node.next = origLastNext;
        head.prev = head.prev == null ? null : node;
        last = node;
        size++;
    }

    void traverseNext() {
        MyDLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traversePrev() {
        MyDLinkNode current = last;
        for (int i = 0; i < size && current != null; i++, current=current.prev) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traverseNextAllTheTime() {
        MyDLinkNode current = head;
        for (; current != null ; current = current.next ) {
            System.out.print(current.val+"->");
            try {
                Thread.sleep(27_0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    void traversePrevAllTheTime() {
        MyDLinkNode current = last;
        for (; current != null ; current = current.prev ) {
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
