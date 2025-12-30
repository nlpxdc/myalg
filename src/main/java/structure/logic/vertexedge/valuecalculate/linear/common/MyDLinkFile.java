package structure.logic.vertexedge.valuecalculate.linear.common;

class MyDLinkApp {
    public static void main(String[] args) {
//        structure.logic.list.sequence.MyDLinkNode node0 = new structure.logic.list.sequence.MyDLinkNode(1);
//        structure.logic.list.sequence.MyDLinkNode node1 = new structure.logic.list.sequence.MyDLinkNode(2);
//        structure.logic.list.sequence.MyDLinkNode node2 = new structure.logic.list.sequence.MyDLinkNode(5);
//        node0.next = node1;
//        node1.next =  node2;
//        node2.next = node0;
//        node2.prev = node1;
//        node1.prev = node0;
//        node0.prev = node2;

        MyDLinkNode node0 = new MyDLinkNode(1);
        MyDLinkNode node1 = new MyDLinkNode(2);
        MyDLinkNode node2 = new MyDLinkNode(5);
        node0.next = node1;
        node1.prev = node0;

        node1.next = node2;
        node2.prev = node1;

//        node2.next = node0;
//        node0.prev = node2;

        MyDLinkList myDLinkList = new MyDLinkList();
        myDLinkList.head = node0;
        myDLinkList.last = node2;
        myDLinkList.size = 3;

        myDLinkList.addHeadEl(new MyDLinkNode(0));
        myDLinkList.addLastEl(new MyDLinkNode(8));

        myDLinkList.traverseNext();
//        myDLinkList.traversePrev();
//        myDLinkList.traverseNextAllTheTime();
//        myDLinkList.traversePrevAllTheTime();

//        myDLinkList.reverseByValue();
//        myDLinkList.reverseByNodeNext();
//        myDLinkList.reverseByNodePrev();
//        myDLinkList.reverseByNodeMid();
//        myDLinkList.traverseNext();
//        myDLinkList.traversePrev();

        MyDLinkNode byIdx = myDLinkList.getByIdx(1);
        MyDLinkNode byIdx1 = myDLinkList.getByIdx(3);


    }
}

class MyDLinkNode {
    int val;
    MyDLinkNode prev;
    MyDLinkNode next;
    //idx? no can

    MyDLinkNode(int val) {
        this.val = val;
    }
}

class MyDLinkList {
    MyDLinkNode head;
    MyDLinkNode last;
    int size; //?

    MyDLinkList() {
        head = null;
        last = null;
        size = 0;
    }

    void addEl(int idx, int val) {

    }

    //每种情况都最多跑一半
    MyDLinkNode getByIdx(int idx) {
        //二分法
        //cnt是cnt
        int halfCnt = size / 2;
        //idx是idx
        int midIdx = halfCnt - 1;
        if (idx <= midIdx) {
            MyDLinkNode left = head;
            for (int i = 0; i < size && i < idx && left != null; i++, left=left.next) {

            }
            return left;
        } else {
            MyDLinkNode right = last;
            for (int i = size-1; i >= 0 && i > idx && right != null; i--, right=right.prev) {

            }
            return right;
        }
    }

    void addHeadEl(MyDLinkNode node) {
        if (head == null) {
            head = node;
            last = node;
            size=1;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
        size++;
    }

    void addLastEl(MyDLinkNode node) {
        if (last == null) {
            head = node;
            last = node;
            size=1;
            return;
        }
//        structure.logic.list.sequence.MyDLinkNode origLastNext = last.next;
        last.next = node;
        node.prev = last;
//        node.next = origLastNext;
//        head.prev = head.prev == null ? null : node;
        last = node;
        size++;
    }

    void traverseNext() {
        MyDLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void traversePrev() {
        MyDLinkNode current = last;
        for (int i = 0; i < size && current != null; i++, current=current.prev) {
            System.out.print(current.val+"->");
        }
        System.out.println();
    }

    void reverseByValue() {
        MyDLinkNode left = head;
        MyDLinkNode right = last;
        for (int i = 0; i < size/2 && left != null && right != null && left != right; i++, left = left.next, right = right.prev) {
            int t = left.val;
            left.val = right.val;
            right.val = t;
        }
    }

    //其实位置就是不动的，就是靠维护引用关系串联起来，拓扑学

    //维护两个引用，节点位置没动
    void reverseByNodeNext() {
        MyDLinkNode current = head;
        for (int i = 0; i < size && current != null; i++) {
            MyDLinkNode origNext = current.next;
            current.next = current.prev;
            current.prev = origNext;
            current = origNext;
        }
        MyDLinkNode origHead = head;
        head = last;
        last = origHead;
    }

    //维护两个引用，节点位置没动
    void reverseByNodePrev() {
        MyDLinkNode current = last;
        for (int i = 0; i < size && current != null; i++) {
            MyDLinkNode origPrev = current.prev;
            current.prev = current.next;
            current.next = origPrev;
            current = origPrev;
        }
        MyDLinkNode origLast = last;
        last = head;
        head = origLast;
    }

    //双引用，同时维护两套，节点位置依旧没动，逻辑上的
    void reverseByNodeMid() {
        MyDLinkNode left = head;
        MyDLinkNode right = last;
        for (int i = 0; i < size/2+1 && left != null && right != null; i++) {
            if (left == right) {
//                structure.logic.list.sequence.MyDLinkNode origLeftNext = left.next;
//                left.next = left.prev;
//                left.prev = origLeftNext;
                MyDLinkNode origRightPrev = right.prev;
                right.prev = right.next;
                right.next = origRightPrev;

                break;
            } else {
                MyDLinkNode origLeftNext = left.next;
                left.next = left.prev;
                left.prev = origLeftNext;
                left = origLeftNext;
                MyDLinkNode origRightPrev = right.prev;
                right.prev = right.next;
                right.next = origRightPrev;
                right = origRightPrev;
            }
        }
//        structure.logic.list.sequence.MyDLinkNode origLast = last;
//        last = head;
//        head = origLast;
        MyDLinkNode origHead = head;
        head = last;
        last = origHead;
    }

//    void traverseNextAllTheTime() {
//        structure.logic.list.sequence.MyDLinkNode current = head;
//        for (; current != null ; current = current.next ) {
//            System.out.print(current.val+"->");
//            try {
//                Thread.sleep(27_0);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println();
//    }

//    void traversePrevAllTheTime() {
//        structure.logic.list.sequence.MyDLinkNode current = last;
//        for (; current != null ; current = current.prev ) {
//            System.out.print(current.val+"->");
//            try {
//                Thread.sleep(27_0);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println();
//    }

    //todo 有序链表插入添加一个key 前后首尾双指针，类二分查找，实现更容易些
    boolean addValInSort(MyDLinkNode node) {
        //先要搜索到待插入的位置，前置节点父节点
        //如果已经有了，返回false，代表插入失败
        //如果没有找到，找到待插入的前置父节点，插入，并返回true
        return false;
    }

}
