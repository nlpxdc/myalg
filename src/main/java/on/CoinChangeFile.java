package on;

class CoinChangeApp {
    public static void main(String[] args) {

        System.out.println("aa");
        CoinChangeApp app = new CoinChangeApp();
        int minSize = app.coinDfs(new int[]{1, 2, 5}, 11);
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
