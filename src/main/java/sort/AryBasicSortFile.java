package sort;

//数组版本，后序可以再考虑链表版本
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
        int[] ary4 = {8, 3, 5, 4, 5};
//        Map2UpDownVo map = mapFor2UpDown(ary4);
//        mergeSort(ary4);
        mergeSortRecur(ary4);

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
    //升级是快速排序, quick sort 算法层面的递归拆解 基本数据类型 数组 链表 jdk DualPivotQuicksort
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

    //2a。选择排序 原地 稳定因为用值比较 使用value值，每次都交换一下，这个交换多次，性能差
    public static void selectionSortWithValue(int[] ary) {
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
    public static void insertionSort(int[] ary) {
        //所以从无序部分第一个开始循环，要插入有序部分，所以要从index为1开始，0是有序部分
        for (int i = 1; i < ary.length; i++) {
            int unsortedFirst = ary[i];
            int j = i-1;
            for (; j >= 0 && ary[j] > unsortedFirst ; j--) {
                ary[j+1] = ary[j];
            }
            ary[j+1] = unsortedFirst;
        }
    }

    //4。归并排序 非原地 不在于排序本身（排序可以使用前面三种基础排序算法），在于map reduce，拆解和合并
    //当然也可以递归调用自身排序，但是实际上不合适
    //方便改造成多线程同时处理，分解以后各自的排序可以同步进行，合并不行，必须是同步操作，这个是操作同一个数组资源
    //思路就是拆解，并发同时操作，快
    //多线程的结合！
    public static void mergeSort(int[] ary) {
        if (ary == null || ary.length <= 1) {
            return;
        }
        Map2UpDownVo mapVo = mapFor2UpDown(ary);
        //各自排序 这部可以并行
//        insertionSort(mapVo.upAry);
//        insertionSort(mapVo.downAry);
        bubbleSortWithStop(mapVo.upAry);
        bubbleSortWithStop(mapVo.downAry);
        //这里排序还能递归调用自身进行排序，不做任何其他处理，将会分解成单个元素的数组处理，然后再合并，粒度太小了，速度会变慢，深度太深，而且没批处理
        //如果只是使用其他基本排序算法，那么就不存在递归，但是实际情况可能数量很大，也会慢
        //合适的化根据具体数量，做一个自适应的算法，会拆解成一定数量的数组进行单独排序，然后再合并，掌握好一个度，会更快，充分利用分解，也不会太深
        //这里不是写死的分批数量，利用递归性质，加上自适应算法，让他自身拆解合适的数组个数进行处理，分解和合并

        //这步不能并行，智能串行处理，属于并发
        reduceFor2UpDown(ary, mapVo);
    }

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

}

class Map2UpDownVo {
    int[] upAry;
    int[] downAry;
}
