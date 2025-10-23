package graph.unweighted;

@FunctionalInterface
public interface GraphFunc<SingleStartParam, AllTemp, SingleVo, AllVo> {

//    SingleVo accept(SingleStartParam singleStartParam, GraphTempVo graphTempVo);
    void singleTraverse(SingleStartParam singleStartParam, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
