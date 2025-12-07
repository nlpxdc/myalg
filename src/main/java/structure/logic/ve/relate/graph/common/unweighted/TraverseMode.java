package structure.logic.ve.relate.graph.common.unweighted;

@FunctionalInterface
public interface TraverseMode<VParam, SingleTemp, AllTemp, SingleVo, AllVo> {

    void singleTraverse(VParam vParam, SingleTemp singleTemp, AllTemp allTemp, SingleVo singleVo, AllVo allVo);

}
