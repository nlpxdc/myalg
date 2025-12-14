package structure.logic.kv.set.variant.disjointset;

//是集合，单纯集合，只有元素没有边的图 空图？连通分量没有or最大？
//也是图，无向无权图，只讲连通关系，等价关系，一类关系，根据此优化存储结构，映射，切面
//也是森林，树，连通无环，多个连通分量，根据此优化存储结构
//集合本身就和hash，tree有关系，但是hash不讲团，不讲区间，但是树讲
//集合不讲顺序，但是为了不讲顺序，需要先有散列概念或者顺序概念，又要有区间区段概念，就只能是树 堆？（优先队列）
class IdxnoDisjointSetApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class IndxnoDisjointSet implements DisjointSetAdt {

    int size;
    //下标idxno充当no序号
    int[] pAry;

    IndxnoDisjointSet(int size) {
        this.size = size;
        pAry = new int[size];

        for (int i = 0; i < size; i++) {
            pAry[i] = i;
        }
    }

    @Override
    public int find(int no) {
        if (no == pAry[no]) {
            //根节点
            return no;
        } else {
//            int noParentNo = find(pAry[no]);
//            return pAry[no] = noParentNo;
            //递归后，这里的值就是根节点
            return pAry[no] = find(pAry[no]);
        }
    }

    @Override
    public void union(int no1, int no2) {
        union1(no1, no2);
    }

    //随意合并，不考虑其他任何情况，但是树高可能高，影响效率
    void union1(int no1, int no2) {
        int root1 = find(no1);
        int root2 = find(no2);
        if (root1 != root2) {
            pAry[root2] = root1;
        }
    }
}
