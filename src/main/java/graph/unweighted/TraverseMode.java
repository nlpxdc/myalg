package graph.unweighted;

@FunctionalInterface
public interface TraverseMode<SingleStartParam, SingleTemp, AllTemp, SingleVo, AllVo> {

    void singleTraverse(SingleStartParam singleStartParam, SingleTemp singleTemp, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
