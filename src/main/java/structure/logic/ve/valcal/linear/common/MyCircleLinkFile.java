package structure.logic.ve.valcal.linear.common;

class MyCircleLinkApp {
    public static void main(String[] args) {
        MyCircleLinkNode node0 = new MyCircleLinkNode(1);
        MyCircleLinkNode node1 = new MyCircleLinkNode(3);
        MyCircleLinkNode node2 = new MyCircleLinkNode(5);
        node0.next = node1;
        node1.next = node2;
        node2.next = node0;

        MyCircleLinkList myCircleLinkList = new MyCircleLinkList();
        myCircleLinkList.head = node0;
        myCircleLinkList.size = 3;

        myCircleLinkList.addHeadEl(new MyCircleLinkNode(0));
        myCircleLinkList.addLastEl(new MyCircleLinkNode(9));

//        myCircleLinkList.traverse();
        myCircleLinkList.traverseAllTheTime();

    }
}

class MyCircleLinkNode {
    int val;
    MyCircleLinkNode next;

    MyCircleLinkNode(int val) {
        this.val = val;
    }
}

class MyCircleLinkList {
    MyCircleLinkNode head;
    int size;

    MyCircleLinkList() {
        head = null;
        size = 0;
    }

    void addHeadEl(MyCircleLinkNode node) {
        if (head == null) {
            node.next = node;
            head = node;
            size = 1;
        }
        MyCircleLinkNode current = head;
        for (int i = 0; i < size-1 && current != null && current.next != null; i++, current = current.next) {
//            System.out.println(current.val);
        }
        current.next = node;
        node.next = head;
        head = node;
        size++;
    }

    void addLastEl(MyCircleLinkNode node) {
        if (head == null) {
            node.next = node;
            head = node;
            size = 1;
        }
        MyCircleLinkNode current = head;
        for (int i = 0; i < size-1 && current!=null && current.next!=null; i++, current=current.next) {

        }
        current.next = node;
        node.next = head;
        size++;
    }

    void traverse() {
        MyCircleLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traverseAllTheTime() {
        MyCircleLinkNode current = head;
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

}
