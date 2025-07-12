package sort;

import java.util.Arrays;

class AryAdvSortApp {
    public static void main(String[] args) {
        int[] ary = {8, 3, 5, 4, 5};
        quickSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    //基本操作
    //compare比较
    //左减右
    // > 0 左大于右（右小于左）
    // = 0 左等于右（右等于左）
    // < 0 左小于右（右大于左）
    //通用，但不直观，没有已有指令效率高
    private static int compare(int leftVal, int rightVal) {
        int delta = leftVal - rightVal;
        return delta;
    }

    //swap交换
    private static void swap(int[] ary, int leftIdx, int rightIdx) {
        if (leftIdx != rightIdx) {
            int t = ary[leftIdx];
            ary[leftIdx] = ary[rightIdx];
            ary[rightIdx] = t;
            //t = 0; 针对对象，复杂对象，设置称null，利用gc清空内存，主动释放
        }
    }

    //没有纯冒泡排序的升级算法，主要都是基于选择排序的
    //内省排序 introsort introspect 快排（主体，分治拆解）+堆排（处理大数组）+插入（处理小数组，可shuffle）
    //内省再进化pdqsort Pattern-defeating quicksort 模式击败 防规律输入 重复值用三向切分
    //自适应算法，需要有前期统计的步骤耗时，对于大数组有用，小数组可能没必要
    //总体上需要有分治思想，所以主题快排为主
    //时间复杂度关键还是在于排序本身的交换，时间复杂度最该考虑这个

    //比较和交换，先比较，再交换，根据比较结果判断是否需要交换
    //比较不动位置，只读，快
    //交换动位置，写操作，慢
    //再升级双轴快排 dual pivot sort，含三色排序（荷兰国旗问题）这一特例

    //1 选择排序（每次都交换冒泡排序）+分区 快排 实战基础类型 小数据量 内存
    //单轴快排
    public static void quickSort(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        innerQuickSort(ary, 0, ary.length-1);
    }

    //实际交换的位置，处理好后分成2区，给出墙的位置
    private static int partition(int[] ary, int idxLeft, int idxRight) {
        //选择基准值，简单选择最右边的值
        //当然，这里可以有很多讲究和算法可以选取，原则是尽可能平均开分区后的两边的数量大致相等，保持平衡
        //todo 罗列下
        int valPivot = ary[idxRight];

        //就这里最像基础排序，选择排序每次交换版本
        //记录中间点墙的位置
        int idxWall = idxLeft;
        for (int i = idxLeft; i < idxRight; i++) {
            if (ary[i] < valPivot) {
                swap(ary, idxWall, i);
                idxWall++;
            }
        }
        swap(ary, idxWall, idxRight);
        return idxWall;
    }

    private static void innerQuickSort(int[] ary, int idxLeft, int idxRight) {
        if (idxLeft >= idxRight) {
            return;
        }
        int idxWall = partition(ary, idxLeft, idxRight);
        innerQuickSort(ary, idxLeft, idxWall-1);
        innerQuickSort(ary, idxWall+1, idxRight);
    }

    //todo 相等的重复值，使用三向切分 单轴快排的补丁

    //todo 双轴快排

    //todo 以及各种快排的优化

    //2 选择排序+堆 堆排序 只记位置，最多交换一次 理论 实战少 不是不用
    // 适合外排？
    public static void heapSort(int[] ary) {

    }

    //3 插入排序+分治 希尔排序 Shell排序 缩小增量排序 间隔序列 缩小间隔排序 增量序列，这里间隔就是增量，增量就是间隔
    //适合小数量，就是分法分区方法，和TimSort不一样，一个间隔，一个顺序
    //ShellSort
    //这个高级优化法，因为核心考虑到的就是移动，每个都移动，所以用间隔，可以设法避免太多不必要的移动
    public static void shellSort(int[] ary) {

    }

    //4 归并排序+多线程
    public static void mergeSortMultiThread() {
        //并发分数组 单线程主线程
        //并行排序各个数组 异步多线程
        //并发合并 单线程主线程
    }

    //todo 内省排序

    //todo 自适应版本？

}
