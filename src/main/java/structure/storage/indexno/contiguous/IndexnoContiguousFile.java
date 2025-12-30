package structure.storage.indexno.contiguous;

import structure.storage.common.TListAdt;

//一块区域，连续摆放 从头开始，记录个数 meta元信息， 后面可null
//和hash冲突，不合作 和动态simref合作组合
class IndexnoContiguousApp {
    public static void main(String[] args) {
        System.out.println("aa");

        //new delete malloc free

        boolean[] booleans = new boolean[100000];
        byte[] bytes = new byte[100000];
        short[] shorts = new short[100000];
        int[] ints = new int[100000];
        long[] longs = new long[100000];
        char[] chars = new char[100000];

        Object[] objects = new Object[100000];
        Boolean[] booleans1 = new Boolean[100000];
        Byte[] bytes1 = new Byte[100000];
        Short[] shorts1 = new Short[100000];
        Integer[] integers = new Integer[100000];
        Long[] longs1 = new Long[100000];
        Character[] characters = new Character[100000];

        String[] strings = new String[100000];

        Void[] voids = new Void[100000];

        MyObj[] myObjs = new MyObj[100000];

        //头插O(n)，尾插O(1)

    }
}

class MyObj {
    int age;
    Long[] longs;
    String[][] strAryAry;
}

class MyAryList implements TListAdt<Integer> {

    private final Integer[] ary;
    private int size;
    //天然头尾指针定位，因为连续规定，所以写入要维护这个规则，要移动，每个都要移动，就O(n)了

    public MyAryList() {
        ary = new Integer[10000];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer loadAtNo(int no) {
        //O(1) 定位址O(1)，取值
        return ary[no];
    }

    @Override
    public int[] search(Integer val) {
        return null;
    }

    @Override
    public int searchFirst(Integer val) {
        //O(n) 定位值的O(n)
        for (int i = 0; i < size; i++) {
            if (val.equals(ary[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchLast(Integer val) {
        //O(n) 定位值的O(n)
        for (int i = size-1; i >= 0; i--) {
            if (val.equals(ary[i])) {
                return i;
            }
        }
        return -1;
    }

//    @Override
//    public void addAtNo(int no, Integer val) {
//        //O(n) 移动址修改的O(n)
//        for (int i = size-1; i >= no; i--) {
//            ary[i+1] = ary[i];
//        }
//        //O(1) 定位址O(1)，同时修改
//        ary[no] = val;
//        size++;
//    }

    @Override
    public void add(Integer val) {
        //O(1) 尾插法
        ary[size] = val;
        size++;
    }

    @Override
    public void delAtNo(int no) {
        //O(n) 移动址修改的O(n)
        for (int i = no; i < size; i++) {
            ary[i] = ary[i+1];
        }
        //O(1)
        size--;
    }

    @Override
    public void updateAtNo(int no, Integer val) {
        //O(1) 定位址O(1)，同时修改
        ary[no] = val;
    }
}
