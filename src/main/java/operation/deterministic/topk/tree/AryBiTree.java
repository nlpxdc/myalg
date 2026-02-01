package operation.deterministic.topk.tree;

public class AryBiTree {
    int[] ary;

    int getRootIdx(){
        return 0;
    }

    int getRootVal() {
        return ary[0];
    }

    int getNodeIdx(int no) {
        return no;
    }

    int getNodeVal(int no) {
        return ary[getNodeIdx(no)];
    }

    int getLeftChildIdx(int no) {
        return 2*no+1;
    }

    int getLeftChildVal(int no) {
        return ary[getLeftChildIdx(no)];
    }

    int getRightChildIdx(int no) {
        return 2*no+2;
    }

    int getRightChildVal(int no) {
        return ary[getRightChildIdx(no)];
    }
}
