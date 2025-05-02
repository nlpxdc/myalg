package mystack2;

class LinkStackApp {
    public static void main(String[] args) {
        LinkNode linkNode0 = new LinkNode(0);
        LinkNode linkNode1 = new LinkNode(1);
        LinkNode linkNode2 = new LinkNode(1);
        LinkNode linkNode3 = new LinkNode(2);

        linkNode0.next = linkNode1;
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = null;

        LinkStack linkStack = new LinkStack();
        linkStack.head = linkNode0;
        linkStack.size = 4;

        linkStack.traverse();

        linkStack.push(new LinkNode(-1));
        linkStack.traverse();

        LinkNode popNode = linkStack.pop();
        linkStack.traverse();

        LinkNode peekNode = linkStack.peek();
        linkStack.traverse();

    }
}

class LinkNode {
    int val;
    LinkNode next;

    LinkNode(int val) {
        this.val = val;
    }
}

class LinkStack {
    LinkNode head;
    int size;

    LinkStack() {
        head = null;
        size = 0;
    }

    void push(LinkNode node) {
        if (node == null) {
            throw new RuntimeException("node null");
        }
        node.next = head;
        head = node;
        size++;
    }

    LinkNode pop() {
        if (head == null || size == 0) {
            throw new RuntimeException("already empty");
        }
        LinkNode ret = head;
        head = head.next;
        size--;
        return ret;
    }

    LinkNode peek() {
        LinkNode ret = head;
        return ret;
    }

    void traverse() {
        LinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

}
