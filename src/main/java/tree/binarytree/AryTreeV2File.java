package tree.binarytree;

class AryTreeV2App {
    public static void main(String[] args) {
        int[] ary = {11,21,22,31,32,33,34,41,42,43,44,45,46,47,48};
        CompleteBiTree completeBiTree = new CompleteBiTree(ary);
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

}

