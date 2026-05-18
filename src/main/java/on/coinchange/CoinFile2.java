package on.coinchange;

import java.util.*;

class CoinApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
        CoinApp2 app = new CoinApp2();
//        int[] coins = {1,2,5};
        int i = app.coinChange(new int[]{1,2,5}, 100);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
//        //降序排列
//        int[] descDenos = Arrays.stream(coins)
//                .boxed()
//                .sorted(Collections.reverseOrder())
//                .mapToInt(Integer::intValue)
//                .toArray();
        Arrays.sort(coins);
        List<Integer> choseCoinList = new ArrayList<>();
        List<List<Integer>> retList = new ArrayList<>();

        coinDfs(coins, choseCoinList, amount, retList);

//        List<Integer> list = retList.stream()
//                .min(Comparator.comparingInt(List::size))
//                .orElse(Collections.emptyList());

        int minSize = retList.stream()
                .mapToInt(List::size)
                .min()
                .orElse(-1);
//        List<List<Integer>> minList = retList.stream().filter(t -> t.size() == minSize).collect(Collectors.toList());

        return minSize;
    }

    //coins 定义结构，事先降序排列，用贪心法，可以提前剪枝不可能范围
    //choseCoins，当前状态枚举 引用类型
    //remain 临时参数，输入
    //retList 返回数据，克隆类型，来源choseCoins
    void coinDfs(int[] coins, List<Integer> choseCoins, int remain, List<List<Integer>> retList) {
        //当前状态choseCoins，计算下一值，组成下一状态
        //可选值 remain减去choseCoins的
        //正好匹配，结果值深拷贝后放入结果容器中
        if (remain == 0) {
            retList.add(new ArrayList<>(choseCoins));
            return;
        } else if (remain < 0) {
            return;
        }
        //可以选中过滤出很多个可能
        //降序拿第一个就好
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coin > remain) {
                break;
            }
            choseCoins.add(coin);
            coinDfs(coins, choseCoins, remain-coin, retList);
            choseCoins.remove(choseCoins.size()-1);
        }

    }

}
