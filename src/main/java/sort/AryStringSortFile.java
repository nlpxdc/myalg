package sort;

//用的基数排序
//假设范围为英文小写字母
//这是二维的排序，有多位，按位处理，从头开始
//不一定依赖计数排序，也可依赖交换排序快排
class AryStringSortApp {
    public static void main(String[] args) {

    }

    //单次比较，两两，并非一定应用于排序，排序用基数
    //二维 多位 相当于char[]和char[]比较，长度不一，等同于String，化作单个元素
    //字符串比较算法也有多种可以写
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
    static void radixSortMsdCntRecur(String[] strAry) {

    }

    //高阶工程版 3way radix quicksort 不稳定 实际常用版
    //三向切分快速排序（3-way radix quicksort）
    //双或三指针原地交换 无辅助数组 无额外空间 可不计数，靠交换
    static void radixSortMsd3WayQuicksort(String[] strAry) {

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
