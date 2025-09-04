package graph.unweighted.undirected;

public class GraphUtil {
    static void visit(int v) {
        System.out.print(v+",");
    }
//    static void discover(int v) {
//        System.out.println(String.format("discover %d", v));
//    }
//    static void finish(int v) {
//        System.out.println(String.format("finish %d", v));
//    }
    static void discover(int v) {
        System.out.print(String.format("D%d,", v));
    }
    static void finish(int v) {
        System.out.print(String.format("F%d,", v));
    }

    static Integer getFirstUnVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return null;
    }

}
