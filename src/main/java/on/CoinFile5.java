package on;

import java.util.HashMap;
import java.util.Map;

class CoinApp5 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp5 app = new CoinApp5();
        int i = app.coinChange(new int[]{1, 2, 5}, 100);
    }

    public int coinChange(int[] coins, int amount) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        coinDfs(amount, 0, amount, 0, coins, dp);
        return dp.get(amount) == null ? -1 : dp.get(amount);
    }

    void coinDfs(int parentRemain, int parentCoin,
                 int remain, int selectedSize, int[] coins,
                 Map<Integer, Integer> dp) {
        System.out.println("enter fun"+remain);

        if (remain == 0) {
            Integer minSize = dp.get(remain);
            if (minSize == null) {
                dp.put(remain, selectedSize);
                return;
            } else {
                int retMinSize = Math.min(selectedSize, minSize);
                dp.put(remain, retMinSize);
                return;
            }
        } else if (remain < 0) {
            dp.put(remain, -1);
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
