package operation.deterministic.sort.linear.divide1;

public class S1aBubbleSortFile {
    public static void main(String[] args) {

    }

    private static void bubbleSort(int[] ary) {
        for (int i = 0;
             i < ary.length-1;
             i++) {
            boolean hasSwap = singleBubbleSort(ary, i);
            //没有交换过，那么说明已经有序，跳出循环
            if (!hasSwap) {
//                break;
                return;
            }
        }
    }

    private static boolean singleBubbleSort(int[] ary, int round) {
        boolean hasSwap = false;
        for (int i = 0;
             i < ary.length-1-round;
             i++) {
            if (ary[i] > ary[i+1]) {
                swap(ary, i, i+1);
                hasSwap = true;
            }
        }
        return hasSwap;
    }

    private static void swap(int[] ary, int idxA, int idxB) {
        int t = ary[idxA];
        ary[idxA] = ary[idxB];
        ary[idxB] = t;
    }

}
