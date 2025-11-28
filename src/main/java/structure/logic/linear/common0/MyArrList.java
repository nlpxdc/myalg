package structure.logic.linear.common0;

class MyArrList {
    MyArrNode[] nodeAry;
    int size;

    //初始化
    public MyArrList() {
        nodeAry = new MyArrNode[10000];
        size = 0;
    }

    public void addEl(MyArrNode el) {
        nodeAry[size] = el;
        size++;
    }

    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
    }

}
