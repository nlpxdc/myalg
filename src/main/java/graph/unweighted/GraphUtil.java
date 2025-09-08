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

    //这里的n是graph的metainfo，且必须是final的不能修改，可以改成单一对象，对象字段也是final的不能改变，因为都是元信息
    //但是这里因为目前只需要一个图的顶点总数即可，所以不额外定义对象了，如果两个或多于两个元信息，那么可以额外定义对象，这里不做复杂处理了
    public static int traverse(final int n, BiConsumer<Integer, TraverseTemp> strategy) {
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
