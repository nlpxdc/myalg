package sort;

import java.util.Arrays;

public class BasicSortApp {
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
        int[] sortAry3 = {3,4,5,5,8};
        bubbleSortWithStop(sortAry3);
        System.out.println(Arrays.toString(sortAry3));


    }

    //每种都可以考虑，是否可以提前结束，以来外部标识判断

    //1。插入排序 原地
    //分两部分 有序无序，开始第一个默认有序部分，后面都是无序部分
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
    //2。选择排序 原地 使用idx位置，最后交换一次
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
    //2b。选择排序 原地 使用value值，每次都交换一下
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
    //3。冒泡排序 原地
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
    //3b。 冒泡排序 原地 提前结束，带标志位swap，如果某一趟没有发生过交换，说明已经有序即可停止，无需再运行
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
    //4。归并排序 非原地
    public static int[] mergeSort(int[] ary) {
        return null;
    }

}
