package sort;

import java.util.Arrays;

public class BasicSortApp {
    public static void main(String[] args) {
        //test sort methods
        int[] ary = {8, 5, 3, 5, 8};
        insertionSort(ary);
        System.out.println(Arrays.toString(ary));

    }


    //1。插入排序 原地
    public static void insertionSort(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            int currentVal = ary[i];
            int j = i-1;
            for (; j >= 0 && ary[j] > currentVal ; j--) {
                ary[j+1] = ary[j];
            }
            ary[j+1] = currentVal;
        }
    }
    //2。选择排序 原地
    public static void selectionSort(int[] ary) {}
    //3。冒泡排序 原地
    public static void bubbleSort(int[] ary) {}
    //4。归并排序 非原地
    public static int[] mergeSort(int[] ary) {
        return null;
    }

}
