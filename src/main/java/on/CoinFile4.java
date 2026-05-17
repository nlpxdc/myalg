package on;

import java.util.HashMap;
import java.util.Map;

class CoinApp4 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp4 app = new CoinApp4();
        int i = app.coinChange(new int[]{1, 2, 5}, 100);
    }

    public int coinChange(int[] coins, int amount) {
        int ret = coinDfs(coins, amount);
        return ret;
    }

    int coinDfs(int[] coins, int remain) {
        if (remain == 0) return 0;
        if (remain < 0) return -1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int min0 = coinDfs(coins, remain - coin);
            if (min0 != -1) min = Math.min(min, min0 + 1);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
