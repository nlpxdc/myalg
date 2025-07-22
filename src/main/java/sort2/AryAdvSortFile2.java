package sort2;

import java.util.Arrays;

class AryAdvSortApp {
    public static void main(String[] args) {
        int[] ary = {8, 4, 5, 3, 5};
        heapSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    private static void swap(int[] ary, int leftIdx, int rightIdx) {
        if (leftIdx != rightIdx) {
            int t = ary[leftIdx];
            ary[leftIdx] = ary[rightIdx];
            ary[rightIdx] = t;
            //t = 0; 针对对象，复杂对象，设置称null，利用gc清空内存，主动释放
        }
    }

    public static void heapSort(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            heapify(ary, ary.length-i);
            CbtUtil.swap(ary, 0, ary.length-1-i);
        }
    }

    public static void heapify(int[] ary, int size) {
        for (int i = CbtUtil.getParentIdxByTreeNodeIdx(ary.length-1); i >=0; i--) {
            siftDownOfMaxHeap(ary, i, size);
        }
    }

    static void siftDownOfMaxHeap(int[] ary, int treeNodeIdx, int size) {
        int currentIdx = treeNodeIdx;
        for (int i = 0; i <= (CbtUtil.getTreeHeight(size) - CbtUtil.getTreeNodeHeightByTreeNodeIdx(treeNodeIdx)); i++) {
            int leftIdx = CbtUtil.getLeftChildIdxByTreeNodeIdx(currentIdx);
            int rightIdx = CbtUtil.getRightChildIdxByTreeNodeIdx(currentIdx);
            boolean hasLeft = leftIdx < size;
            boolean hasRight = rightIdx < size;
            if (!hasRight && !hasLeft) {
                return;
            } else if (hasRight && !hasLeft) {
                throw new RuntimeException("根据定义，不可能");
            } else if (!hasRight && hasLeft) {
                int leftVal = getTreeNodeValByTreeNodeIdx(ary, leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(ary, currentIdx);
                if (currentVal < leftVal) {
                    CbtUtil.swap(ary, currentIdx, leftIdx);
                    currentIdx = leftIdx;
                    continue;
                }
            } else if (hasRight && hasLeft) {
                int rightVal = getTreeNodeValByTreeNodeIdx(ary, rightIdx);
                int leftVal = getTreeNodeValByTreeNodeIdx(ary, leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(ary, currentIdx);
                if (rightVal > leftVal) {
                    if (currentVal < rightVal) {
                        CbtUtil.swap(ary, currentIdx, rightIdx);
                        currentIdx = rightIdx;
                        continue;
                    }
                } else {
                    if (currentVal < leftVal) {
                        CbtUtil.swap(ary, currentIdx, leftIdx);
                        currentIdx = leftIdx;
                        continue;
                    }
                }
            }
        }
    }

    static int getTreeNodeValByTreeNodeIdx(int[] ary, int treeNodeIdx) {
        return ary[treeNodeIdx];
    }

}

class CbtUtil {
    static void swap(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    static int pow2(int n) {
        return 1 << n;
    }

    static int getParentIdxByTreeNodeIdx(int treeNodeIdx) {
        return (treeNodeIdx-1)/2;
    }

    static int getLeftChildIdxByTreeNodeIdx(int treeNodeIdx) {
        return 2*treeNodeIdx+1;
    }

    static int getRightChildIdxByTreeNodeIdx(int treeNodeIdx) {
        return 2*treeNodeIdx+2;
    }

    static int getTreeHeight(int size) {
        return  ((int) (Math.log(size) / Math.log(2)));
    }

    static int getTreeNodeHeightByTreeNodeIdx(int treeNodeIdx) {
        return  ((int) (Math.log(treeNodeIdx+1) / Math.log(2)));
    }

}
