package graph.unweighted;

@FunctionalInterface
public interface TraverseMode<VParam, SingleTemp, AllTemp, SingleVo, AllVo> {

    void singleTraverse(VParam vParam, SingleTemp singleTemp, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
