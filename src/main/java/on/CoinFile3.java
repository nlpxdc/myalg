package on;

import java.util.Arrays;
import java.util.Collections;

class CoinApp3 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp3 app = new CoinApp3();
        int i = app.coinChange(new int[]{2}, 3);
    }

    public int coinChange(int[] coins, int amount) {
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
                minSizeRef.set(Math.min(currMinSize, selectedSize));
            }
        }

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coin > remain) {
                continue;
            }
            coinDfs(coins, remain-coin, selectedSize+1, minSizeRef);
        }

    }

}
