package sort;

import java.util.Arrays;

public class BasicSortApp {
    public static void main(String[] args) {
        //test sort methods
//        int[] ary = {8, 3, 5, 4, 5};
//        insertionSort(ary);
//        System.out.println(Arrays.toString(ary));
        int[] ary2 = {8, 3, 5, 4, 5};
        selectionSort(ary2);
        System.out.println(Arrays.toString(ary2));

    }


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
    //2。选择排序 原地
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
    //3。冒泡排序 原地
    public static void bubbleSort(int[] ary) {}
    //4。归并排序 非原地
    public static int[] mergeSort(int[] ary) {
        return null;
    }

}
