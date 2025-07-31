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
        int[] tmpAry = new int[ary.length];

        for (int i = 1, exp = 1; i <= maxDigits; i++, exp *= 10) {
            //2. 创建10个桶用来计数
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

    //自己的版本
    //基数排序 MSD 版本，适用于字符串数组，尤其是前缀类似的情况，以及不定长，可变长的字符串
    //整数数组，可以简单认为是十进制数字的字符串表示
    //只是字符取值范围只是纯数字，本身可以ascii码进行存储，这样可以方便比较lsd和msd
    //todo 空间复杂度，时间复杂度
    //空间复杂度O(C+N) 时间复杂度？
    //如果是数字，十进制是个字符，桶
    //如果是字符串，进制是256？128？桶，即C的内容
    static void radixSortMsd(int[] ary) {

    }

//    // 基数排序 MSD 版本，适用于字符串数组
//    static void radixSortMsd(String[] strAry) {
//        if (strAry == null || strAry.length <= 1) return;
//        radixSortMsd(strAry, 0, strAry.length, 0);
//    }

//    // 辅助递归方法
//    private static void radixSortMsd(String[] ary, int left, int right, int d) {
//        if (right - left <= 1) return;
//
//        // ASCII 字符集，扩展一位表示字符串结尾
//        int R = 256 + 1; // 0~255 字符 + 1个结尾符
//        int[] count = new int[R + 1]; // 计数数组
//
//        // 统计频率
//        for (int i = left; i < right; i++) {
//            int c = charAt(ary[i], d) + 1; // 结尾为0，其余为字符+1
//            count[c + 1]++;
//        }
//
//        // 转换为前缀和
//        for (int r = 0; r < R; r++) {
//            count[r + 1] += count[r];
//        }
//
//        // 分配
//        String[] aux = new String[right - left];
//        for (int i = left; i < right; i++) {
//            int c = charAt(ary[i], d) + 1;
//            aux[count[c]++] = ary[i];
//        }
//
//        // 复制回原数组
//        for (int i = 0; i < aux.length; i++) {
//            ary[left + i] = aux[i];
//        }
//
//        // 递归对子区间排序
//        for (int r = 0; r < R; r++) {
//            int lo = left + count[r];
//            int hi = left + count[r + 1];
//            if (hi > lo) {
//                radixSortMsd(ary, lo, hi, d + 1);
//            }
//        }
//    }

//    // 返回字符串第d位字符的ASCII码，若越界返回-1
//    private static int charAt(String s, int d) {
//        if (d < s.length()) {
//            return s.charAt(d);
//        } else {
//            return -1; // 结尾符
//        }
//    }

}
