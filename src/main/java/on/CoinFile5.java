package on;

import java.util.HashMap;
import java.util.Map;

class CoinApp5 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp5 app = new CoinApp5();
        int i = app.coinChange(new int[]{5,2,1}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0,0);
        coinDfs(amount, 0, amount, 0, coins, dp);
        return dp.get(amount) == null ? -1 : dp.get(amount);
    }

    void coinDfs(int parentRemain, int parentCoin,
                 int remain, int selectedSize, int[] coins,
                 Map<Integer, Integer> dp) {
        System.out.println("enter fun"+remain);

        if (remain == 0) {
            Integer minSize = dp.get(parentRemain);
            if (minSize == null) {
                dp.put(parentRemain, 1);
                return;
            } else {
                Math.min(minSize, s)
            }
            dp.put(parentRemain, )
            return;
        } else if (remain < 0) {
            dp.put(parentRemain, -1);
            return;
        } else {
            Integer minSize = dp.get(remain);
            if (minSize != null) {
                return;
            }
            for (int i = 0; i < coins.length; i++) {
                int coin = coins[i];
                coinDfs(remain, coin, remain-coin, selectedSize+1, coins, dp);
            }
        }
    }

}
