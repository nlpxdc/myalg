package graph;

import java.util.Set;

//第0视角，非第一自己第三全局视角
//适合按顺序批次流式的初始化init E是V²即VxV的子集
//没法用一维数组来表示，树能用，因为完全树可以描述全貌，而图没办法，必须用二维，所以只有邻接矩阵
//第一自己视角只能看见自己的维度，一维，没法看到二维高维
//没有以身入局，虽然全局，但是按照组合的一维来处理，没有根据两个节点降维处理，索引处理，所以一定会在各种情况下变难变慢，除非按照边排序有优势，否则完全没有邻接的优势
class VeGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class WeightedNode2 {
    int key;
    int nodeWeight;
}

class WeightedEdge2 {
    WeightedNode2 from;
    WeightedNode2 to;
    int edgeWeight;
}

//没有互相嵌套，就是正常定义，抽象定义

class VeGraph {
    Set<WeightedNode2> weightNodeSet;
    Set<WeightedEdge2> weightedEdgeSet;
}
