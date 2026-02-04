package operation.deterministic.op2seq.search.linear.decrease2;

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

    int binarySearchIdx2(int[] ary, int val) {
        if (val < ary[0]) {
            return 0;
        } else if (ary[ary.length-1] < val) {
            return ary.length;
        } else if (val == ary[0]) {
            return 1;
        } else if (val == ary[ary.length-1]) {
            return ary.length;
        } else {
            int leftIdx = 0, rightIdx=ary.length-1;
            for (
                 ; leftIdx < ary.length && rightIdx >= 0 && leftIdx<rightIdx;
            ) {
                //获取中间位置
                int midIdx = (leftIdx+rightIdx)/2;
                int midVal = ary[midIdx];
                if (val < midVal) {
                    rightIdx = midIdx;
                    continue;
                } else if (midVal < val) {
                    leftIdx = midIdx;
                    continue;
                } else if (val == ary[midIdx]) {
                    leftIdx = rightIdx = midIdx;
                    break;
                } else {
                    //noway
                }
            }
            if (leftIdx==rightIdx) {
                return leftIdx+1;
            } else if (leftIdx < rightIdx){
                return rightIdx;
            } else {
                //noway
            }
        }
        return -1;
    }

}
