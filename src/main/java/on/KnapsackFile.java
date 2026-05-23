package on;

import java.util.Arrays;

class KnapsackApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack (int V, int n, int[][] vw) {
        int max = knapsackDfs(vw, 0, V);
        return max;
    }

    int knapsackDfs(int[][] vw, int idx, int remain) {
        if (idx >= vw.length) return 0;

        int[] obj = vw[idx];
        int v = obj[0];
        int w = obj[1];

        int notPick = knapsackDfs(vw, idx + 1, remain);

        int pick = 0;
        if (v <= remain) {
            pick = Math.max(pick, knapsackDfs(vw, idx+1, remain-v)+w);
        }

        return Math.max(notPick, pick);
    }

}

class KnapsackApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int knapsack (int V, int n, int[][] vw) {
        int[][] dp = new int[vw.length][V+1];
        int max = knapsackDfs(vw, 0, V, dp);
        return max;
    }

    int knapsackDfs(int[][] vw, int idx, int remain, int[][] dp) {
        if (idx >= vw.length) return 0;

        if (dp[idx][remain] != 0) return dp[idx][remain];

        int[] obj = vw[idx];
        int v = obj[0];
        int w = obj[1];

        int notPick = knapsackDfs(vw, idx + 1, remain, dp);

        int pick = 0;
        if (remain >= v) {
            int depPick = knapsackDfs(vw, idx + 1, remain - v, dp);
            pick = Math.max(pick, depPick+w);
        }


        return dp[idx][remain] =  Math.max(notPick, pick);
    }

}
