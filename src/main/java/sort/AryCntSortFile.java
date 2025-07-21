package sort;

import java.util.Arrays;

class AryCntSortApp {
    public static void main(String[] args) {
//        int[] ary = {5,7,2,5,1,6,5,8,6,2};
//        countingSort(ary);
//        System.out.println(Arrays.toString(ary));
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

    //1b 计数排序 counting sort
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

    //2 桶排序 bucket sort
    static void bucketSort(int[] ary) {

    }

    //3 基数排序 radix sort
    static void radixSort(int[] ary) {

    }

}
