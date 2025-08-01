package sort;

//用的基数排序
//假设范围为英文小写字母
//这是二维的排序，有多位，按位处理，从头开始
class AryStringSortApp {
    public static void main(String[] args) {

    }

    //如何切分桶、如何组织数据、是否稳定、是否原地，有多种写法
    //主要有2种，一共有4种？
    //American-flag sort 环交换 不稳定 原地 理论上 得不偿失 不常用 思路可借鉴
    //链表桶排序（in-place list） 稳定 原地 理论上 得不偿失 不常用思路可借鉴

    //基数排序 radix sort MSD 字符串排序，场景：字符串、前缀重复多、长度又不一致
    //文件路径或类全限定名

    //经典基础版 计数排序递归版 稳定 通用版 教学版 借鉴思路
    //辅助数组+计数数组（递归计数版）
    //先计数再递归？还是先递归再计数？方法名跟着改
    static void radixSortMsdCntRecur(String[] strAry) {

    }

    //高阶工程版 3way radix quicksort 不稳定 实际常用版
    //三向切分快速排序（3-way radix quicksort）
    //双或三指针原地交换
    static void radixSortMsd3WayQuicksort(String[] strAry) {

    }

//    //这个其实不合适，按位比较字符串，从最后开始，不对齐啊，没必要，即使反转也不行，对齐的也不对
//    //一般字符串比较不用这个，不直观不直觉，但是可能别的场景会合适，比如右对齐时排序？
//    static void radixSortLsd(String[] strAry) {
//
//    }

}
