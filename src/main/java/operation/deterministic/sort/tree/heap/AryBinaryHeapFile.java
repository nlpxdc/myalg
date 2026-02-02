package operation.deterministic.sort.tree.heap;

class AryBinaryHeapApp {
}

class AryBinaryHeap {
    int[] ary;
    int size;

    AryBinaryHeap() {
        ary = new int[10000];
        size = 0;
    }

    AryBinaryHeap(int[] origAry) {
        this.ary = new int[10000];
        this.size = 0;
        //根据origAry，建堆
    }

    void addVal(int val) {
        //先把值追加到末尾
        ary[size++] = val;
        //进行上浮操作
        for (int currIdx = size; currIdx>=0; ) {
//            int parentIdx =
        }
    }

    int depth(int idx) {
        return 31-Integer.numberOfLeadingZeros(idx+1);
    }

    int height(int idx) {
        return depth(size-1) - depth(idx);
    }

    int treeHeight() {
        return height(0);
    }

    //大顶堆
    int getMax() {
        if (size > 0) {
            return ary[0];
        }
        return -1;
    }

    int pull() {
        int currMax = getMax();

        //删除idx=0的值，直接覆盖，用最后一个值覆盖，并清空最后一个值
        ary[0] = ary[size-1];
        ary[size-1] = 0;
        size--;
        //替换后，用新值进行下沉操作
        for (int depth = 0, currIdx=0; depth < treeHeight() ; depth++ ) {
            int lastIdx = size-1;
            int leftIdx = 2*currIdx+1;
            int rightIdx = 2*currIdx+2;
            int currVal = ary[currIdx];

            if (rightIdx <= lastIdx) {
                //两节点都有值
                int leftVal = ary[leftIdx];
                int rightVal = ary[rightIdx];
                int maxChildVal = Math.max(leftVal, rightVal);
                if (currVal < maxChildVal) {
                    if (leftVal > rightVal) {
                        swap(currIdx, leftIdx);
                        currIdx = leftIdx;
                        continue;
                    } else {
                        swap(currIdx, rightIdx);
                        currIdx = rightIdx;
                        continue;
                    }
                } else {
                    //无需继续下沉
                    break;
                }
            } else if (leftIdx <= lastIdx && lastIdx < rightIdx) {
                //只有左节点有值，右节点无值
                int leftVal = ary[leftIdx];
                if (currVal < leftVal) {
                    swap(currIdx, leftIdx);
                    break;
                }
            } else if (lastIdx < leftIdx) {
                //两个左右节点都无值，无序操作，直接返回，跳出
                break;
            }
        }

        return currMax;
    }

    private void swap(int idxA, int idxB) {
        int t = ary[idxA];
        ary[idxA] = ary[idxB];
        ary[idxB] = t;
    }

    //堆化一个数组（原地）
    void heapify(int[] ary) {

    }

}
