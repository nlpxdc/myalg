package graph.unweighted.undirected;

import java.util.function.BiConsumer;

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

    static void gTraverse(int n, BiConsumer<Integer, boolean[]> strategy) {
        //初始化临时数组，记录访问状态
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = GraphUtil.getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                strategy.accept(firstUnVisited, visited);
                System.out.println();
            } else {
                break;
            }

        }
    }

}
