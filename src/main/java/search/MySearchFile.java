package search;

import java.util.Arrays;
import java.util.Collections;

//查找第一个或者最后一个，查找多个的话就是字符串匹配，正则表达式，状态机了，有一整套完善的
//普通场景，主键，值都不同，索引失效不！
class MySearchApp {
    public static void main(String[] args) {
//        System.out.println("aa");
//        String str = "abc";
//        char[] charArray = str.toCharArray();
//        int i = str.indexOf('b');
//        int i1 = Arrays.binarySearch(charArray, 'b');
//        int i2 = Collections.binarySearch(null, null);
        int[] ary = {0,1,2,3,5,8,13,21,34,55};
        int i = idxOfIter(ary, 13);
        int i2 = idxOfRecur(ary, 13);
        int i1 = idxOfIter(ary, 14);
        int i3 = idxOfRecur(ary, 14);


    }

    //1 iter
    static int idxOfIter(int[] ary, int val) {
        int leftIdx = 0, rightIdx = ary.length-1;

        for (int i = 0; i < Integer.MAX_VALUE && leftIdx<=rightIdx; i++) {
//            int mid = leftIdx + ((rightIdx - leftIdx) >>> 1); // 防溢出写法
            int midIdx = (leftIdx+rightIdx)/2;
            int midVal = ary[midIdx];
            if (val < midVal) {
                rightIdx = midIdx-1;
            } else if (midVal < val) {
                leftIdx = midIdx+1;
            } else {
                return midIdx;
            }
        }

//        return -1;
        //用负数代表没找到，但是指明需要插入的位置
        return -(leftIdx+1);
    }

    //2 recur
    static int idxOfRecur(int[] ary, int val) {
        int idx = innerIdxOfRecur(ary, 0, ary.length, val);
        return idx;
    }

    static int innerIdxOfRecur(int[] ary, int leftIdx, int rightIdx, int val) {
        if (leftIdx > rightIdx) {
            return -1;
        }
        int midIdx = (leftIdx+rightIdx)/2;
        int midVal = ary[midIdx];
        if (val < midVal) {
            return innerIdxOfRecur(ary, leftIdx, midIdx-1, val);
        } else if (val > midVal) {
            return innerIdxOfRecur(ary, midIdx+1, rightIdx, val);
        } else {
            return midIdx;
        }
//        return -1;
    }

    //斐波那契搜做，斐波那契区间分割，升级二分法
    //避免了除法或者乘法，即mid的计算，如果用位移，那就不考虑了
    //为什么斐波那契？计算最简单 + 比例最好

    //3 插值思想



}
