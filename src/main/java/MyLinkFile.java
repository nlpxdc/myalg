class MyLinkFileApp {
    public static void main(String[] args) {
//        MyLinkList myLinkList = new MyLinkList();
//        myLinkList.addEl(new MyLinkNode(4));
//        myLinkList.addEl(new MyLinkNode(2));
//        myLinkList.addEl(new MyLinkNode(17));
//        myLinkList.traverse();
//
//        myLinkList.removeEl();
//        myLinkList.traverse();
//
//        myLinkList.addEl(new MyLinkNode(19));
//        myLinkList.traverse();

        MyLinkList myLinkList = new MyLinkList();
        myLinkList.addEl(new MyLinkNode(5));

        myLinkList.traverse();

        myLinkList.removeEl();
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

    void traverse() {
        MyLinkNode current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            System.out.print(current.val+",");
        }
        System.out.println();
    }


}


