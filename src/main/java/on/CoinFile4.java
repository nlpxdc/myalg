package on;

import java.util.HashMap;
import java.util.Map;

class CoinApp4 {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int coinChange(int[] coins, int amount) {
        ThreadLocal<Integer> minSizeRef = new ThreadLocal<>();
        coinDfs(amount, 0, amount, 0, coins, minSizeRef);
        return minSizeRef.get() == null ? -1 : minSizeRef.get();
    }

    void coinDfs(int parentRemain, int parentCoin,
                 int remain, int selectedSize, int[] coins,
                 ThreadLocal<Integer> minSizeRef) {
        if (remain == 0) {
            Integer minSize = minSizeRef.get();
            if (minSize == null) {
                minSizeRef.set(selectedSize);
                return;
            } else {
                int retMinSize = Math.min(selectedSize, minSize);
                minSizeRef.set(retMinSize);
                return;
            }
        } else if (remain < 0) {
            return;
        } else {
            for (int i = 0; i < coins.length; i++) {
                int coin = coins[i];
                coinDfs(remain, coin, remain-coin, selectedSize+1, coins, minSizeRef);
            }
        }
    }

}
