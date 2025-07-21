package sort;

import java.util.Arrays;

class AryCntSortApp {
    public static void main(String[] args) {
        int[] ary = {5,7,2,5,1,6,5,8,6,2};
        countingSort(ary);
        System.out.println(Arrays.toString(ary));
    }


    //1 计数排序 counting sort
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

    //2 桶排序 bucket sort
    static void bucketSort(int[] ary) {

    }

    //3 基数排序 radix sort
    static void radixSort(int[] ary) {

    }

}
