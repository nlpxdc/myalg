package graph.unweighted;

//@FunctionalInterface
public interface GraphFunc<GraphMeta, SingleStartParam, GraphTempVo, SingleVo> {

    SingleVo accept(GraphMeta graphMeta, SingleStartParam singleStartParam, GraphTempVo graphTempVo);

}
