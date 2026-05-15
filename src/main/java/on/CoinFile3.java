package on;

import java.util.Arrays;
import java.util.Collections;

class CoinApp3 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp3 app = new CoinApp3();
        int i = app.coinChange(new int[]{1}, 0);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] descCoins = Arrays.stream(coins).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        ThreadLocal<Integer> minSizeRef = new ThreadLocal<>();
        coinDfs(descCoins, amount, 0, minSizeRef);
        return minSizeRef.get() == null ? -1 : minSizeRef.get();
    }

    void coinDfs(int[] coins,
                int remain, int selectedSize,
                ThreadLocal<Integer> minSizeRef) {
        if (remain == 0) {
            Integer currMinSize = minSizeRef.get();
            if (currMinSize == null) {
                minSizeRef.set(selectedSize);
            } else {
                int retMinSize = Math.min(currMinSize, selectedSize);
                minSizeRef.set(retMinSize);
            }
        } else {
            for (int i = 0; i < coins.length; i++) {
                int coin = coins[i];
                coinDfs(coins, remain-coin, selectedSize+1, minSizeRef);
            }
        }
    }

}
