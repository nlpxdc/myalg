package mylinkpkg2;

import java.util.Arrays;

class MyLinkApp {
    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        MyLinkNode node0 = new MyLinkNode(4);
        myLinkList.addEl(node0);
//        node0.next = node0;
        MyLinkNode node1 = new MyLinkNode(2);
        myLinkList.addEl(node1);
        MyLinkNode node2 = new MyLinkNode(17);
        node2.next = node1;
        myLinkList.addEl(node2);

        myLinkList.traverse();

        MyLinkList retList = myLinkList.hasCircleNotHeadRetList();
        retList.traverse();
    }
}

class MyLinkNode {
    int val;
    MyLinkNode next;

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
    int size;

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
        MyLinkNode pre = null;
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null; i++) {
            //保留当前节点的next指针的后置节点
            MyLinkNode t = current.next;
            //把当前节点的next指针指向前一个节点
            current.next = pre;
            //迭代进入下一次循环
            //前置节点设置成当前节点
            pre = current;
            //current设置成后置节点
            current = t;
        }
        head = pre;
    }

    boolean hasCircle() {
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

//    hasCircleIsHead
    boolean beCircle() {
        if (head == null) {
            return false;
        }
        MyLinkNode slow = head;
        MyLinkNode fast = head.next;
        for (int i = 0; i < size && slow != null && fast != null && fast.next != null; i++, slow=slow.next, fast=fast.next.next) {
            if (fast == slow && slow.next == head) {
                return true;
            }
        }
        return false;
    }

    boolean hasCircleNotHead() {
        if (head == null) {
            return false;
        }
        MyLinkNode slow = head;
        MyLinkNode fast = head.next;
        for (int i = 0; i < size; i++, slow = slow.next, fast = fast.next.next) {
            if (fast == slow && slow.next != head) {
                return true;
            }
        }
        return false;
    }

    MyLinkList hasCircleNotHeadRetList() {
        if (head == null) {
            return null;
        }
        MyLinkNode slow = head;
        MyLinkNode fast = head.next;
        for (int i = 0; i < size; i++, slow = slow.next, fast = fast.next.next) {
            if (fast == slow && slow.next != head) {
                MyLinkList ret = new MyLinkList();
                ret.head = slow;
                MyLinkNode current = slow;
                for (int j = 0, currentIdx = 0 ; j < size; j++, current = current.next, currentIdx++) {
                    if (current.next == slow) {
                        ret.size = currentIdx + 1;
                        break;
                    }
                }
                return  ret;
            }
        }
        return null;
    }

    //todo 计算循环链表的入还第一个节点

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


}


