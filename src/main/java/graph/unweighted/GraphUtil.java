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
    //第二个参数，是遍历方式，是函数是方法，遍历某个点开始的连通图内的所有节点，函数的参数都是单个连通图遍历所需的数据
    //函数的第一个值，是真正的参数，是一个单连通图的起始点startV，这里可以是任意的，和上面同理，也可以是个对象，如果需要多个字段时，这里简单处理
    //另外一个值就是临时变量，放两个重要内容，一个是记录节点的被访问状态数组，另一个是顶点被访问的总次数，这里因为有两个需要记录的变脸，所以定义对象了
    //但是如果只给被访问的数组也是可以的够的，但是给出visitCnt会更好，这是针对元数据n的临时变量，可以控制总体循环逻辑，避免写错进入无限死循环
    //上面不要对象了，visitCnt其实是个计算属性，根据visited数组可以算出来的
    public static int traverse(final int n, BiConsumer<Integer, boolean[]> maxConnectedChildGraphTraverse) {
        //初始化临时数组，记录访问状态
        boolean[] visited = new boolean[n];

        int maxConnectedChildGraphCnt = 0;
        for (int i = 0; i < n; i++) {
            Integer firstUnVisited = getFirstUnVisited(visited);
            if (firstUnVisited != null) {
                maxConnectedChildGraphCnt++;
                maxConnectedChildGraphTraverse.accept(firstUnVisited, visited);
                System.out.println();
            } else {
                break;
            }

        }
        return maxConnectedChildGraphCnt;
    }

}
