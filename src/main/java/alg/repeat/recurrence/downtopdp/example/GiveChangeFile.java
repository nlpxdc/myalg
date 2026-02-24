package alg.repeat.recurrence.downtopdp.example;

import java.util.Arrays;

class GiveChangeApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int i = giveChange(new int[]{1,2, 3, 4}, 9);
    }
    //table tab dpTab dp lineTab treeTab

    static int giveChange(int[] coins, int sum) {
        Arrays.sort(coins);

        int[] dp = new int[sum+1];
        Arrays.fill(dp, sum+1);
        dp[0] = 0;

        //orig sum
        /* sortCoins */ /* int coinStart */
        /* int remain = sum */ /* int[] dp */
        /*pathCnt 不需要*/
        /* int minCnt = sum+1; */
        //循环从小到大，自底向上分别求值，并记录缓存结果
        for (int i = 1; i <= sum; i++) {
            int iMinCnt = dp[i];
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin > i) {
                    break;
                }
                iMinCnt = Math.min(iMinCnt, 1 + dp[i-coin]);
            }
            dp[i] = iMinCnt;
        }
        return dp[sum];
    }

}
