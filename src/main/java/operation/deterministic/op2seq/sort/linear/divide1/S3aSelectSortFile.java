package operation.deterministic.op2seq.sort.linear.divide1;

class S3aSelectSortApp {
    public static void main(String[] args) {
        int[] ary = {9,8,7,6,5,4,3,2,1};
        S3aSelectSortApp.selectSort(ary);
    }

    public static void selectSort(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            singleSelectSort(ary, i);
        }
    }

    private static void singleSelectSort(int[] ary, int round) {
        if (ary == null || ary.length == 0) {
            return;
        }
        int lastIdx = ary.length-1-round;
        int maxIdx = 0;
        for (int i = 1; i <= lastIdx; i++) {
            if (ary[i] > ary[maxIdx]) {
                maxIdx = i;
            }
        }
        if (maxIdx != lastIdx) {
            swap(ary, maxIdx, lastIdx);
        }
    }

    private static void swap(int[] ary, int idxA, int idxB) {
        if (idxA != idxB) {
            int t = ary[idxA];
            ary[idxA] = ary[idxB];
            ary[idxB] = t;
        }
    }

}
