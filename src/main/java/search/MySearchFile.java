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
//        int i = idxOfIter(ary, 13);
//        int i2 = idxOfRecur(ary, 13);
        int i4 = idxOfIterInterpolation(ary, 13);
//        int i1 = idxOfIter(ary, 14);
//        int i3 = idxOfRecur(ary, 14);
//        int i5 = idxOfIterInterpolation(ary, 14);


    }

    //1 iter 迭代二分，jdk官方实现，双指针，前后指针首尾指针
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
            return -(leftIdx+1);
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
    static int idxOfIterInterpolation(int[] ary, int val) {
        int leftIdx = 0, rightIdx = ary.length-1;

        for (int i = 0; i < Integer.MAX_VALUE && leftIdx<=rightIdx; i++) {
            //midIdx 插值猜位置，不是简单的二分，要根据值来
            int midIdx = calcMidIdx(ary, leftIdx, rightIdx, val);

            //二分比较
            int midVal = ary[midIdx];
            if (val < midVal) {
                rightIdx = midIdx-1;
            } else if (midVal < val) {
                leftIdx = midIdx+1;
            } else {
                return midIdx;
            }
        }

        return -(leftIdx+1);
    }

    //测试起始是需要自己构造合适的测试数据，并来验证他优化方面的性能的
    //但是测试数据造起来耗时，属于测试阶段，暂时先不管了，后续专门抽时间弄，先保证功能ok
    //插值计算，根据value，查找对应所在合适的idx位置，更准的猜准接近的位置
    static int calcMidIdx(int[] ary, int lowIdx, int highIdx, int val) {
        int midIdx = lowIdx;
        int lowVal = ary[lowIdx];
        int highVal = ary[highIdx];
        int valLowDelta = val - lowVal;
        int idxWholeDelta = highIdx - lowIdx;
        int valWholeDelta = highVal - lowVal;
        if (valWholeDelta != 0) {
            //线性等比例计算
            int idxLowDelta = idxWholeDelta * valLowDelta / valWholeDelta;
            midIdx = lowIdx + idxLowDelta;
        }
        //防止越界
        midIdx = Math.max(lowIdx, Math.min(midIdx, highIdx));
        return midIdx;
    }

    //4 todo 利用bst (bbst) 二叉搜索树（平衡二叉搜索树）
    //笛卡尔数 笛卡尔树是静态场景的 RMQ 神器
    //树堆 treap Treap 把随机优先级当成“隐形的平衡器”，让它在动态竞赛/工程场景里更简洁、更鲁棒
    //min-max heap？双端优先队列
    //FHQTreap Splay Tree 替罪羊树
    //所有 BST BBST 平衡二叉搜索树 都在用不同的‘平衡+存储’策略，把最坏 O(n) 压回 O(log n)，并适配内存、磁盘、并发或多维数据
    //二分查找应该用的树是avl Catalan数
    //红黑树看下b站视频

    //堆序 kth类二分查找

    //根据有序数组，构造avl
    //根据avl，做查找 外存b数b+数的根基？
    //根据有序数组，构造treap
    //根据treap，做查找 外存b数b+数的根基？

    //todo 根据有序数组，构造BBST，普通递归二分法（用中间节点作为根）
    //todo 根据BBST，做查找（二分思想）
    //todo 根据BBST，做查找，并返回jdk类似的插入位置

}
