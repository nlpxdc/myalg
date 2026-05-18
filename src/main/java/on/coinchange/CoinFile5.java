package on.coinchange;

import java.util.Arrays;

class CoinApp5 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp5 app = new CoinApp5();
        int i = app.coinChange(new int[]{5,2,1}, 11);
    }

    private int[] memo;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        int ret = coinDfs(coins, amount);
        return ret;
    }

    int coinDfs(int[] coins, int target) {
        if (target == 0) return 0;
        if (target < 0) return -1;
        if (memo[target] != -2) return memo[target];  // 查缓存

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinDfs(coins, target - coin);
            if (res != -1) {
                min = Math.min(min, res + 1);
            }
        }

        memo[target] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[target];
    }

}
