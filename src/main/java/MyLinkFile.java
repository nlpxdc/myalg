import java.util.Arrays;

class MyLinkApp {
    public static void main(String[] args) {
//        MyLinkList myLinkList = new MyLinkList();
//        myLinkList.addEl(new MyLinkNode(4));
//        myLinkList.addEl(new MyLinkNode(2));
//        MyLinkNode myLinkNode = new MyLinkNode(17);
//        myLinkList.addEl(myLinkNode);
//
//        myLinkList.traverse();
//
////        new int[]{0, 2}
//        int[] idxList = {2, 0};
//        MyLinkUtil.bubbleSort(idxList);
//
//        MyLinkNode[] myLinkNodes = myLinkList.batchGetByIdxList(idxList);
//
//        myLinkList.traverse();

        MyLinkList upList = new MyLinkList();
        upList.addEl(new MyLinkNode(1));
        upList.addEl(new MyLinkNode(2));
        upList.addEl(new MyLinkNode(3));
        upList.addEl(new MyLinkNode(3));
        upList.addEl(new MyLinkNode(5));
        upList.addEl(new MyLinkNode(7));

        upList.traverseRecur();
        upList.traverseRecurBack();

//        MyLinkList downList = new MyLinkList();
//        downList.addEl(new MyLinkNode(2));
//        downList.addEl(new MyLinkNode(4));
//        downList.addEl(new MyLinkNode(6));
//        downList.addEl(new MyLinkNode(7));
//        downList.addEl(new MyLinkNode(7));
//        downList.addEl(new MyLinkNode(8));

//        MyLinkList myLinkList = MyLinkUtil.mergeSort(upList, downList);
//        myLinkList.traverse();
    }
}

class MyLinkNode {
    int val;
    MyLinkNode next;
    //idx? no can

    MyLinkNode(int val) {
        this.val = val;
    }
}

class MyLinkUtil {
    static boolean hasVal(int[] numAry, int v) {
        for (int i = 0; i < numAry.length; i++) {
            if (numAry[i] == v) {
                return true;
            }
        }
        return false;
    }

    static boolean beSortSmallToBig(int[] numAry) {
        for (int i = 0; i < numAry.length - 1; i++) {
            if (numAry[i] >= numAry[i+1]) {
                return false;
            }
        }
        return true;
    }

    static boolean beSortBigToSmall(int[] numAry) {
        for (int i = 0; i < numAry.length; i++) {
            if (numAry[i] <= numAry[i+1]) {
                return false;
            }
        }
        return true;
    }

    static void bubbleSort(int[] numAry) {
        for (int i = 0; i < numAry.length; i++) {
            for (int j = i+1; j < numAry.length; j++) {
                if (numAry[i] > numAry[j]) {
//                    int t = numAry[i];
//                    numAry[i] = numAry[j];
//                    numAry[j] = t;
//                    swap(numAry, i, j);
                    swap(numAry, i, j);
                }
            }
        }
    }

    static void swap(int[] numAry, int idxl, int idxr) {
        int t = numAry[idxl];
        numAry[idxl] = numAry[idxr];
        numAry[idxr] = t;
    }

    static void swap1(int[] numAry, int idxl, int idxr) {
        numAry[idxl] = numAry[idxl] + numAry[idxr];
        numAry[idxr] = numAry[idxl] - numAry[idxr];
        numAry[idxl] = numAry[idxl] - numAry[idxr];
    }

    static void swap2(int[] numAry, int idxl, int idxr) {
        numAry[idxl] = numAry[idxl] * numAry[idxr];
        numAry[idxr] = numAry[idxl] / numAry[idxr];
        numAry[idxl] = numAry[idxl] / numAry[idxr];
    }

    static void swap3(int[] numAry, int idxl, int idxr) {
        numAry[idxl] = numAry[idxl] ^ numAry[idxr];
        numAry[idxr] = numAry[idxl] ^ numAry[idxr];
        numAry[idxl] = numAry[idxl] ^ numAry[idxr];
    }

    static MyLinkList mergeSort(MyLinkList upList, MyLinkList downList) {
        MyLinkNode virtualHead = new MyLinkNode(Integer.MIN_VALUE);
        MyLinkNode current = virtualHead;
        MyLinkNode upNode = upList.head;
        MyLinkNode downNode = downList.head;
        for (int i = 0; (i < upList.size + downList.size) && (upNode != null || downNode != null); i++, current=current.next) {
            if (upNode == null && downNode != null) {
                current.next = new MyLinkNode(downNode.val);
                downNode = downNode.next;
                continue;
            }
            if (downNode == null && upNode != null) {
                current.next = new MyLinkNode(upNode.val);
                upNode = upNode.next;
                continue;
            }

            if (upNode.val == downNode.val) {
                current.next = new MyLinkNode(upNode.val);
                current = current.next;
                upNode = upNode.next;
                current.next = new MyLinkNode(downNode.val);
                downNode = downNode.next;
            } else if (upNode.val < downNode.val) {
                current.next = new MyLinkNode(upNode.val);
                upNode = upNode.next;
            } else {
                current.next = new MyLinkNode(downNode.val);
                downNode = downNode.next;
            }
        }

        MyLinkList ret = new MyLinkList();
        ret.head = virtualHead.next;
        ret.size = upList.size + downList.size;
        return ret;
    }

}

class MyLinkList {
    MyLinkNode head;
    int size; //?

    MyLinkList() {
        head = null;
        size = 0;
    }

    MyLinkNode getBeforeLast() {
        MyLinkNode beforeLast = null;
        MyLinkNode last = head;
        for (int i = 0; i < size && last != null && last.next != null; i++, last = last.next) {
            beforeLast = last;
        }
        return beforeLast;
    }

    MyLinkNode getLast() {
        MyLinkNode last = head;
        for (int i = 0; i < size && last != null && last.next != null; i++, last = last.next) {

        }
        return last;
    }

    void addEl(int idx, int val) {
        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx > size) {
            throw new RuntimeException("idx不能大于size");
        }

        //新建一个节点
        MyLinkNode addNode = new MyLinkNode(val);
        if (idx == 0) {
            addNode.next = head;
            head = addNode;
        } else {
            //获取弟idx-1个节点
            MyLinkNode preIdxNode = getByIdx(idx-1);
            //新建节点指向idx节点
            addNode.next = preIdxNode.next;
            //preIdx节点指向新建节点
            preIdxNode.next = addNode;
        }

        size++;
    }

    void addEl(MyLinkNode el) {
        MyLinkNode last = getLast();
        if (last == null) {
            head = el;
        } else {
            last.next = el;
        }
        size++;
    }

    boolean removeEl() {
        boolean result;
        MyLinkNode beforeLast = getBeforeLast();
        MyLinkNode last = getLast();
        if (beforeLast == null) {
            if (last == null) {
                //nothing to do
                result = false;
            } else {
                head = null;
                size--;
                result = true;
            }
        } else {
            beforeLast.next = null;
            size--;
            result = true;
        }
        return result;
    }

    MyLinkNode getByIdx(int idx) {
        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        MyLinkNode current = head;
        for (int i = 0; i < idx && current != null; i++, current = current.next) {

        }
        return current;
    }

    MyLinkNode[] batchGetByIdxList(int[] idxList) {
        if (Arrays.stream(idxList).distinct().toArray().length != idxList.length) {
            throw new RuntimeException("idx不能有重复的值");
        }
        if (Arrays.stream(idxList).anyMatch(t -> t < 0 || t >= size)) {
            throw new RuntimeException("idx不能小于0或者大于等于size");
        }
        if (idxList.length > size) {
            throw new RuntimeException("idxList数量不能大于size");
        }
        if (!MyLinkUtil.beSortSmallToBig(idxList)) {
            throw new RuntimeException("idxList必须是单调递增的");
        }
        MyLinkNode[] ret = new MyLinkNode[idxList.length];

        //1. 循环处理，调用单个getByIdx
//        for (int i = 0; i < idxList.length; i++) {
//            int idx = idxList[i];
//            MyLinkNode node = getByIdx(idx);
//            ret[i] = node;
//        }

        //2优化一次遍历
        MyLinkNode current = head;
//        for (int i = 0, j = 0; i < size && current != null; i++, current = current.next) {
        for (int i = 0, j = 0; i <= idxList[idxList.length-1] && current != null; i++, current = current.next) {
            if (MyLinkUtil.hasVal(idxList, i)) {
                ret[j++] = current;
            }
        }
        return ret;
    }

    void removeByIdx(int idx) {
//        //获取idx前一个位置
//        MyLinkNode beforeIdxNode = getByIdx(idx - 1);
//        if (beforeIdxNode == null) {
//            throw new RuntimeException("idx前置节点为null");
//        }
//        MyLinkNode afterIdxNode = getByIdx(idx + 1);
//        beforeIdxNode.next = afterIdxNode;

        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        MyLinkNode beforeIdxNode = head;
        for (int i = 0; i < idx - 1; i++, beforeIdxNode = beforeIdxNode.next) {

        }
        if (beforeIdxNode == null) {
            throw new RuntimeException("idx前置节点不存在");
        }
        if (beforeIdxNode.next == null) {
            throw new RuntimeException("idx节点不存在");
        }
        beforeIdxNode.next = beforeIdxNode.next.next;
        size--;
    }

    void removeByVal(int val) {
        //循环迭代遍历列表节点
        MyLinkNode pre = null;
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null ; i++, pre = current, current = current.next) {
            //值相等的时候，前置节点的next指向本节点的next
            if (current.val == val) {
                if (pre == null) {
                    head = current.next;
                } else {
                    pre.next = current.next;
                }
            }
        }
    }

    void reverse() {
        MyLinkNode prev = null;
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null; i++) {
            //保留当前节点的next指针的后置节点
            MyLinkNode t = current.next;
            //把当前节点的next指针指向前一个节点
            current.next = prev;
            //迭代进入下一次循环
            //前置节点设置成当前节点
            prev = current;
            //current设置成后置节点
            current = t;
        }
        head = prev;
    }

    boolean beCircle() {
        if (head == null) {
            return false;
        }
        MyLinkNode slow = head;
        MyLinkNode fast = head.next;
        for (int i = 0; i < size && slow != null && fast != null && fast.next != null; i++, slow = slow.next, fast = fast.next.next) {
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    void updateValByIdx(int idx, int newVal) {
        MyLinkNode current = getByIdx(idx);
        if (current == null) {
            throw new RuntimeException("找不到当前节点idx");
        } else {
            current.val = newVal;
        }
    }

    void traverse() {
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+",");
        }
        System.out.println();
        System.out.println("---");
    }

    void innerTraverseRecur(MyLinkNode node) {
        //相当于前序遍历，正序遍历，顺序遍历
        System.out.print(node.val+",");
        if (node.next != null) {
            //单分支递归，最后调用，尾递归，可以优化，实际不优化，其实就是我之前写的迭代方式的算法，可以从这个地方等价变换过去
            innerTraverseRecur(node.next); //核心思想可优化，性能好，改迭代简单
        }
    }

    void traverseRecur() {
        //todo 加总数控制
        innerTraverseRecur(head);

        System.out.println();
    }

    void innerTraverseRecurBack(MyLinkNode node) {
        if (node.next != null) {
            //单分支递归，最开始调用，也可以是中间，反正不是最后，非尾递归，不可以优化，实际也不优化，其实就是我之前写的反转的迭代方式的算法，可以从这个地方等价变换过去
            innerTraverseRecurBack(node.next); //核心思想不可优化，性能差，改迭代困难
        }
        //相当于后序遍历（倒序遍历），反序遍历，逆序遍历
        System.out.print(node.val+",");
    }

    void traverseRecurBack() {
        //todo 加总数控制
        innerTraverseRecurBack(head);

        System.out.println();
    }

    //前序和后序，完全是两码事儿，不是一个对称的概念，难度也不一样
    //另外还有二叉树独有的中序遍历，可以单独研究

    //遍历一定是挨个进行的，底层，重要的是挨个，不是随机，随机，hash哈希是一种另外的套路，不全，不能代表遍历，以概率论理解是个大概
    //数组靠元数据，size（偏向这个），或者不定情况的标志位
    //链表靠也元数据，size，或者不定情况的标志位（偏向这个？）
    //统一靠元数据size，做最外层，统一总的控制，上帝视角，第三者视角
    //有的上帝视角第三者视角容易理解和好，有的是第一视角第一人称容易理解和好，尤其是多线程？
    //融汇贯通，综合运用，多角度思考，抽取偏好和最佳套路最佳实践

}


