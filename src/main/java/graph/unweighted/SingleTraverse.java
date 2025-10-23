package graph.unweighted;

@FunctionalInterface
public interface SingleTraverse<SingleStartParam, SingleTemp, AllTemp, SingleVo, AllVo> {

//    SingleVo accept(SingleStartParam singleStartParam, GraphTempVo graphTempVo);
    void callSingleTraverse(SingleStartParam singleStartParam, SingleTemp singleTemp, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
