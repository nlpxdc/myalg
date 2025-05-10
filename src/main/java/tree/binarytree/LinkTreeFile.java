package tree.binarytree;

class LinkTreeApp {
    public static void main(String[] args) {
        TreeNode treeNodea1 = new TreeNode(1);

        TreeNode treeNodeb1 = new TreeNode(2);
        TreeNode treeNodeb2 = new TreeNode(3);

        TreeNode treeNodec1 = new TreeNode(4);
        TreeNode treeNodec2 = new TreeNode(5);
        TreeNode treeNodec3 = new TreeNode(6);
        TreeNode treeNodec4 = new TreeNode(7);

        treeNodea1.left = treeNodeb1;
        treeNodea1.right = treeNodeb2;

        treeNodeb1.left = treeNodec1;
        treeNodeb1.right = treeNodec2;
        treeNodeb2.left = treeNodec3;
        treeNodeb2.right = treeNodec4;

        LinkTree linkTree = new LinkTree();
        linkTree.root = treeNodea1;
        linkTree.size = 7;

        linkTree.traverseBfs();
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
        if (root == null) {
            throw new RuntimeException("empty tree");
        }
        DeLinkQueue tempQueue = new DeLinkQueue();

        tempQueue.offer(new DeLinkNode(root));
        for (int i = 0; i < size && tempQueue.size > 0; i++) {
            DeLinkNode linkNode = tempQueue.poll();
            TreeNode treeNode = linkNode.val;
            System.out.print(treeNode.val+",");
            if (treeNode.left != null) {
                tempQueue.offer(new DeLinkNode(treeNode.left));
            }
            if (treeNode.right != null) {
                tempQueue.offer(new DeLinkNode(treeNode.right));
            }
        }
        System.out.println();
    }
}

//class Node {
//    int val;
//    Node(int val) {
//        this.val = val;
//    }
//}

class DeLinkNode {
    //这里可以是泛型
    TreeNode val;
    DeLinkNode prev;
    DeLinkNode next;

    DeLinkNode(TreeNode val) {
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
            return;
        }
        node.next = head;
        node.prev = null;
        head.prev = node;
        head = node;
        size++;
        return;
    }

    DeLinkNode poll() {
        if (tail == null) {
            throw new RuntimeException("queue empty");
        }
        if (head == tail) {
            DeLinkNode ret = tail;
            head = null;
            tail = null;
            size = 0;
            return ret;
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


