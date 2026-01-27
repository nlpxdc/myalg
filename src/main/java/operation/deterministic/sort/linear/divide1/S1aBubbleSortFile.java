package operation.deterministic.sort.linear.divide1;

public class S1aBubbleSortFile {
    public static void main(String[] args) {

    }

    private static void bubbleSort(int[] ary) {
        for (int i = 0;
             i < ary.length;
             i++) {
            singleBubbleSort(ary, i);
        }
    }

    private static void singleBubbleSort(int[] ary, int round) {
        for (int i = 0;
             i < ary.length-1-round;
             i++) {
            if (ary[i] > ary[i+1]) {
                swap(ary, i, i+1);
            }
        }
    }

    private static void swap(int[] ary, int idxA, int idxB) {
        int t = ary[idxA];
        ary[idxA] = ary[idxB];
        ary[idxB] = t;
    }

}
