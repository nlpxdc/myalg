package sort;

import java.util.Arrays;

class AryAdvSortApp {
    public static void main(String[] args) {
        int[] ary = {8, 4, 5, 3, 5};
//        quickSort(ary);
//        shellSortV2(ary);
        heapSort(ary);
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
    //选择排序，高阶，时间维度二分（递归版本！和栈版本？）
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
    //选择排序 高阶 空间等价替换无序顺序表
    public static void heapSort(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            //首先进行堆化，获取最大值，值处理需要处理的剩余部分，不包含已处理的部分
            heapify(ary, ary.length-i);
            //堆顶，也就是最大值和队列尾部交换，剩余部分的队列尾部，不包含已处理的部分
            CbtUtil.swap(ary, 0, ary.length-1-i);
        }
    }

    public static void heapify(int[] ary, int size) {
        for (int i = CbtUtil.getParentIdxByTreeNodeIdx(ary.length-1); i >=0; i--) {
            siftDownOfMaxHeap(ary, i, size);
        }
    }

    //后序可以更简洁一些，代码行数再少一些
    static void siftDownOfMaxHeap(int[] ary, int treeNodeIdx, int size) {
        int currentIdx = treeNodeIdx;
        //这里下沉的最大高度其实是树的最大高度减去当前高度
        //这里最精彩，显式的用for的边界值限定了循环最大次数，更利于准确分析时间复杂度
        for (int i = 0; i <= (CbtUtil.getTreeHeight(size) - CbtUtil.getTreeNodeHeightByTreeNodeIdx(treeNodeIdx)); i++) {
//        for (int i = 0; i <= CbtUtil.getTreeHeight(size); i++) {
            //先找到自己的左右子节点
            int leftIdx = CbtUtil.getLeftChildIdxByTreeNodeIdx(currentIdx);
            int rightIdx = CbtUtil.getRightChildIdxByTreeNodeIdx(currentIdx);
            //再判断下左右子节点是否存在，即是否小于size边界
            boolean hasLeft = leftIdx < size;
            boolean hasRight = rightIdx < size;
            //找出左右子节点更大的那一个
            if (!hasRight && !hasLeft) {
                return;
            } else if (hasRight && !hasLeft) {
                throw new RuntimeException("根据定义，不可能");
            } else if (!hasRight && hasLeft) {
                int leftVal = getTreeNodeValByTreeNodeIdx(ary, leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(ary, currentIdx);
                if (currentVal < leftVal) {
                    CbtUtil.swap(ary, currentIdx, leftIdx);
                    currentIdx = leftIdx;
                    continue;
                }
            } else if (hasRight && hasLeft) {
                int rightVal = getTreeNodeValByTreeNodeIdx(ary, rightIdx);
                int leftVal = getTreeNodeValByTreeNodeIdx(ary, leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(ary, currentIdx);
                if (rightVal > leftVal) {
                    //右节点大，与右节点比较交换
                    if (currentVal < rightVal) {
                        CbtUtil.swap(ary, currentIdx, rightIdx);
                        currentIdx = rightIdx;
                        continue;
                    }
                } else {
                    //反之，左节点大，与左节点比较交换
                    if (currentVal < leftVal) {
                        CbtUtil.swap(ary, currentIdx, leftIdx);
                        currentIdx = leftIdx;
                        continue;
                    }
                }
            }
        }
    }

    static int getTreeNodeValByTreeNodeIdx(int[] ary, int treeNodeIdx) {
        return ary[treeNodeIdx];
    }

    //3a 折半插入排序 二分查找替换顺序查找，针对有序部分
    //二分查找算法 时间维度，有序情况下，数据结构二分查找树 BST binary search tree 空间维度
    public static void biSearchInsertionSort(int[] ary) {

    }

    //3b 插入排序+分治 希尔排序 Shell排序 缩小增量排序 间隔序列 缩小间隔排序 增量序列，这里间隔就是增量，增量就是间隔
    //主要是移动，而非交换，交换是两两，移动就要看整体了
    //适合小数量，就是分法分区方法，和TimSort不一样，一个间隔，一个顺序
    //ShellSort
    //这个高级优化法，因为核心考虑到的就是移动，每个都移动，所以用间隔，可以设法避免太多不必要的移动
    public static void shellSort(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        //idx 意义不明可该井，和插入排序不像，可以再看看，应该是一致的，但是也等价
        //这里为了性能，也可以使用位移？
        for (int gap = ary.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < ary.length; i++) {
                int t = ary[i];
                int j = i;
                for (; j >= gap && ary[j-gap] > t; j-=gap) {
                    ary[j] = ary[j-gap];
                }
                ary[j] = t;
            }
        }
    }

    public static void shellSortV2(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        //gap可以有多种方式的数列
        for (int gap = ary.length/2; gap > 0; gap /= 2) {
            for (int unsortedFirstIdx = gap; unsortedFirstIdx < ary.length; unsortedFirstIdx++) {
                int unsortedFirstVal = ary[unsortedFirstIdx];
                int i = unsortedFirstIdx-gap;
                for (; i >= 0 && ary[i] > unsortedFirstVal; i-=gap) {
                    ary[i+gap] = ary[i];
                }
                ary[i+gap] = unsortedFirstVal;
            }
        }
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

class CbtUtil {
    static void swap(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    static int pow2(int n) {
        return 1 << n;
    }

    static int getParentIdxByTreeNodeIdx(int treeNodeIdx) {
        return (treeNodeIdx-1)/2;
    }

    static int getLeftChildIdxByTreeNodeIdx(int treeNodeIdx) {
        return 2*treeNodeIdx+1;
    }

    static int getRightChildIdxByTreeNodeIdx(int treeNodeIdx) {
        return 2*treeNodeIdx+2;
    }

    static int getTreeHeight(int size) {
        return  ((int) (Math.log(size) / Math.log(2)));
    }

    static int getTreeNodeHeightByTreeNodeIdx(int treeNodeIdx) {
        return  ((int) (Math.log(treeNodeIdx+1) / Math.log(2)));
    }

}
