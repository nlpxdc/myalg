package structure.logic.vertexedge.valuecalculate.tree.common.kwaytree;

class UpTreeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }
}

//非递归非嵌套定义，元信息定义，元信息的递归嵌套或者说迭代
//以节点为核心进行思考，通过读扩散构建出树结构
//多字段标识，要保持一致性，操作原子性
class UpNode {
//    int id;
//    int parentId;
    int val;
//    int idx; ? redundant
    int parentIdx;

//    int[] childrenIdAry;
//    int[] childrenIdxAry;

    //depth or height? yes
    //root? yes calc by depth
    //leaf? yes calc by childrenIdxAry no! mark! tag! consistent!
    //has children? calc by childrenIdxAry consistent! tag!
    //has children? calc by read all records consistent! this primary tag!
    //如果不一致，以哪个为准？以大的metadata为准
    //has parent? parentIdx parentId
    //middleNode? no

    UpNode(int val) {
        this.val = val;
    }
}

//身在其外，上帝视角，第三视角
//线性，可数组可链表
class UpTree {
    //适合大量部分的外存，磁盘等
    //不定长叉树，可以限定
    //一个线性结构即可确定 最关键
    //maxDepth? no can, yes better!
    //define with static idx 树的size
//    UpAryNode[] nodeAry; 当中可能空浪费
//    List<UpAryNode> nodeList; 不合适
    //define with dynamic node ary 节点总的size
//    UpAryNode[] nodeAry; 不浪费 操作写入麻烦
//    List<UpAryNode> nodeList; 合适
}
