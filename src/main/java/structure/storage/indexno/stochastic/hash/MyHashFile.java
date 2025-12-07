package structure.storage.indexno.stochastic.hash;

import com.google.common.hash.Hashing;
import structure.storage.common.TListAdt;

import java.util.zip.CRC32;

//一块区域，散列摆放，记录个数 meta元信息， 中间隔开可null
//数学知识 散列技巧 概率论（随机过程）数论？ 数学证明  >数理统计 （实际验证）
//O(1) 参考SimRef的O(logn)
//和contiguous冲突，不合作 和动态simref合作组合
//一一映射，不讲区间
//在连续中，玩离散，解决冲突，散列冲突
//假设前提散列不冲突 完美hash算法 （用一个common的来模拟即可）
//测哈希：SMhasher 最全面，Hashcat 看纯速度。
//        测随机：TestU01 / PractRand / dieharder 三件套，统计学报告直接出。
class MyHashApp {
    public static void main(String[] args) {
        System.out.println("aa");

//        Object[] objects = new Object[100000];
//
//        Object o0 = new Object();
//        int i0 = o0.hashCode() % 100000;
//
//        Object o1 = new Object();
//        int i1 = o1.hashCode() % 100000;
//
//        Object o2 = new Object();
//        int i2 = o2.hashCode() % 100000;
//
//        Object o3 = new Object();
//        int i3 = o3.hashCode() % 100000;
//
////        myHashCode()
//        objects[i0] = o0;
//        objects[i1] = o1;
//        objects[i2] = o2;
//        objects[i3] = o3;

        String[] stringAry = new String[100000];

        String abc = new String("abc");
        String def = new String("def");
        String ghi = new String("ghi");

        stringAry[myHashCode("abc")%100000] = abc;
        stringAry[myHashCode("def")%100000] = def;
        stringAry[myHashCode("ghi")%100000] = ghi;

    }

    static int myHashCode(String str) {
        int h = 0;
        char[] charArray = str.toCharArray();
        if (charArray.length > 0) {
            for (int i = 0; i < charArray.length; i++) {
                h = 31 * h + charArray[i];
            }
        }
        return h;
    }

//    static int myHashCode(int[] val) {
//        int h = 0;
//        for (int i = 0; i < val.length; i++) {
//            h = 31 * h + val[i];
//        }
//        return h;
//    }

}

//class MyNode {
//
//}

class MyHashList implements TListAdt<Integer> {

    int size;
    final int[] ary;
    static final int max = 100000;

    public MyHashList() {
        size = 0;
        ary = new int[max];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer loadAtNo(int no) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        return ary[noIdx];
    }

    @Override
    public int[] search(Integer val) {
        return new int[0];
    }

    @Override
    public int searchFirst(Integer val) {
        //O(n)
        for (int i = 0; i < size; i++) {
            int noIdx = MyHashUtil.noIdx(i, max);
            if (ary[noIdx] == val) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int searchLast(Integer val) {
        //O(n)
        for (int i = size-1; i >= 0; i--) {
            int noIdx = MyHashUtil.noIdx(i, max);
            if (ary[noIdx] == val) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(Integer val) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(size, max);
        ary[noIdx] = val;
        size++;
    }

    @Override
    public void delAtNo(int no) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        ary[noIdx] = 0;
        size--;
    }

    @Override
    public void updateAtNo(int no, Integer val) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        ary[noIdx] = val;
    }
}


