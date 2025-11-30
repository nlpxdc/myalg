package structure.logic.ve.graph.common.unweighted.directed;

import java.util.LinkedList;
import java.util.List;

public class AllTopoOrderByDfsVo {
    public boolean beCyclic;
    public List<Integer> topoList;

    public AllTopoOrderByDfsVo() {
        this.beCyclic = false;
        this.topoList = new LinkedList<>();
    }

}
