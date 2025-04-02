class MyArrFile {
    public static void main(String[] args) {
        MyArrList myArrList = new MyArrList();
        myArrList.addEl(new MyArrNode(5));
        myArrList.addEl(new MyArrNode(3));
        myArrList.addEl(new MyArrNode(18));

        myArrList.traverse();

        myArrList.reverse();
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

    int idxOfVal(int val) {
        int idx = -1;
        for (int i = 0; i < size; i++) {
            if (nodeAry[i].val == val) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    void removeByVal(int val) {
        //循环迭代便利找到val的idx
        int idx = idxOfVal(val);
        //删除idx
        removeByIdx(idx);
    }

    void reverse() {
        for (int i = 0, j = size-1; i < size && j >= 0 && i < j; i++, j--) {
            int t = nodeAry[i].val;
            nodeAry[i].val = nodeAry[j].val;
            nodeAry[j].val = t;
        }
    }

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
        System.out.println("---");
    }
}
