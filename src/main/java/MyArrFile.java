class MyArrFile {
    public static void main(String[] args) {
        MyArrList myArrList = new MyArrList();
        myArrList.addEl(new MyArrNode(5));
        myArrList.addEl(new MyArrNode(3));
        myArrList.addEl(new MyArrNode(18));

        myArrList.traverse();

        myArrList.removeByIdx(1);
        myArrList.traverse();
    }
}

class MyArrNode {
    int val;

    MyArrNode(int val) {
        this.val = val;
    }
}

class MyArrList {
    MyArrNode[] nodeAry;
    int size;

    MyArrList() {
        nodeAry = new MyArrNode[10000];
        size = 0;
    }

    void addEl(MyArrNode el) {
        nodeAry[size++] = el;
    }

    void removeEl() {
        nodeAry[--size] = null;
    }

    void removeByIdx(int idx) {
        if (idx < 0) {
            throw new RuntimeException("idx不能是负数");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        nodeAry[idx] = null;
        for (int i = idx; i < size - 1 ; i++) {
            nodeAry[i] = nodeAry[i+1];
        }
        size--;
    }

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
        System.out.println("---");
    }
}
