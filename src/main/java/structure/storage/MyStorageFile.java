package structure.storage;

//indexno 和 ref，是权衡，是动态，可以综合组合配合，本身存储线性，逻辑天然线性。其他逻辑结构都在这个线性存储结构模式下玩出花来
//indexno 查块 写慢
//ref 查慢 写块
//InnerMem要管
//OuterDisk也要管
//适用的不同场景算法数据结构，例如树 胜者树 败者树（OuterDisk） 内外结合？排序时
class MyStorageApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}
