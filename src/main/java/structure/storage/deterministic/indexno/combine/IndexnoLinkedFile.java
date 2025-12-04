package structure.storage.deterministic.indexno.combine;

import structure.storage.deterministic.TListAdt;

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
    int next;

    Node(T val) {
        this.val = val;
        next = -1;
    }
}

class MyIndexnoLinkedList<T> implements TListAdt<T> {

    int size;
    Node<T>[] ary;
    int head;

    MyIndexnoLinkedList() {
        size = 0;
        ary = new Node[10000];
        head = -1;
    }


    @Override
    public int size() {
        return size;
    }

    Node<T> loadAtNoWithNode(int no) {
        //O(n)
        int curr = head;
        for (int i = 0; i < no && curr >= 0; i++, curr = ary[curr].next) {

        }
        return ary[curr];
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
        int curr = head;
        for (int i = 0; i < size && curr >=0; i++, curr = ary[curr].next) {
            if (ary[curr].val.equals(val)) {
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
    public void addAtNo(int no, T val) {
        throw new RuntimeException();
    }

    public void add(T val) {
        //O(1)
        Node<T> node = new Node<>(val);
        node.next = head;
        ary[size] = node;
        head = size;
        size++;
    }

    @Override
    public void delAtNo(int no) {
        //O(n)
        Node<T> node1 = loadAtNoWithNode(no - 1);
        Node<T> node2 = ary[node1.next];
        //O(1)
        int t = node1.next;
        node1.next = node2.next;
        ary[t] = null;
    }

    @Override
    public void updateAtNo(int no, T val) {
        //O(n)
        Node<T> node = loadAtNoWithNode(no);
        //O(1)
        node.val = val;
    }

}


