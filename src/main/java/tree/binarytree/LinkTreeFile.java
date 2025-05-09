package tree.binarytree;

class LinkTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class LinkTree {
    TreeNode root;
    int size;
    
    void traverseBfs() {
        DeLinkQueue deLinkQueue = new DeLinkQueue();
        
    }
}

//class Node {
//    int val;
//    Node(int val) {
//        this.val = val;
//    }
//}

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


