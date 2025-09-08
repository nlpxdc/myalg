package graph.unweighted;

import java.util.function.BiConsumer;

public class GraphUtil {
    public static void visit(int v) {
        System.out.print(v+",");
    }
//    static void discover(int v) {
//        System.out.println(String.format("discover %d", v));
//    }
//    static void finish(int v) {
//        System.out.println(String.format("finish %d", v));
//    }
    public static void discover(int v) {
        System.out.print(String.format("D%d,", v));
    }
    public static void finish(int v) {
        System.out.print(String.format("F%d,", v));
    }

    public static Integer getFirstUnVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return null;
    }

//    public static int traverse(int n, BiConsumer<Integer, boolean[]> strategy) {
//        //初始化临时数组，记录访问状态
//        boolean[] visited = new boolean[n];
//
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            Integer firstUnVisited = getFirstUnVisited(visited);
//            if (firstUnVisited != null) {
//                count++;
//                strategy.accept(firstUnVisited, visited);
//                System.out.println();
//            } else {
//                break;
//            }
//
//        }
//        return count;
//    }

    public static int traverse(int n, BiConsumer<Integer, TraverseTemp> strategy) {
        //初始化临时数组，记录访问状态
        TraverseTemp traverseTemp = new TraverseTemp();
        traverseTemp.visited = new boolean[n];
        traverseTemp.visitCnt = 0;

        int count = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(traverseTemp.visited);
            if (firstUnVisited != null) {
                count++;
                strategy.accept(firstUnVisited, traverseTemp);
                System.out.println();
            } else {
                break;
            }

        }
        return count;
    }

}
