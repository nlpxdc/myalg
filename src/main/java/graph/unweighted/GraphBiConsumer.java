package graph.unweighted;

//@FunctionalInterface
public interface GraphBiConsumer<T, U> {

    void accept(T t, U u);

}
