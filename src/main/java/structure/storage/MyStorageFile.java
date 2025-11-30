package structure.storage;

//确定性 偶然性
//本质上就是地址，地址编号。indexno显示指定，ref是隐式指定，安全的不可计算的，但是可以被重新绑定。pointer是不安全的可计算的，灵活的
//一旦地址指向可计算，也就是用pointer，那么随机情况的reference也有用处了，也就是再c++里可以存在，但在java里不存在
//indexno 和 ref，是权衡，是动态，可以综合组合配合，本身存储线性，逻辑天然线性。其他逻辑结构都在这个线性存储结构模式下玩出花来
//indexno 查块 写慢
//ref 查慢 写块
//InnerMem要管
//OuterDisk也要管
//适用的不同场景算法数据结构，例如树 胜者树 败者树（OuterDisk） 内外结合？排序时
//局部缓存行原理 时间局部近 空间局部近 预取等，都是图灵机理论计算机模型，冯诺依曼体系结构，工程实现造成的局部性原理，深入理解计算机系统内容
class MyStorageApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}
