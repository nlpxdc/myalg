package structure.logic.ve.linear.variant.myqueue;

class AryQueueApp {
    public static void main(String[] args) {
        AryQueue aryQueue = new AryQueue();
        aryQueue.offer(new Node(0));
        aryQueue.offer(new Node(1));
        aryQueue.offer(new Node(1));
        aryQueue.offer(new Node(2));

        aryQueue.traverse();

        Node poll = aryQueue.poll();
        aryQueue.traverse();

        Node head = aryQueue.getHead();
        Node tail = aryQueue.getTail();

        Node poll1 = aryQueue.poll();
        aryQueue.traverse();

        Node poll2 = aryQueue.poll();
        aryQueue.traverse();
    }
}

class Node {
    int val;

    Node(int val) {
        this.val = val;
    }
}

class AryQueue {
    Node[] nodeAry;
    int size;

    AryQueue() {
        nodeAry = new Node[1000];
        size = 0;
    }

    //队头进，队尾出（更快，不用移动）

    void offer(Node node) {
        for (int i = size-1; i >= 0; i--) {
            nodeAry[i+1] = nodeAry[i];
        }
        nodeAry[0] = node;
        size++;
    }

    Node poll() {
        if (size <= 0 || nodeAry[size-1] == null) {
            throw new RuntimeException("no tail");
        }
        Node ret = nodeAry[size-1];
//        nodeAry[size-1] = null;
//        size--;
        nodeAry[--size] = null;
        return ret;
    }

    Node getHead() {
        if (size <= 0 || nodeAry[0] == null) {
            throw new RuntimeException("no head");
        }
        return nodeAry[0];
    }

    Node getTail() {
        if (size <= 0 || nodeAry[size-1] == null) {
            throw new RuntimeException("no tail");
        }
        return nodeAry[size-1];
    }

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
    }

}
