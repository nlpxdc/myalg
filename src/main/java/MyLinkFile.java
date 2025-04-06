class MyLinkFileApp {
    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.addEl(new MyLinkNode(4));
        myLinkList.addEl(new MyLinkNode(2));
        MyLinkNode myLinkNode = new MyLinkNode(17);
        myLinkList.addEl(myLinkNode);
        myLinkNode.next = myLinkList.head;

        boolean beCircle = myLinkList.beCircle();
        System.out.println(beCircle);

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
        for (int i = 0; i < idx; i++, current = current.next) {

        }
        return current;
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

    void traverse() {
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+",");
        }
        System.out.println();
        System.out.println("---");
    }


}


