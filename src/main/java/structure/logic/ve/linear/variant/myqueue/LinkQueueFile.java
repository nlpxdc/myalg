package structure.logic.ve.linear.variant.myqueue;

class LinkQueueApp {
    public static void main(String[] args) {
        LinkQueue linkQueue = new LinkQueue();
        LinkNode linkNode0 = new LinkNode(0);
        LinkNode linkNode1a = new LinkNode(1);
        LinkNode linkNode1b = new LinkNode(1);
        LinkNode linkNode2 = new LinkNode(2);

        linkNode0.next = linkNode1a;
        linkNode1a.next = linkNode1b;
        linkNode1b.next = linkNode2;

        linkQueue.head = linkNode0;
        linkQueue.size = 4;

        linkQueue.traverse();

        linkQueue.offer(new LinkNode(-1));
        linkQueue.traverse();

        LinkNode poll = linkQueue.poll();
        linkQueue.traverse();

        LinkNode head = linkQueue.getHead();
        linkQueue.traverse();

        LinkNode tail = linkQueue.getTail();
        linkQueue.traverse();
    }
}

class LinkNode {
    int val;
    LinkNode next;

    LinkNode(int val) {
        this.val = val;
    }
}

class LinkQueue {
    LinkNode head;
    int size;

    LinkQueue() {
        head = null;
        size = 0;
    }

    void offer(LinkNode node) {
        if (head == null) {
            head = node;
            size = 1;
            return;
        }
        LinkNode current = head;
        for (int i = 0; i < size && current != null && current.next != null; i++, current = current.next) {

        }
        current.next = node;
        size++;
    }

    LinkNode poll() {
        if (head == null) {
            throw new RuntimeException("queue empty");
        }
        LinkNode ret = head;
        head = head.next;
        size--;
        return ret;
    }

    LinkNode getHead() {
        return head;
    }

    LinkNode getTail() {
        LinkNode current = head;
        for (int i = 0; i < size && current!=null && current.next!=null; i++, current=current.next) {

        }
        return current;
    }

    void traverse() {
        LinkNode current = head;
        for (int i = 0; i < size && current!=null; i++, current=current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

}
