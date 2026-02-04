package operation.deterministic.op2seq.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//用的基数排序 字典序适合用msd
//假设范围为英文小写字母
//这是二维的排序，有多位，按位处理，从头开始
//不一定依赖计数排序，也可依赖交换排序快排
class AryStringSortApp {
    public static void main(String[] args) {
//        String upStr = "apple";
//        String downStr = "banana";
//        String thirdStr = "apple";
//        int compare = compare(upStr, downStr);
//        int compare1 = compare(downStr, upStr);
//        int compare2 = compare(upStr, thirdStr);

//        String[] strAry = {"apple", "banana", "peach"};
//        swap(strAry, 0,2);
//        Arrays.sort(strAry);

//        String[] words = {
//                "prefix",
//                "preset",  // 重复值
//                "preview",
//                "prepare",
//                "premium",
//                "pretend",
//                "preset",  // 同上
//                "prevent",
//                "prelude"
//        };
        String[] words = {
                "add",
                "cab",  // 重复值
                "fad",
                "fee",
                "bad",
                "bee",
                "fed",  // 同上
                "bed",
                "ace"
        };
        List<String> wordList = Arrays.asList(words);
        Collections.sort(wordList);
        System.out.println(wordList);
//        radixSortMsdAuxRecurCnt(words);
        radixSortMsd3WayQuicksort(words);
        System.out.println(Arrays.toString(words));

    }

    //单次比较，两两，并非一定应用于排序，排序用基数
    //二维 多位 相当于char[]和char[]比较，长度不一，等同于String，化作单个元素
    //字符串比较算法也有多种可以写
    //如果是多个字符串，通过比较交换，也能做，如同单个元素是int的，但是内部比较算法多了一个数量级别
    //把int的算法大于小于等于号，转成compare和0比较，算法就可以服用了
    //把swap也替换下，但是内部比较时间复杂度高，整整高一个数量级，空间复杂度也有字符串最大长度决定，临时辅助字符数组，一个字符串
    //这个配合比较交换排序可以衍生出任意复杂对象的排序
    static int compare(String upStr, String downStr) {
        int upLength = upStr.length();
        int downLength = downStr.length();
        int minLength = Math.min(upLength, downLength);
        char[] upAry = upStr.toCharArray();
        char[] downAry = downStr.toCharArray();

        for (int i = 0; i < Integer.MAX_VALUE && i < minLength; i++) {
            char upChar = upAry[i];
            char downChar = downAry[i];
            if (upChar != downChar) {
                return upChar - downChar;
            }
        }

        return upLength - downLength;
    }

    //swap 字符串数组种，字符串的交换
    //见参考java.lang.String.compareTo方法
    static void swap(String[] strAry, int upIdx, int downIdx) {
        String upStr = strAry[upIdx];
        String downStr = strAry[downIdx];
        if (compare(upStr, downStr) != 0) {
            String t = strAry[upIdx];
            strAry[upIdx] = downStr;
            strAry[downIdx] = t;
        }
    }

    //如何切分桶、如何组织数据、是否稳定、是否原地，有多种写法
    //主要有2种，一共有4种？
    //American-flag sort 环交换 不稳定 原地 理论上 得不偿失 不常用 思路可借鉴
    //链表桶排序（in-place list） 稳定 原地 理论上 得不偿失 不常用思路可借鉴

    //基数排序 radix sort MSD 字符串排序，场景：字符串、前缀重复多、长度又不一致
    //文件路径或类全限定名

    //多元素排序，非两两比较，则常用基数排序MSD，并非一定依赖两两比较compare

    //经典基础版 计数排序递归版 稳定 通用版 教学版 借鉴思路
    //辅助数组（在外n）+计数数组（在内c） 空间复杂度O(n+c)
    //先计数再递归 基数数组在内，在递归内，不多，常数，在递归内创建不用重置值
    //辅助数组在外，在递归外定义，在递归内利用，只在外一次就好，不用递归迭代每次都创建
    //有cnt，就肯定有辅助数组，额外数组
    //所以多个的时候，不用两两比较交换的迭代，用借助额外计数和额外辅助数组的方法处理，其实就是排序，当然也可以比较两个元素的数组
    //辅助数组（空间）+递归计数（时间），参考快排递归
    static void radixSortMsdAuxRecurCnt(String[] strAry) {
        if (strAry == null || strAry.length <= 1) {
            return;
        }
        //辅助数组
        String[] aux = new String[strAry.length];
        innerRadixSortMsdAuxRecurCnt(strAry, 0, strAry.length-1, 0, aux);
    }

    static int R = 256;
//    static char NULL = '\0';

    static void innerRadixSortMsdAuxRecurCnt(String[] strAry, int lowIdx, int highIdx, int n, String[] aux) {
        if (lowIdx >= highIdx) {
            return;
        }
        int shortStrCnt = 0;
        int shortStrEndIdx = 0;
        int[] rCntBuckets = new int[R];
        int[] rEndIdxBuckets = new int[R];

        //统计次数，频率
        for (int i = lowIdx; i <= highIdx; i++) {
            String s = strAry[i];
            int r = calcRadixByString(s, n);
            if (r < 0) {
                shortStrCnt++;
            } else {
                rCntBuckets[r]++;
            }
        }

        //shortStrCnt作为rCntBuckets处理成位置idx后的起始偏移量

        //转成位置区间，关键，巧妙的一步，算出来的是结束位置的索引idx，和lsd保持一致
        //一起计算短串，加上短串的偏移量
        shortStrEndIdx = shortStrCnt;
        System.arraycopy(rCntBuckets, 0, rEndIdxBuckets, 0, rCntBuckets.length);
        rEndIdxBuckets[0] = shortStrEndIdx+rEndIdxBuckets[0];
        for (int r = 1; r < R; r++) {
            rEndIdxBuckets[r] = rEndIdxBuckets[r-1] + rEndIdxBuckets[r];
        }

        //稳定分发到辅助数组，和原数组等长，和lsd一样的
        for (int i = highIdx; i >= lowIdx; i--) {
            String origStr = strAry[i];
            int r = calcRadixByString(origStr, n);
            int toPutIdx;
            if (r < 0) {
                toPutIdx = --shortStrEndIdx;
            } else {
                toPutIdx = --rEndIdxBuckets[r];
            }
            aux[toPutIdx] = origStr;
        }
        System.arraycopy(aux, 0, strAry, 0, strAry.length);

        //第一轮排序好后成组，需要在组内继续排序，需要缩小范围，按照r来缩小范围
        //此时r的endIdx已经变成了beginIdx了，然后r的结束位置，就是r+1起始位置减去1
        for (int r = 0; r < R; r++) {
            if (rCntBuckets[r] == 0) {
                continue;
            }
            if (r >= R-1) {
                //最后一组了
                innerRadixSortMsdAuxRecurCnt(strAry, rCntBuckets[r], strAry.length-1, n+1, aux);
            } else {
                innerRadixSortMsdAuxRecurCnt(strAry, rCntBuckets[r], rCntBuckets[r+1]-1, n+1, aux);
            }
        }

    }

    static int calcRadixByString(String str, int n) {
        return n < str.length() ? str.charAt(n) : -1;
    }

    //高阶工程版 3way radix quicksort 不稳定 实际常用版
    //三向切分快速排序（3-way radix quicksort）
    //双或三指针（相等）原地交换 无辅助数组 无额外空间 可不计数，靠交换
    //内部也是递归，二分递归，有计数吗？没。有辅助数组吗？没！
    static void radixSortMsd3WayQuicksort(String[] strAry) {
        if (strAry == null || strAry.length <= 1) {
            return;
        }
        innerRadixSortMsd3WayQuicksort(strAry, 0, strAry.length-1, 0);
    }

//    static int INSERT_THRESHOLD = 47;
    static int INSERT_THRESHOLD = 3;

    static void innerRadixSortMsd3WayQuicksort(String[] strAry, int lowIdx, int highIdx, int n) {
//        if (lowIdx >= highIdx) {
//            //这里可以再优化，减少递归深度
//            return;
//        }
        if (highIdx - lowIdx <= INSERT_THRESHOLD) {
            insertSort(strAry, lowIdx, highIdx);
            return;
        }

        int ltIdx=lowIdx, gtIdx=highIdx;
        int pivotR = calcRadixByString(strAry[lowIdx], n);

        for (int i = lowIdx+1; i <= gtIdx; ) {
            int r = calcRadixByString(strAry[i], n);
            if (r < pivotR) {
                swap(strAry, ltIdx++, i++);
            } else if (r > pivotR) {
                swap(strAry, i, gtIdx--);
            } else {
                i++;
            }
        }

        //小于部分继续排序
        innerRadixSortMsd3WayQuicksort(strAry, lowIdx, ltIdx-1, n);
        //等于部分，继续往下一位排序，但是如果pivotR=-1，就是这位已经没有字符了，后续也没有了，就不用继续递归了
        if (pivotR >= 0) {
            innerRadixSortMsd3WayQuicksort(strAry, ltIdx, gtIdx, n+1);
        }
        //大于部分继续排序
        innerRadixSortMsd3WayQuicksort(strAry, gtIdx+1, highIdx, n);

    }

    static void insertSort(String[] ary, int lowIdx, int highIdx) {
        //所以从无序部分第一个开始循环，要插入有序部分，所以要从index为1开始，0是有序部分
        for (int unsortedFirstIdx = lowIdx+1; unsortedFirstIdx <= highIdx; unsortedFirstIdx++) {
            //未排序部分的第一个值，待插入，备份下值，后续要用
            String unsortedFirstVal = ary[unsortedFirstIdx];
            //未排序的往前移一格，找到排序的最后一个元素
            //作为初始比较索引位置idx，第一次是排序部分的最后一个元素
            //这里初始化要放在外面，保证后面最后插入的时候能用到，否则用不到
            int sortedCurrentIdx = unsortedFirstIdx-1;
            //int sortedVal = ary[sortedCurrentIdx] 就是当前排序部分的值和未排序待插入的值比较，
            // 如果大于，那么往后移动一格，腾出位置，让其插入或者让前面的元素移动到这里
            //循环结束后当前节点往前移动一格--自减1继续判断
//            for (; sortedCurrentIdx >= lowIdx && ary[sortedCurrentIdx] > unsortedFirstVal ; sortedCurrentIdx--) {
            for (; sortedCurrentIdx >= lowIdx && compare(ary[sortedCurrentIdx], unsortedFirstVal) > 0 ; sortedCurrentIdx--) {
                ary[sortedCurrentIdx+1] = ary[sortedCurrentIdx];
            }
            //跳出循环后，说明到了该插入的位置了
            //插入操作，把未排序部分的第一个值，插入到这里，这里是稳定性的关键
            ary[sortedCurrentIdx+1] = unsortedFirstVal;
        }
    }

//    //这个其实不合适，按位比较字符串，从最后开始，不对齐啊，没必要，即使反转也不行，对齐的也不对
//    //一般字符串比较不用这个，不直观不直觉，但是可能别的场景会合适，比如右对齐时排序？
//    static void radixSortLsd(String[] strAry) {
//
//    }

    //SA-IS算法 诱导推导出 Lucene
    //Induced Suffix Array – Simplified Alphabet Induced Sorting
    //后缀数组排后缀，前缀数组排前缀；后缀加 LCP Longest Common Prefix Array最长公共前缀数组，前缀用 Trie前缀树。

}
