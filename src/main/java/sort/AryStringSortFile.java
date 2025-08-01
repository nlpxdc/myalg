package sort;

//用的基数排序
//假设范围为英文小写字母
//这是二维的排序，有多位，按位处理，从头开始
class AryStringSortApp {
    public static void main(String[] args) {

    }



    //基数排序 radix sort MSD 字符串排序，场景：字符串、前缀重复多、长度又不一致
    //文件路径或类全限定名
//    static void radixSortMsd(String[] strAry) {
//
//    }



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
