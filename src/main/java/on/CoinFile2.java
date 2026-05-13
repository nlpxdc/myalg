package on;

import java.util.*;
import java.util.stream.Collectors;

class CoinApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int coinChange(int[] coins, int amount) {
        //降序排列
        int[] descDenos = Arrays.stream(coins)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        List<Integer> choseCoinList = new ArrayList<>();
        List<List<Integer>> retList = new ArrayList<>();

        coinDfs(descDenos, choseCoinList, amount, retList);

//        List<Integer> list = retList.stream()
//                .min(Comparator.comparingInt(List::size))
//                .orElse(Collections.emptyList());

        int minSize = retList.stream()
                .mapToInt(List::size)
                .min()
                .orElse(0);
//        List<List<Integer>> minList = retList.stream().filter(t -> t.size() == minSize).collect(Collectors.toList());

        return minSize;
    }

    void coinDfs(int[] coins, List<Integer> choseCoins, int remain, List<List<Integer>> retList) {

    }

}
