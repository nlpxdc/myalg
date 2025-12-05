package structure.storage.indexno.deterministic.contiguous.simlink;

import structure.storage.common.TListAdt;

//一块区域，分开摆放，记录个数 meta元信息， 中间隔开可null
//下标索引 充当指针引用 受限动态 很小（节省 压缩）
//树 图，都能表示
//O(logn) 参考hash的O(n)
//讲区间 讲区段
class IndexnoLinkedApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class Node<T> {
    T val;
    int nextIdx;

    Node(T val) {
        this.val = val;
        nextIdx = -1;
    }
}

class MyIndexnoLinkedList<T> implements TListAdt<T> {

    int size;
    //局部性好，空间时间，预取，不能手动开辟ary情况，省去空间常数
    Node<T>[] ary;
    int headIdx;

    MyIndexnoLinkedList() {
        size = 0;
        ary = new Node[10000];
        headIdx = -1;
    }

    @Override
    public int size() {
        return size;
    }

    Node<T> loadAtNoWithNode(int no) {
        //O(n)
        int currIdx = headIdx;
        for (int i = 0; i < no && currIdx >=0; i++, currIdx = ary[currIdx].nextIdx) {

        }
        return ary[currIdx];
    }

    @Override
    public T loadAtNo(int no) {
        //O(n)
        Node<T> node = loadAtNoWithNode(no);
        return node.val;
    }

    @Override
    public int[] search(T val) {
        return new int[0];
    }

    @Override
    public int searchFirst(T val) {
        //O(n)
        int currIdx = headIdx;
        for (int i = 0; i < size && currIdx >=0; i++, currIdx = ary[currIdx].nextIdx) {
            if (ary[currIdx].val.equals(val)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchLast(T val) {
        throw new RuntimeException();
//        return 0;
    }

    @Override
    public void add(T val) {
        //O(1) 存储尾插 逻辑头插
        Node<T> node = new Node<>(val);
        node.nextIdx = headIdx;
        ary[size] = node;
        headIdx = size;
        size++;
    }

    @Override
    public void delAtNo(int no) {
//        ary[no] = null;
        throw new RuntimeException();
    }

    @Override
    public void updateAtNo(int no, T val) {
        //O(n)
        Node<T> node = loadAtNoWithNode(no);
        //O(1)
        node.val = val;
    }

}


