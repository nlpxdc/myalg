package sort;

//非交换类排序
//借助于外部存储空间，外部结构，外部协助，场景受限，但是 O(n)
//适合整数，字符串？
//优化方法，可以多线程并行排序部分的逻辑，和归并排序一样
class AryNonSwapSortApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    //1 计数排序 O(n+k)
    //数据分布情况
    public static void countingSort(int[] ary) {}

    //3 基数排序，进制（含计数排序或桶排序作为基础） 本福特定理
    //适用于整数，字符串 O(d*(n+k))
    //k是基数 通常10或256
    //适合多关键字排序，似稳定排序算法
    public static void radixSort(int[] ary) {}

}
