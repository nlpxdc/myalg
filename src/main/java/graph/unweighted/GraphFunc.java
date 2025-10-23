package graph.unweighted;

@FunctionalInterface
public interface GraphFunc<SingleStartParam, GraphTempVo, SingleVo> {

//    SingleVo accept(SingleStartParam singleStartParam, GraphTempVo graphTempVo);
    void singleTraverse(SingleStartParam singleStartParam, GraphTempVo graphTempVo, SingleVo singleVo);

}
