package graph;

import java.util.Set;

//第0视角，非第一自己第三全局视角
//适合按顺序批次流式的初始化init E是V²即VxV的子集
//没法用一维数组来表示，树能用，因为完全树可以描述全貌，而图没办法，必须用二维，所以只有邻接矩阵
//第一自己视角只能看见自己的维度，一维，没法看到二维高维
//没有以身入局，虽然全局，但是按照组合的一维来处理，没有根据两个节点降维处理，索引处理，所以一定会在各种情况下变难变慢，除非按照边排序有优势，否则完全没有邻接的优势
//这个也没有二维，而是两个一维的简单组合，定义为之，点归点，边归边，没有任何技巧，只有朴素，如果连通边权问题，如果顶权也加入，各自处理各自的，虽然有关系，单这里妹描述出来，需要额外依赖计算出来，计算属性，然后在这个基础上继续
//所以这个其实已经在邻接表示法中表现出来了，最重要的就是邻边的表示，邻点的表示，所以这里一维，没有真正二维，而是两个一维度的简单组合
//其实也可以用两个一维的数组，也是可以的
class VeGraphApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

class WeightedNode2 {
    int nodeKey;
    int nodeWeight;
}

//也可以命名Edge，如果无向图
class WeightedArc2 {
    //其实也可以给编号，但是这个编号可以是计算属性，依赖节点from to计算出来即可，是个组合属性
//    int arcKey; //计算属性，nodeKey组合，无向图不讲顺序有一边即可，有向图讲顺序要有两弧
    int arcWeight;
    WeightedNode2 from;
    WeightedNode2 to;
}

//没有互相嵌套，就是正常定义，抽象定义

class VeGraph {
    Set<WeightedNode2> weightNodeSet;
    Set<WeightedArc2> weightedArcSet;
}
