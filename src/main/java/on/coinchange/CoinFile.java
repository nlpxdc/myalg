package on.coinchange;

import java.util.*;

class CoinApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int coin = coin(46);
    }

    static int[] dimos = {5,1,8,30,10};

    static int coin(int target) {
        List<List<Integer>> lists = coin0(target);
        List<Integer> list = lists.stream().min(Comparator.comparingInt(List::size)).orElse(Collections.emptyList());

        int ret = lists.stream().mapToInt(List::size).min().orElse(0);

        return list.size(); //ret
    }

    static List<List<Integer>> coin0(int target) {
        List<List<Integer>> result = new ArrayList<>();
        int[] descDimos = Arrays.stream(dimos)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        ArrayList<Integer> choseCoins = new ArrayList<>();
        coinDfs(descDimos, choseCoins, target, result);

        return result;
    }

    static void coinDfs(int[] descDimos, List<Integer> chosenCoins, int remain, List<List<Integer>> result) {

    }

}
