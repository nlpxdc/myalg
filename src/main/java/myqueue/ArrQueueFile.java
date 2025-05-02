package myqueue;

class ArrQueueApp {
    public static void main(String[] args) {
        ArrQueue arrQueue = new ArrQueue();
        arrQueue.offer(new Node(0));
        arrQueue.offer(new Node(1));
        arrQueue.offer(new Node(1));
        arrQueue.offer(new Node(2));

        arrQueue.traverse();

        Node poll = arrQueue.poll();
        arrQueue.traverse();

        Node head = arrQueue.getHead();
        Node tail = arrQueue.getTail();

        Node poll1 = arrQueue.poll();
        arrQueue.traverse();

        Node poll2 = arrQueue.poll();
        arrQueue.traverse();
    }
}

class Node {
    int val;

    Node(int val) {
        this.val = val;
    }
}

class ArrQueue {
    Node[] nodeAry;
    int size;

    ArrQueue() {
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
