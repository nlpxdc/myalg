package structure.logic;

//每种逻辑结构，都要考虑用indexno 和 ref
//indexno 3种 连续 hash 静态链表
//ref 包含单向 双向 meta的元标记单个 多个等
//同时给出每种实现下的时空复杂度，如果不全部写，至少思考下，思考全了，选最好的去写去实现
//处理元素可以不只是单纯传统的数字可算术计算，还包括向量（一维数组，字符串） 以及广义表（二维数组，字符串数组，甚至更高）
//线性 树 图 ，元素的邻接关系划分
//map 元素的关联关系划分，所以独立
//map -> set 也独立
//最终落到 线性 树 图这些逻辑结构上，借助他们，最后最后 落到存储结构上 indexno ref
//第一维度 indexno(显式) vs ref（隐式）
//第二维度 deterministic vs stochastic（随机空间 hash（只散列固定） 随机时间随机过程random含(t 不固定（逻辑t和现实t）和种子seed熵防猜测防碰撞)）
//随机冲突后，多次hash或多次random解决，配合其他方法
class MyLogicApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}
