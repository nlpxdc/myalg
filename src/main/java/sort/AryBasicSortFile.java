package sort;

import java.util.Arrays;

//数组版本，后序可以再考虑链表版本
//天然方便支持双引用（不可变指针）操作，指针，下标，索引，本质上就是地址操作
//都是比较类(Compare)，交换类(Swap)，先比较 后交换，有必要再交换，更通用 O(nlogn)
//todo 最后别忘了，从各个维度特性上去分析和整理每种算法
//基础操作，抽象出单独函数，compare swap
//AoS vs SoA (SIMD / AVX2)
class AryBasicSortApp {
    public static void main(String[] args) {
        //test sort methods
//        int[] ary = {8, 3, 5, 4, 5};
//        insertionSort(ary);
//        System.out.println(Arrays.toString(ary));
//        int[] ary2 = {8, 3, 5, 4, 5};
//        selectionSort(ary2);
//        System.out.println(Arrays.toString(ary2));
//        int[] ary3 = {8, 3, 5, 4, 5};
//        int[] sortAry2 = {3,4,5,5,8};
//        selectionSortWithValueStop(sortAry2);
//        System.out.println(Arrays.toString(sortAry2));
//        int[] ary4 = {8, 3, 5, 4, 5};
//        bubbleSort(ary4);
//        System.out.println(Arrays.toString(ary4));
//        int[] sortAry3 = {3,4,5,5,8};
//        bubbleSortWithStop(sortAry3);
//        System.out.println(Arrays.toString(sortAry3));
//        int[] ascAry3 = {3,4,5,5,8};
//        boolean b = beAsc(ascAry3);
//        boolean b1 = beDesc(ascAry3);
//        int[] descAry3 = {8,5,5,4,3};
//        boolean b2 = beAsc(descAry3);
//        boolean b3 = beDesc(descAry3);
//        int[] ary4 = {8, 3, 5, 4, 5};
//        boolean b4 = beAsc(ary4);
//        boolean b5 = beDesc(ary4);
//        int[] ary4 = {8, 3, 5, 4, 5};
//        Map2UpDownVo map = mapFor2UpDown(ary4);
//        mergeSort(ary4);
//        mergeSortRecur(ary4);
//        insertionSort(ary4);

        int[] ary5 = {7, 2, 9, 2, 0, 5, 7, 1, 3};
        bucketSort(ary5);
        System.out.println(Arrays.toString(ary5));

    }

    public static boolean beAsc(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            if (ary[i] > ary[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean beDesc(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            if (ary[i] < ary[i+1]) {
                return false;
            }
        }
        return true;
    }

    //自适应算法，阈值
    //每种都可以考虑，是否可以提前结束，以来外部标识判断

    //1。冒泡排序 原地 稳定性 交换比较赋值太多次了，3次
    //时间和空间，都是局部性原理 这个和插入比较像
    public static void bubbleSort(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            for (int j = 0; j < ary.length-i-1; j++) {
                if (ary[j] > ary[j+1]) {
                    int t = ary[j+1];
                    ary[j+1] = ary[j];
                    ary[j] = t;
                }
            }
        }
    }
    //1b。 冒泡排序 原地 提前结束，带标志位swap，如果某一趟没有发生过交换，说明已经有序即可停止，无需再运行
    public static void bubbleSortWithStop(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            boolean beSwapped = false;
            for (int j = 0; j < ary.length-i-1; j++) {
                if (ary[j] > ary[j+1]) {
                    int t = ary[j+1];
                    ary[j+1] = ary[j];
                    ary[j] = t;
                    beSwapped = true;
                }
            }
            if (!beSwapped) {
                return;
            }
        }
    }

    //1.5。选择排序 原地 稳定因为用值比较 使用value值，每次都交换一下，这个交换多次，性能差
    //介于冒泡和选择之间，有冒泡特点（循环结构），也有选择特点（远距离比较）
    //和冒泡内部最像，可以说升级就是快排
    //升级是快速排序, quick sort 算法层面的递归拆解 基本数据类型 数组 链表 jdk DualPivotQuicksort
    //时间局部性原理，空间不是局部性原理，差一点意思
    public static void selectionSortEveryTime(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            for (int j = i+1; j < ary.length; j++) {
                if (ary[i] > ary[j]) {
                    int t = ary[i];
                    ary[i] = ary[j];
                    ary[j] = t;
                }
            }
        }
    }
    //2。选择排序 原地 不稳定因为用idx 使用idx位置，最后交换一次，这个只读，只交换一次，性能好，利用位置
    //减少交换的次数，先整体比较，只在最后一次做一次交换，代价缓存命中低，空间离得远，但适合外存排序？因为可以减少io次数
    //升级是堆排序，heap sort 数据结构层面的递归拆解
    //工业上几乎不用
    //时间局部性原理，空间不是局部性原理，差一点意思
    public static void selectionSort(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            int currentMinIdx = i;
            for (int j = i+1; j < ary.length && ary[currentMinIdx] > ary[j]; j++) {
                currentMinIdx = j;
            }
            if (i != currentMinIdx) {
                int t = ary[i];
                ary[i] = ary[currentMinIdx];
                ary[currentMinIdx] = t;
            }
        }
    }


    //3。插入排序 原地 稳定性 比较移动，只有1次，快
    //分两部分 有序无序，开始第一个默认有序部分，后面都是无序部分
    //升级是希尔排序，Shell sort，注意间隔序列 gap seq or increment seq，有多种，影响时间复杂度
    //复杂数据类型，对象线性列表 数组 链表 jdk TimSort
    //工业上小数组用
    //时间和空间都是局部性原理 这个和冒泡比较像
    public static void insertionSort(int[] ary) {
        //所以从无序部分第一个开始循环，要插入有序部分，所以要从index为1开始，0是有序部分
        for (int unsortedFirstIdx = 1; unsortedFirstIdx < ary.length; unsortedFirstIdx++) {
            int unsortedFirstVal = ary[unsortedFirstIdx];
            int i = unsortedFirstIdx-1;
            //int sortedVal = ary[i] 就是排序的值和未排序值比较
            for (; i >= 0 && ary[i] > unsortedFirstVal ; i--) {
                ary[i+1] = ary[i];
            }
            //这里是稳定性的关键
            ary[i+1] = unsortedFirstVal;
        }
    }

    //4。归并排序 非原地 不在于排序本身（排序可以使用前面三种基础排序算法），在于map reduce，拆解和合并
    //当然也可以递归调用自身排序，但是实际上不合适
    //方便改造成多线程同时处理，分解以后各自的排序可以同步进行，合并不行，必须是同步操作，这个是操作同一个数组资源
    //思路就是拆解，并发同时操作，快
    //多线程的结合！
    //类似TimSort
    //根据址分 idx分 花头在址上，设法均分，每次均分
    public static void mergeSort(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        Map2UpDownVo mapVo = mapFor2UpDown(ary);
        //各自排序 这步可以并行
        //复杂对象，用insertion sort，类似TimSort
        insertionSort(mapVo.upAry);
        insertionSort(mapVo.downAry);
        //基本类型，用冒泡
//        bubbleSortWithStop(mapVo.upAry);
//        bubbleSortWithStop(mapVo.downAry);
        //这里排序还能递归调用自身进行排序，不做任何其他处理，将会分解成单个元素的数组处理，然后再合并，粒度太小了，速度会变慢，深度太深，而且没批处理
        //如果只是使用其他基本排序算法，那么就不存在递归，但是实际情况可能数量很大，也会慢
        //合适的化根据具体数量，做一个自适应的算法，会拆解成一定数量的数组进行单独排序，然后再合并，掌握好一个度，会更快，充分利用分解，也不会太深
        //这里不是写死的分批数量，利用递归性质，加上自适应算法，让他自身拆解合适的数组个数进行处理，分解和合并

        //这步不能并行，智能串行处理，属于并发
        reduceFor2UpDown(ary, mapVo);
    }

    //todo 归并排序的原地版本 也应该带递归？再看
    public static void mergeSortLocal(int[] ary) {}

    //这里拆解到一个合适的度，范围去处理
    public static void mergeSortRecur(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        //控制数量，避免深度 也可以不控制，用原来的
        Map2UpDownVo mapVo = mapFor2UpDownMin(ary);
        //各自排序 并行
        mergeSortRecur(mapVo.upAry);
        mergeSortRecur(mapVo.downAry);

        //串行
        reduceFor2UpDown(ary, mapVo);
    }

    //todo 归并排序的栈版本 等价替换递归
    public static void mergeSortStack(int[] ary) {}

    //todo 归并的原地和栈组合，或栈和原地的组合

    //todo 自底向上的迭代版本，适合空间受限场景，如嵌入式
    //递归和迭代的是一种互逆转换吗？递归是从顶向下，迭代是从底向上？ 有可能，但不总是，看情况

    public static Map2UpDownVo mapFor2UpDown(int[] origAry) {
        Map2UpDownVo mapVo = new Map2UpDownVo();

        int half = origAry.length/2;

        int[] upAry = new int[half];
        for (int i = 0; i < half; i++) {
            upAry[i] = origAry[i];
        }
        mapVo.upAry = upAry;

        int[] downAry = new int[origAry.length-half];
        for (int i = 0; i < origAry.length - half; i++) {
            downAry[i] = origAry[half+i];
        }
        mapVo.downAry = downAry;

        return mapVo;
    }

    //这里要注意拆解的限制，结束时机，不能等到数组只有1个，要有个数组的最小值，适合多一些的数据
    //这里有递归吗？没有，直接逻辑控制即可 需要其他额外的变量 标志位 来进行记录处理
    //每个数组32个最少控制
    public static Map2UpDownVo mapFor2UpDownMin(int[] origAry) {
        Map2UpDownVo mapVo = new Map2UpDownVo();

        int half = origAry.length/2;

        int[] upAry = new int[half];
        for (int i = 0; i < half; i++) {
            upAry[i] = origAry[i];
        }
        mapVo.upAry = upAry;

        int[] downAry = new int[origAry.length-half];
        for (int i = 0; i < origAry.length - half; i++) {
            downAry[i] = origAry[half+i];
        }
        mapVo.downAry = downAry;

        return mapVo;
    }

    public static void reduceFor2UpDown(int[] origAry, Map2UpDownVo mapVo) {
        int origIdx = 0, upIdx = 0, downIdx = 0;

        for (; upIdx < mapVo.upAry.length && downIdx < mapVo.downAry.length; ) {
            if (mapVo.upAry[upIdx] <= mapVo.downAry[downIdx]) {
                origAry[origIdx++] = mapVo.upAry[upIdx++];
            } else {
                origAry[origIdx++] = mapVo.downAry[downIdx++];
            }
        }

        for (; upIdx < mapVo.upAry.length ; ) {
            origAry[origIdx++] = mapVo.upAry[upIdx++];
        }
        for (; downIdx < mapVo.downAry.length; ) {
            origAry[origIdx++] = mapVo.downAry[downIdx++];
        }
    }

    //2 桶排序 bucket sort 根据值value分，需要提前直到数据值的分布，要么通用盲猜，要么事先统计一次统计个大概，需要事先的统计计算成本，预处理成本，部common
    static void bucketSort(int[] ary) {
        //算桶 暂跳过
        //建桶
        //建3个桶 每个桶长度最大ary的个数 桶0放0-2， 桶1放3-6，桶2放7-9
        int[][] bucketAry = new int[3][ary.length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < ary.length; j++) {
                bucketAry[i][j] = Integer.MAX_VALUE;
            }
        }
        //分桶
        int bIdx0 = 0;
        int bIdx1 = 0;
        int bIdx2 = 0;
        for (int i = 0; i < ary.length; i++) {
            int t = ary[i];
            if (0<=t && t<=2) {
                bucketAry[0][bIdx0++] = t;
            } else if (3<=t && t<=6) {
                bucketAry[1][bIdx1++] = t;
            } else if (7<=t && t<=9) {
                bucketAry[2][bIdx2++] = t;
            } else {
                throw new RuntimeException("超出取值范围");
            }
        }
        //桶内排序
        insertionSort(bucketAry[0]);
        insertionSort(bucketAry[1]);
        insertionSort(bucketAry[2]);
        //桶合并
        int aryIdx = 0;
        for (int i = 0; i < bIdx0; i++) {
            ary[aryIdx++] = bucketAry[0][i];
        }
        for (int i = 0; i < bIdx1; i++) {
            ary[aryIdx++] = bucketAry[1][i];
        }
        for (int i = 0; i < bIdx2; i++) {
            ary[aryIdx++] = bucketAry[2][i];
        }
    }

}

class Map2UpDownVo {
    int[] upAry;
    int[] downAry;
}
