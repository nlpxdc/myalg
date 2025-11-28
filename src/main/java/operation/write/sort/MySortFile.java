package operation.write.sort;

//O(nlogn)
//纯比较排序！肯定都带比较，是否还有其他？如基数O(n) 空间换了时间
//稳态分布 加上随机函数，或者斐波那契？ 不是单纯二分？
//关心最差，最差情况的最好算法下界算法
//交换排序？
//“交换排序”并不是国际教材里的一级大类，只是**“基于元素交换”**的通俗说法。
//        公认的五大宏观范式（taxonomies）如下，按“主要破坏逆序的手段”划分：
//
//        1. **Comparison-based**
//        - 插入类 Insertion-Sort, Shell-Sort
//        - 选择类 Selection-Sort, Heap-Sort
//        - 分治类 Merge-Sort, Quick-Sort
//        - 归并 + 选择混合 Tim-Sort
//
//        2. **Exchange / Bubble**（常被并入 1，不算独立大类）
//        Bubble-Sort, Cocktail-Shaker, Odd-Even-Sort
//
//        3. **Distribution / Bucket**
//        - 桶排 Bucket-Sort
//        - 基数排 Radix-Sort（LSD/MSD）
//        - 计数排 Counting-Sort
//
//        4. **Tree / Tournament**
//        - 锦标赛树排序
//        - 线段树排序
//
//        5. **External / Multi-way Merge**
//        - k-路归并
//        - 置换-选择 + 败者树（Replacement-Selection + Loser-Tree）
//
//        所以除了“交换”这一动作，真正的一级范式是：
//        **插入、选择、分治、桶/分布、外部归并**

//比较排序（comparison-based sorting）的**时间复杂度下界**是：
//
//        > **Ω(n log n)**
//
//        ### 含义
//        在最坏情况下，任何只通过**元素间两两比较**来获取顺序信息的算法，都必须做 **Ω(n log n)** 次比较，因此总时间不能低于这个量级。
//
//        ### 简要推导（信息论论证）
//        - n 个互异元素有 **n!** 种可能的排列（即决策树叶节点）。
//        - 一棵二叉决策树要区分 **n!** 种结果，树高 **h** 必须满足：
//        2^h ≥ n!
//        - 取对数：
//        h ≥ log₂(n!) = Θ(n log n)（斯特林公式）
//        - 所以**最坏情况比较次数** ≥ **Ω(n log n)**。
//
//        ### 结论
//        - 归并排序、堆排序等 **O(n log n)** 算法已是**渐进最优**。
//        - 线性时间排序（计数、基数、桶排）之所以能达到 **O(n)**，是因为它们**利用了键的分布或位表示**，不属于纯比较排序，因而打破了这个下界。

class MySortApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}
