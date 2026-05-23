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

class CoinChangeApp2a {
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
            if (remain >= coin) {
                int depMinSize = coinDfs(coins, remain - coin);
                if (depMinSize != -1) minSize = Math.min(minSize, depMinSize+1);
            }

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

class CoinChangeApp3 {
    public static void main(String[] args) {

        System.out.println("aa");
        CoinChangeApp3 app = new CoinChangeApp3();
        int minSize = app.coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        //排列
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

}

class CoinChangeApp4 {
    public static void main(String[] args) {

        System.out.println("aa");
        CoinChangeApp4 app = new CoinChangeApp4();
        int minSize = app.coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        //组合
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin]+1);
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

}
