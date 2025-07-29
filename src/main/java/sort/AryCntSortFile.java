package sort;

import java.util.Arrays;

class AryCntSortApp {
    public static void main(String[] args) {
//        int[] ary = {5,7,2,5,1,6,5,8,6,2};
//        countingSort(ary);
//        System.out.println(Arrays.toString(ary));
        int digits = digits(2);

        int[] ary2 = {95,97,92,95,91,96,95,98,96,92};
        countingSortV2(ary2);
        System.out.println(Arrays.toString(ary2));
    }


    //1a 计数排序 counting sort
    static void countingSort(int[] ary) {
        int[] cntAry = new int[10];

        for (int idx = 0; idx < ary.length; idx++) {
            int val = ary[idx];
            cntAry[val]++;
        }

        int globalIdx = 0;
        for (int val = 0; val < 9; val++) {
            int valCnt = cntAry[val];
            for (int i = 0; i < valCnt; i++) {
                ary[globalIdx++] = val;
            }
        }
    }

    //1b 计数排序 counting sort 含偏移量
    static void countingSortV2(int[] ary) {
        int offset = 90;

        int[] cntAry = new int[10];

        for (int idx = 0; idx < ary.length; idx++) {
            int val = ary[idx];
            cntAry[val-offset]++;
        }

        int globalIdx = 0;
        for (int val = 0; val < 9; val++) {
            int valCnt = cntAry[val];
            for (int i = 0; i < valCnt; i++) {
                ary[globalIdx++] = val+offset;
            }
        }
    }

    //3a 基数排序 radix sort LSD 整数排序
    static void radixSortLsd(int[] ary) {

    }

    //3b 基数排序 radix sort MSD 字符串排序，不常用？场景：字符串很长、前缀重复极多、长度又不一致
    //文件路径或类全限定名
    //搜索引擎倒排词
    //DNA 序列比对
    static void radixSortMsd(char[][] strAry) {

    }

    // 假设输入是自然数
    static int digits(int n) {
        if (n == 0) {
            return 1;
        }
        int cnt = 0;
        for (int i = 0, d = n; i < Integer.MAX_VALUE && d>0; i++, d /= 10) {
            cnt++;
        }
        return cnt;
    }

}
