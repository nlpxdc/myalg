package operation.retvoid.sort.divide1;

class S1aInsertSortApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int[] ary = {9,8,7,6,5,4,3,2,1};
        S1aInsertSortApp.insertSort(ary);
    }

    public static void insertSort(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            singleInsertSort(ary, i);
        }
    }

    private static void singleInsertSort(int[] ary, int idx) {
        int t = ary[idx];
        int i = idx-1;
        for (; i >= 0; i--) {
            if (ary[i] > t) {
                ary[i+1]=ary[i];
            } else {
                break;
            }
        }
        if (i != idx-1) {
            ary[i+1]=t;
        }
    }

}
