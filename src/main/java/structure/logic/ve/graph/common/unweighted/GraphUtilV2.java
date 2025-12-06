package structure.logic.ve.graph.common.unweighted;

import structure.logic.ve.graph.common.unweighted.directed.ArcParam;
import structure.logic.ve.graph.common.unweighted.directed.ArcParamV2;
import structure.logic.ve.graph.common.unweighted.undirected.EdgeParam;
import structure.logic.ve.graph.common.unweighted.undirected.EdgeParamV2;

public class GraphUtilV2 {

    //无向边要判重，入参可带AllVo
    public static void bfsVisitEdge(EdgeParamV2 edgeParam, SingleVoV2 singleVo) {
        if (!singleVo.bfsEdgeList.contains(edgeParam)) {
            singleVo.bfsEdgeList.add(edgeParam);
            System.out.println(edgeParam);
        }
    }
    //无向边要判重，入参可带AllVo
    public static void dfsVisitEdge(EdgeParamV2 edgeParam, SingleVoV2 singleVo) {
        if (!singleVo.dfsEdgeList.contains(edgeParam)) {
            singleVo.dfsEdgeList.add(edgeParam);
            System.out.println(edgeParam);
        }
    }
    //有向边可以不判，因为理论上不会重复，极致性能去掉判重，入参可带AllVo
    public static void bfsVisitArc(ArcParamV2 arcParam, SingleVoV2 singleVo) {
        if (!singleVo.bfsArcList.contains(arcParam)) {
            singleVo.bfsArcList.add(arcParam);
            System.out.println(arcParam);
        }
    }
    //有向边可以不判，因为理论上不会重复，极致性能去掉判重，入参可带AllVo
    public static void dfsVisitArc(ArcParamV2 arcParam, SingleVoV2 singleVo) {
        if (!singleVo.dfsArcList.contains(arcParam)) {
            singleVo.dfsArcList.add(arcParam);
            System.out.println(arcParam);
        }
    }
    public static void bfsVisitV(final VParamV2 vParam,
                                 final SingleTemp singleTemp,
                                 final AllTempV2 allTemp,
                                 final SingleVoV2 singleVo,
                                 final AllVoV2 allVo) {
        singleVo.bfsVList.add(vParam);
    }
    public static void dfsDiscoverV(final VParamV2 vParam,
                                    final SingleTemp singleTemp,
                                    final AllTempV2 allTemp,
                                    final SingleVoV2 singleVo,
                                    final AllVoV2 allVo) {
        singleVo.dfsVDiscoverList.add(vParam.v);
        singleVo.dfsVDiscoverFinishList.add(String.format("D%d", vParam.v));
        VDfsDoV2 vDfsDo = singleVo.dfsVVDfsDoMap.getOrDefault(vParam.v, new VDfsDoV2(vParam.parent, vParam.v));
        singleVo.dfsVVDfsDoMap.put(vParam.v, vDfsDo);
//        vDfsDo.discoverTime = System.nanoTime() - singleVo.dfsVStartNanoTime;
        vDfsDo.discoverTimeNo = ++allTemp.allDfsVTimeNo;
    }
    public static void dfsFinishV(final VParamV2 vParam,
                                  final SingleTemp singleTemp,
                                  final AllTempV2 allTemp,
                                  final SingleVoV2 singleVo,
                                  final AllVoV2 allVo) {
        singleVo.dfsVFinishList.add(vParam.v);
        singleVo.dfsVDiscoverFinishList.add(String.format("F%d", vParam.v));
        VDfsDoV2 vDfsDo = singleVo.dfsVVDfsDoMap.getOrDefault(vParam.v, new VDfsDoV2(vParam.parent, vParam.v));
        singleVo.dfsVVDfsDoMap.put(vParam.v, vDfsDo);
//        vDfsDo.finishTime = System.nanoTime() - singleVo.dfsVStartNanoTime;
        vDfsDo.finishTimeNo = ++allTemp.allDfsVTimeNo;
    }

//    public static Integer getFirstUnVisited(boolean[] visited) {
//        for (int i = 0; i < visited.length; i++) {
//            if (!visited[i]) {
//                return i;
//            }
//        }
//        return null;
//    }

    //这里的n是graph的metainfo，且必须是final的不能修改，可以改成单一对象，对象字段也是final的不能改变，因为都是元信息
    //但是这里因为目前只需要一个图的顶点总数即可，所以不额外定义对象了，如果两个或多于两个元信息，那么可以额外定义对象，这里不做复杂处理了
    //第二个参数，是遍历方式，是函数是方法，遍历某个点开始的连通图内的所有节点，函数的参数都是单个连通图遍历所需的数据
    //函数的第一个值，是真正的参数，是一个单连通图的起始点startV，这里可以是任意的，和上面同理，也可以是个对象，如果需要多个字段时，这里简单处理
    //另外一个值就是临时变量，放两个重要内容，一个是记录节点的被访问状态数组，另一个是顶点被访问的总次数，这里因为有两个需要记录的变脸，所以定义对象了
    //但是如果只给被访问的数组也是可以的够的，但是给出visitCnt会更好，这是针对元数据n的临时变量，可以控制总体循环逻辑，避免写错进入无限死循环
    //上面不要对象了，visitCnt其实是个计算属性，根据visited数组可以算出来的

    //这里的n可以是图的元信息metadata 这里BiConsumer不用Func或BiFunc，因为需要原地，bfs可以函数是但是dfs递归不可以，需要原地
    //除非改造dfs为stack版本，这里分两种参数，第一参数是输入信息真实参数，第二参数起始是返回结果，也可认为是临时数据，原地处理，所以作为入参传入
    //如果有必要都可转换成对象，起始一个Func也可以，但是区分开来Param和vo更好，有区别
    public static void allTraverse(final GraphMetaV2 graphMeta, final TraverseMode<VParamV2, SingleTemp, AllTempV2, SingleVoV2, AllVoV2> traverseMode, AllVoV2 allVo) {
        AllTempV2 allTemp = new AllTempV2(graphMeta);

        int maxConnectedChildGraphCnt = 0;
        for (char v = 'A'; v < graphMeta.n; v++) {
//        for (Character v = 'A'; v < graphMeta.n; v++) {
//            Integer firstUnVisited = getFirstUnVisited(allTemp.visited);
            if (!allTemp.visited[v]) {
                VParamV2 singleStartVParam = new VParamV2(null, v);
                maxConnectedChildGraphCnt++;
                SingleVoV2 singleVo = new SingleVoV2();
                SingleTemp singleTemp = new SingleTemp();
                traverseMode.singleTraverse(singleStartVParam, singleTemp, allTemp, singleVo, allVo);
                allVo.directedList.add(singleVo.directed);
                allVo.undirectedCyclicList.add(singleVo.undigraphBeCyclic());
                allVo.directedCyclicList.add(singleVo.digraphBeCyclic());
                allVo.bfsListList.add(singleVo.bfsVList);
                allVo.dfsDiscoverListList.add(singleVo.dfsVDiscoverList);
                allVo.dfsFinishListList.add(singleVo.dfsVFinishList);
                allVo.dfsDiscoverFinishListList.add(singleVo.dfsVDiscoverFinishList);
                allVo.dfsVDfsDoMapList.add(singleVo.dfsVVDfsDoMap);
            }
        }
        allVo.childGraphCnt = maxConnectedChildGraphCnt;
    }

}
