package operation.deterministic.search.linear.decrease1;

//未知序，无序，也可有序
class LinearScanSearchApp {
    public static void main(String[] args) {

    }

    int searchIdx(int[] ary, int val) {
        for (int i = 0; i < ary.length; i++) {
            if (ary[i] == val) {
                return i;
            }
        }
        return -1;
    }

    int searchIdx2(int[] ary, int val) {
        if (ary == null || ary.length==0) {
            return -1;
        }
        if (val < ary[0]) {
            return 0;
        }
        if (val > ary[ary.length-1]) {
            return ary.length;
        }

        for (int i = 0; i < ary.length-1; i++) {
            if (ary[i] < val && val <= ary[i+1]) {
                return i+1;
            }
        }
        return ary.length;
    }

}
