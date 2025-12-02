package structure.storage.deterministic.indexno.contiguous;

import structure.storage.deterministic.TListAdt;

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
        //O(1)
        return ary[no];
    }

    @Override
    public int[] search(Integer val) {
        return null;
    }

    @Override
    public int searchFirst(Integer val) {
        //O(n)
        for (int i = 0; i < size; i++) {
            if (val.equals(ary[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchLast(Integer val) {
        //O(n)
        for (int i = size-1; i >= 0; i--) {
            if (val.equals(ary[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addAtNo(int no, Integer val) {
        //O(n)
        for (int i = size-1; i >= no; i--) {
            ary[i+1] = ary[i];
        }
        ary[no] = val;
        size++;
    }

    @Override
    public void delAtNo(int no) {
        //O(n)
        for (int i = no; i < size; i++) {
            ary[i] = ary[i+1];
        }
        size--;
    }

    @Override
    public void updateAtNo(int no, Integer val) {
        //O(1)
        ary[no] = val;
    }
}
