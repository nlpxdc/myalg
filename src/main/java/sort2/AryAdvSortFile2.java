package sort2;

import java.util.Arrays;

class AryAdvSortApp {
    public static void main(String[] args) {
        int[] ary = {8, 4, 5, 3, 5};
        funcA(ary);
        System.out.println(Arrays.toString(ary));
    }

    public static void funcA(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            funcB(ary, ary.length-i);
            funcD(ary, 0, ary.length-1-i);
        }
    }

    public static void funcB(int[] ary, int size) {
        for (int i = funcF(ary.length-1); i >=0; i--) {
            funcE(ary, i, size);
        }
    }

    static void funcE(int[] ary, int treeNodeIdx, int size) {
        int currentIdx = treeNodeIdx;
        for (int i = 0; i <= (funcH(size) - funcG(treeNodeIdx)); i++) {
            int leftIdx = funcJ(currentIdx);
            int rightIdx = funcK(currentIdx);
            boolean hasLeft = leftIdx < size;
            boolean hasRight = rightIdx < size;
            if (!hasRight && !hasLeft) {
                return;
            } else if (hasRight && !hasLeft) {
                throw new RuntimeException("根据定义，不可能");
            } else if (!hasRight && hasLeft) {
                int leftVal = funcL(ary, leftIdx);
                int currentVal = funcL(ary, currentIdx);
                if (currentVal < leftVal) {
                    funcD(ary, currentIdx, leftIdx);
                    currentIdx = leftIdx;
                    continue;
                }
            } else if (hasRight && hasLeft) {
                int rightVal = funcL(ary, rightIdx);
                int leftVal = funcL(ary, leftIdx);
                int currentVal = funcL(ary, currentIdx);
                if (rightVal > leftVal) {
                    if (currentVal < rightVal) {
                        funcD(ary, currentIdx, rightIdx);
                        currentIdx = rightIdx;
                        continue;
                    }
                } else {
                    if (currentVal < leftVal) {
                        funcD(ary, currentIdx, leftIdx);
                        currentIdx = leftIdx;
                        continue;
                    }
                }
            }
        }
    }

    static int funcL(int[] ary, int treeNodeIdx) {
        return ary[treeNodeIdx];
    }

    static void funcD(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    static int funcF(int treeNodeIdx) {
        return (treeNodeIdx-1)/2;
    }

    static int funcJ(int treeNodeIdx) {
        return 2*treeNodeIdx+1;
    }

    static int funcK(int treeNodeIdx) {
        return 2*treeNodeIdx+2;
    }

    static int funcH(int size) {
        return  ((int) (Math.log(size) / Math.log(2)));
    }

    static int funcG(int treeNodeIdx) {
        return  ((int) (Math.log(treeNodeIdx+1) / Math.log(2)));
    }

}

