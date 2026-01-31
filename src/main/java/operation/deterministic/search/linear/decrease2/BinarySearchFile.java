package operation.deterministic.search.linear.decrease2;

//必须有序，小到大，大到小，带区间
//必须预处理
class BinarySearchApp {
    public static void main(String[] args) {

    }

//    int searchIdx() {}
    int binarySearchIdx(int[] ary, int val) {
        if (val < ary[0]) {
            return -1;
        } else if (ary[ary.length-1] < val) {
            return -1;
        } else if (val == ary[0]) {
            return 0;
        } else if (val == ary[ary.length-1]) {
            return ary.length-1;
        } else {
            for (int leftIdx = 0, rightIdx=ary.length-1
                 ; leftIdx < ary.length && rightIdx >= 0 && leftIdx<=rightIdx;
            ) {
                //获取中间位置
                int midIdx = (leftIdx+rightIdx)/2;
                if (val < ary[midIdx]) {
                    rightIdx = midIdx;
                    continue;
                } else if (ary[midIdx] < val) {
                    leftIdx = midIdx;
                    continue;
                } else if (val == ary[midIdx]) {
                    return midIdx;
                } else {
                    //noway
                }
            }
        }
        return -1;
    }
}
