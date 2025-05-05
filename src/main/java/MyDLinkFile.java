class MyDLinkApp {
    public static void main(String[] args) {
//        MyDLinkNode node0 = new MyDLinkNode(1);
//        MyDLinkNode node1 = new MyDLinkNode(2);
//        MyDLinkNode node2 = new MyDLinkNode(5);
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
//        MyDLinkNode origLastNext = last.next;
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

    void reverseByNodeMid() {
        MyDLinkNode left = head;
        MyDLinkNode right = last;
        for (int i = 0; i < size/2+1 && left != null && right != null; i++) {
            if (left == right) {
//                MyDLinkNode origLeftNext = left.next;
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
//        MyDLinkNode origLast = last;
//        last = head;
//        head = origLast;
        MyDLinkNode origHead = head;
        head = last;
        last = origHead;
    }

//    void traverseNextAllTheTime() {
//        MyDLinkNode current = head;
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
//        MyDLinkNode current = last;
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

}
