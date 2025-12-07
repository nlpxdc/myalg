package structure.logic.ve.valcal.linear.variant.myqueue;

class DeLinkQueueApp {
    public static void main(String[] args) {
        DeLinkQueue deLinkQueue = new DeLinkQueue();
        DeLinkNode deLinkNode0 = new DeLinkNode(0);
        DeLinkNode deLinkNode1a = new DeLinkNode(1);
        DeLinkNode deLinkNode1b = new DeLinkNode(1);
        DeLinkNode deLinkNode2 = new DeLinkNode(2);

        deLinkNode0.next = deLinkNode1a;
        deLinkNode0.prev = null;

        deLinkNode1a.next = deLinkNode1b;
        deLinkNode1a.prev = deLinkNode0;

        deLinkNode1b.next = deLinkNode2;
        deLinkNode1b.prev = deLinkNode1a;

        deLinkNode2.next = null;
        deLinkNode2.prev = deLinkNode1b;

        deLinkQueue.head = deLinkNode0;
        deLinkQueue.tail = deLinkNode2;

        deLinkQueue.size = 4;

        deLinkQueue.traverse();

        deLinkQueue.offer(new DeLinkNode(5));
        deLinkQueue.traverse();

        DeLinkNode poll = deLinkQueue.poll();
        deLinkQueue.traverse();

        DeLinkNode head = deLinkQueue.getHead();
        deLinkQueue.traverse();
        DeLinkNode tail = deLinkQueue.getTail();
        deLinkQueue.traverse();
    }
}

class DeLinkNode {
    int val;
    DeLinkNode prev;
    DeLinkNode next;

    DeLinkNode(int val) {
        this.val = val;
        prev = null;
        next = null;
    }
}

class DeLinkQueue {
    int size;
    DeLinkNode head;
    DeLinkNode tail;

    DeLinkQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    void offer(DeLinkNode node) {
        if (node == null) {
            throw new RuntimeException("node null");
        }
        if (head == null) {
            node.next = null;
            node.prev = null;
            head = node;
            tail = node;
            size = 1;
        }
        node.next = head;
        node.prev = null;
        head = node;
        size++;
    }

    DeLinkNode poll() {
        if (tail == null) {
            throw new RuntimeException("queue empty");
        }
        DeLinkNode ret = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return ret;
    }

    DeLinkNode getHead() {
        return head;
    }

    DeLinkNode getTail() {
        return tail;
    }

    void traverse() {
        DeLinkNode current = head;
        for (int i = 0; i < size && current!=null; i++, current=current.next) {
            System.out.print(current.val+",");
        }
        System.out.println();
    }

}
