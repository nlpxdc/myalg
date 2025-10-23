package graph.unweighted;

@FunctionalInterface
public interface TraverseMode<SingleStartParam, SingleTemp, AllTemp, SingleVo, AllVo> {

//    SingleVo accept(SingleStartParam singleStartParam, GraphTempVo graphTempVo);
    void singleTraverse(SingleStartParam singleStartParam, SingleTemp singleTemp, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
