package tree.binarytree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class AryTreeV2App {
    public static void main(String[] args) {
        int[] ary = {11,21,22,31,32,33,34,41,42,43,44,45,46,47,48};
        List<Integer> aryList = Arrays.stream(ary).boxed().collect(Collectors.toList());
        PriorityQueue minHeap = new PriorityQueue<>(aryList);
        System.out.println(minHeap);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        boolean b = maxHeap.addAll(aryList);
        System.out.println(maxHeap);

        CompleteBiTree completeBiTree = new CompleteBiTree(ary);
//        completeBiTree.traverseBfsLeveledSingleLine();
        completeBiTree.traverseBfsLeveledMultiLine();
        int height = completeBiTree.getHeight();

        int treeNodeIdx = 6;
        int val = completeBiTree.getTreeNodeValByTreeNodeIdx(treeNodeIdx);
        int leftChildVal = completeBiTree.getTreeNodeValByTreeNodeIdx(CbtUtil.getLeftChildIdxByTreeNodeIdx(treeNodeIdx));
        int rightChildVal = completeBiTree.getTreeNodeValByTreeNodeIdx(CbtUtil.getRightChildIdxByTreeNodeIdx(treeNodeIdx));
        int parentVal = completeBiTree.getTreeNodeValByTreeNodeIdx(CbtUtil.getParentIdxByTreeNodeIdx(treeNodeIdx));
    }
}

//没有Node定义，也不用装箱类型，就使用基本数据类型int

class CompleteBiTree {
    int[] ary;
    int size;

    public CompleteBiTree() {
        ary = new int[1000];
        size = 0;
    }

    public CompleteBiTree(int[] ary) {
        if (ary.length > 1000) {
            throw new RuntimeException("too large");
        }
        this.ary = new int[1000];
        System.arraycopy(ary, 0, this.ary, 0, ary.length);
        this.size = ary.length;
    }

    void traverseBfsLeveledSingleLine() {
        for (int i = 0; i < size; i++) {
            System.out.print(ary[i]+",");
        }
        System.out.println();
    }

    void traverseBfsLeveledMultiLine() {
        for (int h = 0; h <= getHeight(); h++) {
            for (int idx = (CbtUtil.pow2(h)-1); idx <= (CbtUtil.pow2(h+1)-2) && idx < size ; idx++) {
                System.out.print(ary[idx]+",");
            }
            System.out.println();
        }
        System.out.println();
    }

    int getTreeNodeValByTreeNodeIdx(int treeNodeIdx) {
        return ary[treeNodeIdx];
    }

    int getHeight() {
        return  (int) (Math.log(size) / Math.log(2));
    }

    int getRootTreeNodeIdx() {
        return 0;
    }

    int getLastTreeNodeIdx() {
        return size-1;
    }

    void siftDownOfMaxHeap(int treeNodeIdx) {
        int currentIdx = treeNodeIdx;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //先找到自己的左右子节点
            int leftIdx = CbtUtil.getLeftChildIdxByTreeNodeIdx(currentIdx);
            int rightIdx = CbtUtil.getRightChildIdxByTreeNodeIdx(currentIdx);
            //再判断下左右子节点是否存在，即是否小于size边界
            boolean hasLeft = leftIdx < size;
            boolean hasRight = rightIdx < size;
            //找出左右子节点更大的那一个
            if (!hasRight && !hasLeft) {
                return;
            } else if (hasRight && !hasLeft) {
                throw new RuntimeException("根据定义，不可能");
            } else if (!hasRight && hasLeft) {
                int leftVal = getTreeNodeValByTreeNodeIdx(leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(currentIdx);
                if (currentVal < leftVal) {
                    CbtUtil.swap(ary, currentIdx, leftIdx);
                    currentIdx = leftIdx;
                    continue;
                }
            } else if (hasRight && hasLeft) {
                int rightVal = getTreeNodeValByTreeNodeIdx(rightIdx);
                int leftVal = getTreeNodeValByTreeNodeIdx(leftIdx);
                int currentVal = getTreeNodeValByTreeNodeIdx(currentIdx);
                if (rightVal > leftVal) {
                    //右节点大，与右节点比较交换
                    if (currentVal < rightVal) {
                        CbtUtil.swap(ary, currentIdx, rightIdx);
                        currentIdx = rightIdx;
                        continue;
                    }
                } else {
                    //反之，左节点大，与左节点比较交换
                    if (currentVal < leftVal) {
                        CbtUtil.swap(ary, currentIdx, leftIdx);
                        currentIdx = leftIdx;
                        continue;
                    }
                }
            }
        }
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
}

