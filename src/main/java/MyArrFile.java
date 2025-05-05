import java.util.Arrays;
import java.util.List;

class MyArrApp {
    public static void main(String[] args) {
//        MyArrList myArrList = new MyArrList();
//        myArrList.addEl(new MyArrNode(5));
//        myArrList.addEl(new MyArrNode(3));
//        myArrList.addEl(new MyArrNode(18));
//
//        myArrList.traverse();
//
//        MyArrNode[] myArrNodes = myArrList.batchGetByIdxList(new int[]{0, 2});
//        myArrList.traverse();

        int[] upList = {1,2,2,3,3,5,5,7};
        int[] downList = {2,4,5,5,6,8,8};
        int[] mergeList = MyArrUtil.mergeSort(upList, downList);

    }
}

class MyArrNode {
    int val;
    //idx? no can

    MyArrNode(int val) {
        this.val = val;
    }
}

class MyArrUtil {
    static int[] mergeSort(int[] upList, int[] downList) {
        int[] ret =new int[upList.length+downList.length];
        for (int i = 0, idxUp = 0, idxDown = 0; i < ret.length; i++) {
            if (idxUp >= upList.length && idxDown < downList.length) {
                ret[i] = downList[idxDown++];
                continue;
            }
            if (idxDown >= downList.length && idxUp < upList.length) {
                ret[i] = upList[idxUp++];
                continue;
            }
            if (upList[idxUp] == downList[idxDown]) {
                ret[i++] = upList[idxUp++];
                ret[i] = downList[idxDown++];
            } else if (upList[idxUp] < downList[idxDown]) {
                ret[i] = upList[idxUp++];
            } else {
                ret[i] = downList[idxDown++];
            }
        }
        return ret;
    }
}

class MyArrList {
    MyArrNode[] nodeAry;
    int size;

    MyArrList() {
        nodeAry = new MyArrNode[10000];
        size = 0;
    }

    void addEl(int idx, int val) {
        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx > size) {
            throw new RuntimeException("idx不能大于size");
        }
        for (int i = size-1; idx <= i; i--) {
            if (nodeAry[i+1] == null) {
                nodeAry[i+1] = new MyArrNode(nodeAry[i].val);
            } else {
                nodeAry[i+1].val = nodeAry[i].val;
            }
        }
        if (nodeAry[idx] == null) {
            nodeAry[idx] = new MyArrNode(val);
        } else {
            nodeAry[idx].val = val;
        }
        size++;
    }

    void addEl(MyArrNode el) {
        nodeAry[size++] = el;
    }

    void removeEl() {
        nodeAry[--size] = null;
    }

    void removeByIdx(int idx) {
        if (idx < 0) {
            throw new RuntimeException("idx不能是负数");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        nodeAry[idx] = null;
        for (int i = idx; i < size - 1 ; i++) {
            nodeAry[i] = nodeAry[i+1];
        }
        size--;
    }

    MyArrNode getByIdx(int idx) {
        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        return nodeAry[idx];
    }

    MyArrNode[] batchGetByIdxList(int[] idxList) {
        if (Arrays.stream(idxList).distinct().toArray().length != idxList.length) {
            throw new RuntimeException("idx不能有重复的值");
        }
        if (Arrays.stream(idxList).anyMatch(t -> t < 0 || t >= size)) {
            throw new RuntimeException("idx不能小于0或者大于等于size");
        }
        if (idxList.length > size) {
            throw new RuntimeException("idxList数量不能大于size");
        }
        MyArrNode[] ret = new MyArrNode[idxList.length];

        //1. 循环处理，调用单个getByIdx
        for (int i = 0; i < idxList.length; i++) {
            int idx = idxList[i];
            MyArrNode node = getByIdx(idx);
            ret[i] = node;
        }
        return ret;
    }

    int idxOfVal(int val) {
        int idx = -1;
        for (int i = 0; i < size; i++) {
            if (nodeAry[i].val == val) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    void removeByVal(int val) {
        //循环迭代便利找到val的idx
        int idx = idxOfVal(val);
        //删除idx
        removeByIdx(idx);
    }

    void reverse() {
        for (int i = 0, j = size-1; i < size/2 && j >= 0 && i < j; i++, j--) {
            int t = nodeAry[i].val;
            nodeAry[i].val = nodeAry[j].val;
            nodeAry[j].val = t;
        }
    }

    void updateValByIdx(int idx, int newVal) {
        if (idx < 0) {
            throw new RuntimeException("idx不能小于0");
        }
        if (idx >= size) {
            throw new RuntimeException("idx不能大于等于size");
        }
        nodeAry[idx].val = newVal;
    }

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
        System.out.println("---");
    }
}
