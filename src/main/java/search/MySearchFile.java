package search;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

//查找第一个或者最后一个，查找多个的话就是字符串匹配，正则表达式，状态机了，有一整套完善的
//普通场景，主键，值都不同，索引失效不！
//总体思想可以先递归再迭代，一般来说递归更简单更会符合贴切的描述，尤其是树，线性结构其实也是，只是线性结构迭代也很好思考，性能又好，所以一般会直接思考迭代，而不考虑递归了
class MySearchApp {
    public static void main(String[] args) {
//        System.out.println("aa");
//        String str = "abc";
//        char[] charArray = str.toCharArray();
//        int i = str.indexOf('b');
//        int i1 = Arrays.binarySearch(charArray, 'b');
//        int i2 = Collections.binarySearch(null, null);
        //构造数据分布错误了，是类质数的斐波那契，我说调试不太理想
        int[] ary = {0,1,2,3,5,8,13,21,34,55};
        //可以再构造线性分布均匀数组，调试更直观
//        int i = idxOfIter(ary, 13);
//        int i2 = idxOfRecur(ary, 13);
//        int i4 = idxOfIterInterpolation(ary, 13);
//        int i1 = idxOfIter(ary, 14);
//        int i3 = idxOfRecur(ary, 14);
//        int i5 = idxOfIterInterpolation(ary, 14);
        Avl avl = new Avl(ary);
//        avl.traverseInOrder();
//        avl.traverseInOrderByAddrIdx();
        avl.traverseInOrderByAll();
//        TreeNode search = avl.search(5);
//        TreeNode search1 = avl.search(6);
//        TreeNode treeNode = avl.searchIter(5);
//        TreeNode treeNode1 = avl.searchIter(6);
//        TreeNode byKeyToAddParent = avl.getByKeyToAddParent(5);
//        TreeNode byKeyToAddParent1 = avl.getByKeyToAddParent(6);
//        TreeNode byKeyToAddParentIter = avl.getByKeyToAddParentIter(5);
//        TreeNode byKeyToAddParentIter1 = avl.getByKeyToAddParentIter(6);
//        TreeNode byKeyToAddParentInOrder = avl.getByKeyToAddParentInOrder(5);
//        TreeNode byKeyToAddParentInOrder1 = avl.getByKeyToAddParentInOrder(6);

//        SingleLinkList singleLinkList = new SingleLinkList(ary);
//        singleLinkList.traverse();
//        LinkNode byKey = singleLinkList.findByKey(5);
//        LinkNode byKey1 = singleLinkList.findByKey(6);
//        LinkNode byKeyToAddPrev = singleLinkList.findByKeyToAddPrev(5);
//        LinkNode byKeyToAddPrev1 = singleLinkList.findByKeyToAddPrev(6);
////        LinkNode byKeyIter = singleLinkList.findByKeyIter(5);
////        LinkNode byKeyIter1 = singleLinkList.findByKeyIter(6);
//        LinkNode byKeyToAddPrevIter = singleLinkList.findByKeyToAddPrevIter(5);
//        LinkNode byKeyToAddPrevIter1 = singleLinkList.findByKeyToAddPrevIter(6);

    }

    //1 iter 迭代二分，jdk官方实现，双指针，前后指针首尾指针
    static int idxOfIter(int[] ary, int val) {
        int leftIdx = 0, rightIdx = ary.length-1;

        for (int i = 0; i < Integer.MAX_VALUE && leftIdx<=rightIdx; i++) {
//            int mid = leftIdx + ((rightIdx - leftIdx) >>> 1); // 防溢出写法
            int midIdx = (leftIdx+rightIdx)/2;
            int midVal = ary[midIdx];
            if (val < midVal) {
                rightIdx = midIdx-1;
            } else if (midVal < val) {
                leftIdx = midIdx+1;
            } else {
                return midIdx;
            }
        }

//        return -1;
        //用负数代表没找到，但是指明需要插入的位置
        return -(leftIdx+1);
    }

    //2 recur
    static int idxOfRecur(int[] ary, int val) {
        int idx = innerIdxOfRecur(ary, 0, ary.length, val);
        return idx;
    }

    static int innerIdxOfRecur(int[] ary, int leftIdx, int rightIdx, int val) {
        if (leftIdx > rightIdx) {
            return -(leftIdx+1);
        }
        int midIdx = (leftIdx+rightIdx)/2;
        int midVal = ary[midIdx];
        if (val < midVal) {
            return innerIdxOfRecur(ary, leftIdx, midIdx-1, val);
        } else if (val > midVal) {
            return innerIdxOfRecur(ary, midIdx+1, rightIdx, val);
        } else {
            return midIdx;
        }
//        return -1;
    }

    //斐波那契搜做，斐波那契区间分割，升级二分法
    //避免了除法或者乘法，即mid的计算，如果用位移，那就不考虑了
    //为什么斐波那契？计算最简单 + 比例最好

    //3 插值思想
    static int idxOfIterInterpolation(int[] ary, int val) {
        int leftIdx = 0, rightIdx = ary.length-1;

        for (int i = 0; i < Integer.MAX_VALUE && leftIdx<=rightIdx; i++) {
            //midIdx 插值猜位置，不是简单的二分，要根据值来
            int midIdx = calcMidIdx(ary, leftIdx, rightIdx, val);

            //二分比较
            int midVal = ary[midIdx];
            if (val < midVal) {
                rightIdx = midIdx-1;
            } else if (midVal < val) {
                leftIdx = midIdx+1;
            } else {
                return midIdx;
            }
        }

        return -(leftIdx+1);
    }

    //测试起始是需要自己构造合适的测试数据，并来验证他优化方面的性能的
    //但是测试数据造起来耗时，属于测试阶段，暂时先不管了，后续专门抽时间弄，先保证功能ok
    //插值计算，根据value，查找对应所在合适的idx位置，更准的猜准接近的位置
    //没有类似快排pivot的多种复杂算法，因为本身已经拍好序列了，取中就是很好的办法，但是可以使用等比例猜测的方法，试图更快接近真实值，依赖线性均匀分布假设，如连续id，时间戳
    //避免指数增长，或者斐波那契！会退化成线性扫描，此时二分更好，没有银弹，通用vs特殊，数据分布情况和概率
    static int calcMidIdx(int[] ary, int lowIdx, int highIdx, int val) {
        int midIdx = lowIdx;
        int lowVal = ary[lowIdx];
        int highVal = ary[highIdx];
        int valLowDelta = val - lowVal;
        int idxWholeDelta = highIdx - lowIdx;
        int valWholeDelta = highVal - lowVal;
        if (valWholeDelta != 0) {
            //线性等比例计算
            int idxLowDelta = idxWholeDelta * valLowDelta / valWholeDelta;
            midIdx = lowIdx + idxLowDelta;
        }
        //防止越界
        midIdx = Math.max(lowIdx, Math.min(midIdx, highIdx));
        return midIdx;
    }

    //4 利用Avl（平衡二叉搜索树）
    //笛卡尔数 笛卡尔树是静态场景的 RMQ 神器
    //树堆 treap Treap 把随机优先级当成“隐形的平衡器”，让它在动态竞赛/工程场景里更简洁、更鲁棒
    //min-max heap？双端优先队列
    //FHQTreap Splay Tree 替罪羊树
    //所有 BST BBST 平衡二叉搜索树 都在用不同的‘平衡+存储’策略，把最坏 O(n) 压回 O(log n)，并适配内存、磁盘、并发或多维数据
    //二分查找应该用的树是avl Catalan数
    //红黑树看下b站视频

    //堆序 kth类二分查找

    //根据有序数组，构造avl
    //根据avl，做查找 外存b数b+数的根基？
    //根据有序数组，构造treap
    //根据treap，做查找 外存b数b+数的根基？

    //递归版本时间O(h)O(depth)或O(logn) + 空间O(n) 迭代版时间O(h)O(depth)O(logn) + 空间O(1)
    //根据有序数组，构造avl，普通递归二分法（用中间节点作为根）
    //根据Avl，做查找（二分思想） 递归版 迭代版
    //todo 根据Avl，做查找，并返回jdk类似的插入位置 递归版 迭代版

}

class TreeNode {
    int key;
    String addrIdx;
    TreeNode left;
    TreeNode right;

    TreeNode(int key) {
        this.key = key;
        int randInt = ThreadLocalRandom.current().nextInt(100, 999);
        this.addrIdx = Integer.toString(randInt, 17);
    }
}

class Avl {
    TreeNode root;
    int size;

    //根据有序数组初始化平衡二叉搜索树
    Avl(int[] ary) {
        if (ary == null || ary.length == 0) {
            root = null;
            return;
        }
        root = innerBuild(ary, 0, ary.length - 1);
        size = ary.length;
    }

    TreeNode innerBuild(int[] ary, int lowIdx, int highIdx) {
        if (lowIdx > highIdx) {
            return null;
        }
        int midIdx = lowIdx + (highIdx - lowIdx)/2;
        int midKey = ary[midIdx];
        TreeNode root = new TreeNode(midKey);
        root.left = innerBuild(ary, lowIdx, midIdx-1);
        root.right = innerBuild(ary, midIdx+1, highIdx);
        return root;
    }

    void traverseInOrder() {
        innerTraverseInOrder(root);
        System.out.println();
    }

    void traverseInOrderByAddrIdx() {
        innerTraverseInOrderByAddrIdx(root);
        System.out.println();
    }

    void innerTraverseInOrderByAddrIdx(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            innerTraverseInOrderByAddrIdx(root.left);
        }
        System.out.print(root.addrIdx+",");
        if (root.right != null) {
            innerTraverseInOrderByAddrIdx(root.right);
        }
    }

    void traverseInOrderByAll() {
        innerTraverseInOrderByAll(root);
        System.out.println();
    }

    void innerTraverseInOrderByAll(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            innerTraverseInOrderByAll(root.left);
        }
        System.out.print(String.format("[%d->%s],", root.key, root.addrIdx));
        if (root.right != null) {
            innerTraverseInOrderByAll(root.right);
        }
    }

    void innerTraverseInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            innerTraverseInOrder(root.left);
        }
        System.out.print(root.key+",");
        if (root.right != null) {
            innerTraverseInOrder(root.right);
        }
    }

    TreeNode search(int key) {
        if (root == null) {
            return null;
        }
        TreeNode node = innerSearch(root, key);
        return node;
    }

    TreeNode searchIter(int key) {
        for (TreeNode current = root; current != null; ) {
            if (current.key == key) {
                return current;
            } else {
                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        return null;
    }

    TreeNode innerSearch(TreeNode currentRoot, int key) {
        if (currentRoot == null) {
            return null;
        }
        if (currentRoot.key == key) {
            return currentRoot;
        } else {
            if (currentRoot.left != null) {
                return innerSearch(currentRoot.left, key);
            }
            if (currentRoot.right != null) {
                return innerSearch(currentRoot.right, key);
            }
        }
        return null;
    }

    TreeNode getByKeyToAddParent(int key) {
        TreeNode node = innerGetByKeyToAddParent(null, root, key);
        return node;
    }

    TreeNode innerGetByKeyToAddParent(TreeNode currentParent, TreeNode currentRoot, int key) {
        if (currentParent == null && currentRoot == null) {
            return new TreeNode(-1);
        }
        if (currentRoot == null) {
            return currentParent;
        } else {
            if (key == currentRoot.key) {
                return null;
            } else {
                if (key < currentRoot.key) {
                    if (currentRoot.left != null) {
                        return innerGetByKeyToAddParent(currentRoot, currentRoot.left, key);
                    } else {
                        return currentRoot;
                    }
                } else {
                    if (currentRoot.right != null) {
                        return innerGetByKeyToAddParent(currentRoot, currentRoot.right, key);
                    } else {
                        return currentRoot;
                    }
                }
            }
        }
    }

//    //todo 这个递归是否可以用中序遍历？
//    TreeNode getByKeyToAddParentInOrder(int key) {
//        LimitQueue prevNodeList = new LimitQueue(2);
//        if (root == null) {
//            return new TreeNode(-1);
//        }
//        TreeNode treeNode = innerGetByKeyToAddParentInOrder(prevNodeList, root, key);
////        prevNodeList.traverse();
//        return treeNode;
//    }

//    TreeNode innerGetByKeyToAddParentInOrder(LimitQueue prevNodeList, TreeNode currentRoot, int key) {
//        if (currentRoot.left != null) {
//            return innerGetByKeyToAddParentInOrder(prevNodeList, currentRoot.left, key);
//        }
//
//        TreeNode lowTreeNode = null;
//        if (prevNodeList.nodeList.size() >=1) {
//            lowTreeNode = prevNodeList.getTreeNode(0);
//        }
//        TreeNode highTreeNode = null;
//        if (prevNodeList.nodeList.size() >=2) {
//            highTreeNode = prevNodeList.getTreeNode(1);
//        }
////        if (lowTreeNode ==)
//
////        if (key == lowTreeNode.key || key == highTreeNode.key) {
////            return null;
////        }
////        if (key < lowTreeNode.key) {
////            return null;
////        } else if (key > highTreeNode.key) {
////            return null;
////        } else {
////            return lowTreeNode;
////        }
//
//        if (currentRoot.right != null) {
//            return innerGetByKeyToAddParentInOrder(prevNodeList, currentRoot.right, key);
//        }
//        return null;
//    }

    TreeNode getByKeyToAddParentIter(int key) {
        return null;
    }

}

//class LinkNode {
//    int key;
//    String addrIdx;
//    LinkNode next;
//
//    LinkNode(int key) {
//        this.key = key;
//        int randInt = ThreadLocalRandom.current().nextInt(100, 999);
//        this.addrIdx = Integer.toString(randInt, 17);
//    }
//}

//todo 还有双链表，会更加简单些
//双链表的递归，O(n/2)，少一半时间，但达不到O(logn)，没有办法每次迭代都减半，因为没有全局位置，数组有全局连续位置
//这个不需要prev前引用，因为直接能返回往上找，但是从双端递归，所以依旧是前后双递归引用，找到位置后，往前直接能找到，所以形式上和但链表一样，但是含义不一样
//迭代写法直观看到减半

//todo 如果要写dummy 一致性代码
//这样树是不是也会更难一些？但是如果树也有指针指向父母节点，是不是就会也好些？
//单链表的递归，O(n)，递归方法需要借助prev引用来临时记录，返回前节点，不然就需要遍历完以后借助临时记录数据再遍历一次到指定位置才行
//所以递归需要外加一个前指针用来临时保存，传递到下次递归中，这样就不用再遍历一边回头找了，而且这个是尾递归
//迭代写法ok
//class SingleLinkList {
//    LinkNode head;
////    int size;
//
//    SingleLinkList(int[] ary) {
//        if (ary == null || ary.length <=0) {
//            return;
//        }
////        LinkNode node = innerBuildList(ary, 0, ary.length-1);
//        LinkNode head = new LinkNode(ary[0]);
//
//        LinkNode current = head;
//        for (int i = 1; i < ary.length; i++, current = current.next) {
//            int key = ary[i];
//            LinkNode next = new LinkNode(key);
//            current.next = next;
//        }
//        this.head = head;
//    }
//
//    LinkNode innerBuildList(int[] ary, int lowIdx, int highIdx) {
//        if (lowIdx > highIdx) {
//            return null;
//        }
//        int key = ary[lowIdx];
//        LinkNode node = new LinkNode(key);
//        node.next = innerBuildList(ary, lowIdx+1, highIdx);
//        return node;
//    }
//
//    void traverse() {
//        for (LinkNode current = head; current != null; current = current.next) {
//            System.out.print(String.format("[%d->%s],", current.key, current.addrIdx));
//        }
//    }
//
//    LinkNode findByKey(int key) {
//        LinkNode node = innerFindByKey(head, key);
//        return node;
//    }
//
//    //如果key存在就返回null，没有待插入的位置
//    //如果key不存在就返回待插入的前置父节点
//    LinkNode findByKeyToAddPrev(int key) {
//        LinkNode node = innerFindByKeyToAddPrev(null, head, key);
//        return node;
//    }
//
//    LinkNode findByKeyIter(int key) {
//        for (LinkNode current = head; current != null; current = current.next) {
//            if (current.key == key) {
//                return current;
//            }
//        }
//        return null;
//    }
//
//    LinkNode findByKeyToAddPrevIter(int key) {
//        for (LinkNode prev = null, current = head; current != null; prev = current, current = current.next) {
//            if (key == current.key) {
//                return null;
//            } else {
//                if (current.next == null) {
//                    return current;
//                } else {
//                    if (key < current.key) {
//                        return prev;
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        }
//        return new LinkNode(-1);
//    }
//
//    LinkNode innerFindByKey(LinkNode head, int key) {
//        if (head == null) {
//            return null;
//        }
//        if (head.key == key) {
//            return head;
//        } else {
//            if (head.next != null) {
//                return innerFindByKey(head.next, key);
//            } else {
//                return null;
//            }
//        }
//    }
//
//    LinkNode innerFindByKeyToAddPrev(LinkNode prev, LinkNode head, int key) {
//        if (prev == null && head == null) {
//            return new LinkNode(-1);
//        }
//        if (head.key == key) {
//            return null;
//        } else {
//            if (head.next != null) {
//                if (key < head.key) {
//                    return prev;
//                } else {
//                    return innerFindByKeyToAddPrev(head, head.next, key);
//                }
//            } else {
//                return head;
//            }
//        }
//    }
//
//}

//class LimitQueue {
//    LinkedList<TreeNode> nodeList;
//    int limitSize;
//
//    LimitQueue(int limitSize) {
//        nodeList = new LinkedList<>();
//        this.limitSize = limitSize;
//    }
//
//    void offer(TreeNode treeNode) {
//        if (nodeList.size() >= limitSize) {
//            nodeList.poll();
//        }
//        nodeList.offer(treeNode);
//    }
//
//    TreeNode getTreeNode(int idx) {
//        TreeNode treeNode = nodeList.get(idx);
//        return treeNode;
//    }
//
//    void traverse() {
//        for (TreeNode treeNode : nodeList) {
//            System.out.print(treeNode.key+",");
//        }
//        System.out.println();
//    }
//
//}
