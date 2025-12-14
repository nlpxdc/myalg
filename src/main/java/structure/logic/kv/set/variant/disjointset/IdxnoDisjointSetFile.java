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
    int[] parentAry;
    int[] heightAry;
    int[] sizeAry;

    IndxnoDisjointSet(int size) {
        this.size = size;
        parentAry = new int[size];
        heightAry = new int[size];
        sizeAry = new int[size];

        for (int i = 0; i < size; i++) {
            parentAry[i] = i;
            heightAry[i] = 1;
            sizeAry[i] = 1;
        }
    }

    @Override
    public int find(int no) {
        if (no == parentAry[no]) {
            //根节点
            return no;
        } else {
//            int noParentNo = find(pAry[no]);
//            return pAry[no] = noParentNo;
            //递归后，这里的值就是根节点
            return parentAry[no] = find(parentAry[no]);
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
            parentAry[root2] = root1;
        }
    }

    //按秩合并

    //按高度合并
    void union2(int no1, int no2) {
        int root1 = find(no1);
        int root2 = find(no2);
        if (heightAry[root1] < heightAry[root2]) {
            //root1高度小于root2高度，root1矮小，root1挂到root2下
            parentAry[root1] = root2;
        } else if (heightAry[root1] > heightAry[root2]) {
            //root1消毒大于root2，root1更高，root2挂到root1下
            parentAry[root2] = root1;
        } else {
            //root1高度和root2高度一样，随便怎么挂，或者再根据第二维度大小？不复杂了，随意挂，数学证明高度是logn，最终看高度
            parentAry[root2]  = root1;
            heightAry[root1]++;
        }
    }

    //按大小合并，个数
    void union3(int no1, int no2) {
        int root1 = find(no1);
        int root2 = find(no2);
        if (sizeAry[root1] < sizeAry[root2]) {
            //root1的数量小于root2的数量，root1挂到root2下
            parentAry[root1] = root2;
            sizeAry[root2] += sizeAry[root1];
        } else if (sizeAry[root1] > sizeAry[root2]) {
            //root1的数量大于root2的数量，root2挂到root1下
            parentAry[root2] = root1;
            sizeAry[root1] =+ sizeAry[root2];
        } else {
            //root1的数量和root2的数量一样，随便挂，或者再看height？
            parentAry[root2] = root1;
            sizeAry[root1] =+ sizeAry[root2];
        }
    }
}
