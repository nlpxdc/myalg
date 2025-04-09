import java.util.Arrays;

class MyLinkFileApp {
    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.addEl(new MyLinkNode(4));
        myLinkList.addEl(new MyLinkNode(2));
        MyLinkNode myLinkNode = new MyLinkNode(17);
        myLinkList.addEl(myLinkNode);

        myLinkList.traverse();

        MyLinkNode[] myLinkNodes = myLinkList.batchGetByIdxList(new int[]{0, 2});

        myLinkList.traverse();

    }
}

class MyLinkNode {
    int val;
    MyLinkNode next;

    MyLinkNode(int val) {
        this.val = val;
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
        MyLinkNode[] ret = new MyLinkNode[idxList.length];

        //1. 循环处理，调用单个getByIdx
        for (int i = 0; i < idxList.length; i++) {
            int idx = idxList[i];
            MyLinkNode node = getByIdx(idx);
            ret[i] = node;
        }
        //todo 优化一次循环
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


}


