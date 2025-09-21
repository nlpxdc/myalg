package graph.unweighted;

public class GraphUtil {
    public static void bfsVisit(VParam vParam, SingleVo singleVo) {
        singleVo.bfsList.add(vParam);
    }
    public static void dfsDiscover(VParam vParam, SingleVo singleVo) {
        singleVo.dfsDiscoverList.add(vParam.v);
        singleVo.dfsDiscoverFinishList.add(String.format("D%d", vParam.v));
    }
    public static void dfsFinish(VParam vParam, SingleVo singleVo) {
        singleVo.dfsFinishList.add(vParam.v);
        singleVo.dfsDiscoverFinishList.add(String.format("F%d", vParam.v));
    }

    public static Integer getFirstUnVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return null;
    }

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
    public static AllVo traverse(final GraphMeta graphMeta, final GraphFunc<GraphMeta, SingleStartParam, AllTemp, SingleVo> maxConnectedChildGraphTraverse) {
        AllTemp allTemp = new AllTemp(graphMeta);

        AllVo allVo = new AllVo();
        int maxConnectedChildGraphCnt = 0;
        for (int i = 0; i < graphMeta.n; i++) {
            Integer firstUnVisited = getFirstUnVisited(allTemp.visited);
            SingleStartParam singleStartParam = new SingleStartParam(firstUnVisited);
            if (firstUnVisited != null) {
                maxConnectedChildGraphCnt++;
                SingleVo singleVo = maxConnectedChildGraphTraverse.accept(graphMeta, singleStartParam, allTemp);
                allVo.bfsListList.add(singleVo.bfsList);
                allVo.dfsDiscoverListList.add(singleVo.dfsDiscoverList);
                allVo.dfsFinishListList.add(singleVo.dfsFinishList);
                allVo.dfsDiscoverFinishListList.add(singleVo.dfsDiscoverFinishList);
            } else {
                break;
            }

        }
        allVo.childGraphCnt = maxConnectedChildGraphCnt;
        return allVo;
    }

}
