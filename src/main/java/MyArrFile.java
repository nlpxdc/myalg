class MyArrFile {
    public static void main(String[] args) {
        MyArrList myArrList = new MyArrList();
        myArrList.addEl(new MyArrNode(5));
        myArrList.addEl(new MyArrNode(3));
        myArrList.addEl(new MyArrNode(18));

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

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
    }
}
