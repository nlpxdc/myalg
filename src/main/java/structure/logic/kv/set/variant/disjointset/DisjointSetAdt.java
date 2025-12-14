package structure.logic.kv.set.variant.disjointset;

//没有idx
public interface DisjointSetAdt {
    //no是序号，返回也是序号
    int find(int no);
    //no1,no2都是序号
    void union(int no1, int no2);
}
