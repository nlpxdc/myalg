package sort;

import java.util.Arrays;

class AryCntSortApp {
    public static void main(String[] args) {
//        int[] ary = {5,7,2,5,1,6,5,8,6,2};
//        countingSort(ary);
//        System.out.println(Arrays.toString(ary));
//        int digits = digits(2);

//        int[] ary2 = {95,197,2692,595,7391,96,3895,98,996,92};
//        int max = max(ary2);
//        countingSortV2(ary2);
        // radixSortLsd(ary2);
        // System.out.println(Arrays.toString(ary2));
//        String[] strAry = {"banana", "apple", "orange", "grape", "pear", "peach", "kiwi", "melon", "plum", "cherry"};
//        radixSortMsd(strAry);
//        System.out.println(Arrays.toString(strAry));
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
    //空间复杂度O(C+N) 时间复杂度？
    static void radixSortLsd(int[] ary) {
        //1. 找到数组中的最大值，以确定最大位数
        int max = max(ary);
        int maxDigits = digits(max);

        //3. 创建一个临时数组，用于存储排序后的结果
        //辅助数组
        int[] tmpAry = new int[ary.length];

        for (int i = 1, exp = 1; i <= maxDigits; i++, exp *= 10) {
            //2. 创建10个桶用来计数
            //计数数组
            int[] buckets = new int[10];
            //3. 计数
            for (int j = 0; j < ary.length; j++) {
                int val = ary[j];
                //余数
                int remainder = (val / exp) % 10;
                buckets[remainder]++;
            }

            //4. 每一位数的右端位置（）
            for (int j = 1; j <= 9; j++) {
                buckets[j] = buckets[j-1] + buckets[j];
            }

            //根据位置确定元素
            for (int j = ary.length-1; j >= 0; j--) {
                //找到哪个桶的
                int val = ary[j];
                int remainder = (val/exp)%10;
                //确定这个桶的下标位置
                int idx = --buckets[remainder];
                tmpAry[idx] = val;
            }

            System.arraycopy(tmpAry, 0, ary, 0, ary.length);
        }
    }

    //3b 基数排序 radix sort MSD 字符串排序，不常用？场景：字符串很长、前缀重复极多、长度又不一致
    //文件路径或类全限定名
    //搜索引擎倒排词
    //DNA 序列比对
//    static void radixSortMsd(String[] strAry) {
//
//    }

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

    static int max(int[] ary) {
        int max = ary[0];
        for (int i = 1; i < ary.length; i++) {
            if (ary[i] > max) {
                max = ary[i];
            }
        }
        return max;
    }

    //如何切分桶、如何组织数据、是否稳定、是否原地，有多种写法
    //2种，
    // 辅助数组+计数数组（递归计数版）；
    // 3way radix quicksort

    //自己的版本 非ai版本辅助
    //基数排序 MSD 版本，适用于字符串数组，尤其是前缀类似的情况，以及不定长，可变长的字符串
    //整数数组，可以简单认为是十进制数字的字符串表示 前面补全0，等长？
    //只是字符取值范围只是纯数字，本身可以ascii码进行存储，这样可以方便比较lsd和msd
    //todo 空间复杂度，时间复杂度
    //空间复杂度O(C+N) 时间复杂度？
    //如果是数字，十进制是个字符，桶
    //如果是字符串，进制是256？扩展asc码128？asc码桶，即C的内容
    static void radixSortMsd(int[] ary) {

    }

}
