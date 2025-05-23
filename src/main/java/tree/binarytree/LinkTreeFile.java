package tree.binarytree;

import java.util.LinkedList;

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

//        TreeNode treeNodea1 = new TreeNode(4);
//
//        TreeNode treeNodeb1 = new TreeNode(2);
//        TreeNode treeNodeb2 = new TreeNode(6);
//
//        TreeNode treeNodec1 = new TreeNode(1);
//        TreeNode treeNodec2 = new TreeNode(3);
//        TreeNode treeNodec3 = new TreeNode(5);
//        TreeNode treeNodec4 = new TreeNode(7);
//
//        treeNodea1.left = treeNodeb1;
//        treeNodea1.right = treeNodeb2;
//
//        treeNodeb1.left = treeNodec1;
//        treeNodeb1.right = treeNodec2;
//        treeNodeb2.left = treeNodec3;
//        treeNodeb2.right = treeNodec4;
//
//        LinkTree linkTree = new LinkTree();
//        linkTree.root = treeNodea1;
//        linkTree.size = 7;

//        linkTree.traverseBfs();
//        linkTree.traverseDfsPreOrder();
//        linkTree.traverseDfsPreOrderStack();
//        linkTree.traverseDfsPostOrder();
//        linkTree.traverseDfsInOrder();
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

    //Bfs
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

    //Dfs pre order
    void traverseDfsPreOrder() {
        innerTraverseDfsPreOrder(root);
        System.out.println();
    }

    //借助栈模拟递归
    void innerTraverseDfsPreOrder(TreeNode node) {
        System.out.print(node.val+",");
        if (node.left != null) {
            innerTraverseDfsPreOrder(node.left);
        }
        if (node.right != null) {
            innerTraverseDfsPreOrder(node.right);
        }
    }

    void traverseDfsPreOrderStack() {
        System.out.print(root.val+",");
        LinkedList<TreeNode> treeNodes = new LinkedList<>();

//        //定义借助的临时stack
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        //初始化stack
//        stack.push(root);
//
//        for (int i = 0; i < size && !stack.isEmpty(); i++) {
//            TreeNode node = stack.pop();
//            System.out.print(node.val+",");
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }
//        System.out.println();
    }

    //Dfs post order
    void traverseDfsPostOrder() {
        innerTraverseDfsPostOrder(root);
        System.out.println();
    }

    void traverseDfsPostOrderStack() {
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//
//        for (int i = 0; i < size && !stack.isEmpty(); i++) {
//
//            System.out.println();
//        }
    }

    //后续遍历，借助两个额外变量？（k-way多路树，借助k个多个额外变量？）
    void innerTraverseDfsPostOrder(TreeNode node) {
        if (node.left != null) {
            innerTraverseDfsPostOrder(node.left);
        }
        if (node.right != null) {
            innerTraverseDfsPostOrder(node.right);
        }
        System.out.print(node.val+",");
    }

    //Dfs in order
    void traverseDfsInOrder() {
        innerTraverseDfsInOrder(root);
        System.out.println();
    }

    void traverseDfsInOrderStack() {

    }

    //树的中序，借助1个额外变量？
    void innerTraverseDfsInOrder(TreeNode node) {
        if (node.left != null) {
            innerTraverseDfsInOrder(node.left);
        }
        System.out.print(node.val+",");
        if (node.right != null) {
            innerTraverseDfsInOrder(node.right);
        }
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


