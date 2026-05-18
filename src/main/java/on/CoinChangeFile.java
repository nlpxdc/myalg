package on;

import java.util.Arrays;

class CoinChangeApp {
    public static void main(String[] args) {

        System.out.println("aa");
        CoinChangeApp app = new CoinChangeApp();
        int minSize = app.coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        int minSize = coinDfs(coins, amount);
        return minSize;
    }

    int coinDfs(int[] coins, int remain) {
        if (remain == 0) return 0;
        if (remain < 0) return -1;

        int minSize = remain+1;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int depMinSize = coinDfs(coins, remain - coin);
            if (depMinSize != -1) minSize = Math.min(minSize, depMinSize+1);
        }

        return minSize == remain+1 ? -1 : minSize;
    }

}

class CoinChangeApp2 {
    public static void main(String[] args) {

        System.out.println("aa");
        CoinChangeApp2 app = new CoinChangeApp2();
        int minSize = app.coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        dp[0] = 0;
        int minSize = coinDfs(coins, amount, dp);
        return minSize;
    }

    int coinDfs(int[] coins, int remain, int[] dp) {
        if (remain == 0) return 0;
        if (remain < 0) return -1;
        if (dp[remain] != -2) return dp[remain];

        int minSize = remain+1;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int depMinSize = coinDfs(coins, remain - coin, dp);
            if (depMinSize != -1) minSize = Math.min(minSize, depMinSize+1);
        }

        return dp[remain] = minSize == remain+1 ? -1 : minSize;
    }

}
