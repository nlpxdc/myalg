package tree.binarytree;

import java.util.LinkedList;

class LinkTreeApp {
    public static void main(String[] args) {
//        TreeNode treeNodea1 = new TreeNode(1);
//
//        TreeNode treeNodeb1 = new TreeNode(2);
//        TreeNode treeNodeb2 = new TreeNode(3);
//
//        TreeNode treeNodec1 = new TreeNode(4);
//        TreeNode treeNodec2 = new TreeNode(5);
//        TreeNode treeNodec3 = new TreeNode(6);
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

        TreeNode treeNodea1 = new TreeNode(4);

        TreeNode treeNodeb1 = new TreeNode(2);
        TreeNode treeNodeb2 = new TreeNode(6);

        TreeNode treeNodec1 = new TreeNode(1);
        TreeNode treeNodec2 = new TreeNode(3);
        TreeNode treeNodec3 = new TreeNode(5);
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

//        linkTree.traverseBfs();
//        linkTree.traverseDfsPreOrder();
//        linkTree.traverseDfsPreOrderStack();
//        linkTree.traverseDfsPostOrder();
//        linkTree.traverseDfsInOrder();

//        linkTree.traverseBfs();
//        linkTree.traverseBfsLevelOrder1();
//        linkTree.traverseBfsLevelOrder2();

//        linkTree.traverseDfsPreOrder();
//        linkTree.traverseDfsPreOrderOneStack();

//        linkTree.traverseDfsInOrder();
//        linkTree.traverseDfsInOrderOneStack();

        linkTree.traverseDfsPostOrder();
        linkTree.traverseDfsPostOrderOneStack();
    }
}

//root 根节点 裸指针版本
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

//树的封装版本
class LinkTree {
    TreeNode root;
    int size;

    //Bfs 有多层次 多行 多队列 或 一队列+标记
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

    void traverseBfsLevelOrderOneLineWithOneQueue() {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        //init
        queue.offer(root);

        for (int i = 0; i < size && !queue.isEmpty(); i++) {
            TreeNode node = queue.poll();
            System.out.print(node.val+",");

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        System.out.println();

    }

    void traverseBfsLevelOrderMultiLineWithTwoQueue() {
        if (root == null) {
            throw new RuntimeException("empty tree");
        }

        LinkedList<TreeNode> currentLevelQueue = new LinkedList<>();
        LinkedList<TreeNode> downLevelQueue = new LinkedList<>();


        //init 初始化
        currentLevelQueue.offer(root);

        //iterate loop
        for (int i = 0; i < size; i++) {
            //打印当前层
            for (int j = 0; j < size && !currentLevelQueue.isEmpty(); j++) {
                TreeNode node = currentLevelQueue.poll();
                System.out.print(node.val+",");
                //保留下层打印节点
                if (node.left != null) {
                    downLevelQueue.offer(node.left);
                }
                if (node.right != null){
                    downLevelQueue.offer(node.right);
                }
            }
            System.out.println();
            for (int j = 0; j < size && !downLevelQueue.isEmpty(); j++) {
                currentLevelQueue.offer(downLevelQueue.poll());
            }
        }
    }

    //按层次进行层序遍历，只用一个队列的情况下做到，树的节点需要额外信息加上深度，写的时候就要维护，写扩散，第一人称视角 3+1
    //或者想办法，靠外界，上帝视角3+0，记录深度进行处理
    void traverseBfsLevelOrderMultiLineWithOneQueue() {

    }

    //Dfs 无多层次 一行 一栈 --------------------------------------------
    //多重递归一定不是尾递归，主要看代码非最后一行的递归调用，就需要一个栈来记录，保存前面n个递归调用，非原始递归
    //sout用visit(node)表示，可以代表一整段复杂逻辑

    //借助栈模拟递归
    //但是这里是多分支递归，所以这里可以借助栈记录递归的反向顺序，而不是模拟递归
    void innerTraverseDfsPreOrder(TreeNode node) {
        System.out.print(node.val+",");
        if (node.left != null) {
            innerTraverseDfsPreOrder(node.left);
        }
        if (node.right != null) {
            innerTraverseDfsPreOrder(node.right);
        }
    }

    //Dfs pre order 从前到后 1st
    void traverseDfsPreOrder() {
        innerTraverseDfsPreOrder(root);
        System.out.println();
    }

    //子节点进一栈 or 二栈？ 二栈降维一栈合并一栈？ or 就只是一栈？
    void traverseDfsPreOrderOneStack() {
        LinkedList<TreeNode> stack = new LinkedList<>();

        //init
        stack.push(root);

        //iter loop
        for (int i = 0; i < size && !stack.isEmpty(); i++) {
            TreeNode node = stack.pop();
            System.out.print(node.val+",");

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        System.out.println();
    }

    //多家额外参数，例如列表，有状态处理，有状态就要考虑并发，线程变量，cas，锁
    void visit(TreeNode node) {
        //can be any logic
        System.out.print(node.val+",");
    }

    //树的中序，借助1个额外变量？
    //借助栈模拟递归，单无需栈记录递归的反向顺序，因为只有一个没顺序可言
    //所以二叉树的中序遍历，最像链表，用栈模拟递归，就是反过来的特性
    //多叉树就没有这个特性了
    //就只有二叉树有中序遍历，其他多叉树不好定义，否则需要额外定义规则，也就要额外的变量来记录处理了，那这个就算了
    //而且只适用于都是节点定义的结构，而不是不定长的通用结构数组或者列表定义？应该也可以，但是根据规则，也要额外变量，处理当中为一个整体的数组或者列表可能更不方便
    //所以结论，中序遍历只适用于二叉树这个特殊的树种，就像2是唯一一个偶数的质数素数
    //2的组合可以有中间态，形成3，然后3真形成稳定格局，形成pattern形成模式，最后加入任意一个变量，形成4也就是万物
    void innerTraverseDfsInOrder(TreeNode node) {
        if (node.left != null) {
            innerTraverseDfsInOrder(node.left);
        }
//        System.out.print(node.val+",");
        visit(node);
        if (node.right != null) {
            innerTraverseDfsInOrder(node.right);
        }
    }

    //Dfs in order 2..n-1
    void traverseDfsInOrder() {
        innerTraverseDfsInOrder(root);
        System.out.println();
    }

    // length-n stacks
    void traverseDfsInOrderOneStack() {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();

        //init
        TreeNode current = root;

        //iter loop
        for (int i = 0; i < size && (current != null || !stack.isEmpty()); i++) {
            for (int j = 0; j < size && current != null; j++) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            visit(current);
            current = current.right;
        }

    }

    //todo 无递归无栈，线索化中序遍历，线索二叉树

    //后续遍历，借助两个额外变量？（k-way多路树，借助k个多个额外变量？）
    //既用栈模拟递归，又用栈记录递归的反向顺序
    void innerTraverseDfsPostOrder(TreeNode node) {
        if (node.left != null) {
            innerTraverseDfsPostOrder(node.left);
        }
        if (node.right != null) {
            innerTraverseDfsPostOrder(node.right);
        }
        System.out.print(node.val+",");
    }

    //Dfs post order 从后到前 nth last
    void traverseDfsPostOrder() {
        innerTraverseDfsPostOrder(root);
        System.out.println();
    }

    void traverseDfsPostOrderOneStack() {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();

        //init
        TreeNode current = root;
        TreeNode lastVisitNode = null;

        //iter loop
//        for (int i = 0; i < size && (current != null || !stack.isEmpty()); i++) {
        for (int i = 0; i < size*2 && (current != null || !stack.isEmpty()); i++) {
            for (int j = 0; j < size && current != null; j++) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (current.right == null || current.right == lastVisitNode) {
                visit(current);
                lastVisitNode = current;
                current = null;
            } else {
                stack.push(current);
                current = current.right;
            }
        }
    }

    //todo 树的旋转？

    //todo 节点添加（树的分裂可能） 和 节点删除（树的合并可能） 尤其类似b树b+树
    
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

//todo (B)BST rw rcdu
