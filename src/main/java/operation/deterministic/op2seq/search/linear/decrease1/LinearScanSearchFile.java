package operation.deterministic.op2seq.search.linear.decrease1;

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

    int searchIdxL2(int[] ary, int val) {
        for (int j = ary.length-1; j >= 0; j--) {
            if (ary[j] == val) {
                return j;
            }
        }
        return -1;
    }

    int searchIdxL3(int[] ary, int val) {
        for ( int i = 0, j = ary.length-1 ; i < ary.length && j >= 0 && i <= j ; i++, j--) {
            if (ary[i] == val) {
                return i;
            }
            if (i != j) {
                if (ary[j] == val) {
                    return j;
                }
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
        } else if (val == ary[0]) {
            return 1;
        } else if (val >= ary[ary.length-1]) {
            return ary.length;
        } else {
            for (int i = 0, j = ary.length-1; i < ary.length-1 && j >= 1 && i < j ; i++,j--) {
                if (ary[i] <= val && val < ary[i+1]) {
                    return i+1;
                }
                if (ary[j-1] <= val && val < ary[j]) {
                    return j;
                }
            }
            return -1;
        }
    }

}
