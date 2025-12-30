package structure.storage.indexno.contiguous.simlink;

import structure.storage.common.TListAdt;

//一块区域，分开摆放，记录个数 meta元信息， 中间隔开可null
//下标索引 充当指针引用 受限动态 很小（节省 压缩）
//树 图，都能表示
//O(logn) 参考hash的O(n)
//讲区间 讲区段
class IndexnoLinkedAppV3 {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class MyIndexnoLinkedListV3 {

    int size;
    //局部性好，空间时间，预取，不能手动开辟ary情况，省去空间常数
//    boolean[] field0Ary;
//    byte[] field1Ary;
//    short[] field2Ary;
//    long[] field3Ary;
    char[] field4Ary;
    float[] field5Ary;
    double[] field6Ary;
    int[] nextIdxAry;
    int headIdx;

    MyIndexnoLinkedListV3() {
        size = 0;
        int max = 10000;
        field4Ary = new char[max];
        nextIdxAry = new int[max];
        headIdx = -1;
    }

    public int size() {
        return size;
    }

    private int loadIdxAtNo(int no) {
        //O(n)
        int currIdx = headIdx;
        for (int i = 0; i < no && currIdx >=0; i++, currIdx = nextIdxAry[currIdx]) {

        }
        return currIdx;
    }

    public char loadAtNo(int no) {
        //O(n)
        int noIdx = loadIdxAtNo(no);
        return field4Ary[noIdx];
    }

    public int[] search(char val) {
        return new int[0];
    }

    public int searchFirst(char val) {
        //O(n)
        int currIdx = headIdx;
        for (int i = 0; i < size && currIdx >=0; i++, currIdx = nextIdxAry[currIdx]) {
            if (field4Ary[currIdx] == val) {
                return i;
            }
        }
        return -1;
    }

    public int searchLast(char val) {
        throw new RuntimeException();
//        return 0;
    }

    public void add(char val) {
        //O(1) 存储尾插 逻辑头插
        field4Ary[size] = val;
        nextIdxAry[size] = headIdx;
        headIdx = size;
        size++;
    }

    public void delAtNo(int no) {
//        ary[no] = null;
        throw new RuntimeException();
    }

    public void updateAtNo(int no, char val) {
        //O(n)
        int noIdx = loadIdxAtNo(no);
        //O(1)
        field4Ary[noIdx] = val;
    }

}


