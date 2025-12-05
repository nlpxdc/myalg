package structure.storage.ref.discrete;

import structure.storage.common.TListAdt;

//动态 指针 引用 传统动态 够大 碎片化
class RefLinkedApp {
    public static void main(String[] args) {
        System.out.println("aa");

        Node<Integer> myNode = new Node<>(1);
        Node<Integer> myNode1 = new Node<>(2);
        Node<Integer> myNode2 = new Node<>(3);
        myNode.next = myNode1;
        myNode1.next = myNode2;
        myNode2.next = null;

        //头插O(1)，尾插 单链O(n) 双链O(1)

    }
}

class Node<T> {
    T val;
    Node<T> next;
//    Node<T> prev;

    public Node(T val) {
        this.val = val;
    }

}

class MyLinkedList implements TListAdt<Integer> {

    int size;
    Node<Integer> head;
    //todo 可对称反向，设计成双链
//    private Node<Integer> tail;

    MyLinkedList() {
        size = 0;
        head = null;
//        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    Node<Integer> loadAtNoWithNode(int no) {
        //O(n) 定位址的O(n)
        Node<Integer> curr = head;
        for (int i = 0; i < no && curr != null; i++, curr = curr.next) {

        }
        return curr;
    }


    @Override
    public Integer loadAtNo(int no) {
        //O(n) 定位址的O(n)
        Node<Integer> node = loadAtNoWithNode(no);
        return node.val;
    }

    @Override
    public int[] search(Integer val) {
        throw new RuntimeException();
//        return null;
    }

    @Override
    public int searchFirst(Integer val) {
        //O(n) 定位值的O(n)
        Node<Integer> curr = head;
        for (int i = 0; i < size && curr != null; i++, curr=curr.next) {
            if (curr.val.equals(val)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchLast(Integer val) {
//        return 0;
        throw new RuntimeException();
    }

//    //头插，即no固定为0，即把n控制为1常数，复杂度为O(1)
//    //todo 注意边界条件
//    //对称双链时，可尾插
//    @Override
//    public void addAtNo(int no, Integer val) {
//        size++;
//        //O(n) 定位址的O(n)
//        Node<Integer> node1 = loadAtNoWithNode(no-1);
//        Node<Integer> node2 = node1.next;
//        //O(1) 修改的O(1)
//        Node<Integer> addNode = new Node<>(val);
//        node1.next = addNode;
//        addNode .next = node2;
//    }

    @Override
    public void add(Integer val) {
        //O(1) 头插法
        size++;
        Node<Integer> node = new Node<>(val);
        node.next = head;
        head = node;
    }

    //同add
    @Override
    public void delAtNo(int no) {
        size--;
        //O(n) 定位址的O(n)
        Node<Integer> node1 = loadAtNoWithNode(no - 1);
        Node<Integer> node2 = node1.next;
        //O(1) 修改的O(1)
        node1.next = node2.next;
        node2 = null;
    }

    @Override
    public void updateAtNo(int no, Integer val) {
        //O(n) 定位址的O(n)
        Node<Integer> node = loadAtNoWithNode(no);
        //O(1) 修改的O(1)
        node.val = val;
    }
}