package sort;

class AryAdvSortApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    //比较和交换，先比较，再交换，根据比较结果判断是否需要交换
    //比较不动位置，只读，快
    //交换动位置，写操作，慢

    //1 冒泡排序+分区 快排 实战基础类型 可提前停止 小数据量 内存
    public static void quickSort(int[] ary) {

    }
    //记忆最深的算法，最鸡肋，不容易分类 有思维过度作用

    //2 选择排序+堆 堆排序 只记位置，最多交换一次 理论 实战少
    // 适合外排？
    public static void heapSort(int[] ary) {

    }
    //3 插入排序+分治 希尔排序 Shell排序 缩小增量排序 间隔序列 缩小间隔排序 增量序列，这里间隔就是增量，增量就是间隔
    //实战复杂对象类型
    //小数据量 内存
    //ShellSort
    public static void reduceGapSort(int[] ary) {

    }

}
