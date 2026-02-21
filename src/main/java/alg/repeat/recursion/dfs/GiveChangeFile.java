package alg.repeat.recursion.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class GiveChangeApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    List<List<Integer>> startBackTrack(int[] coins, int sum) {
//        Arrays.sort(coins);
        int[] sortCoins = IntStream.of(coins)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        List<List<Integer>> result = new ArrayList<>();
        backtrack(sortCoins, sum, result);
        return result;
    }

    void backtrack(int[] sortCoins, int remain, List<List<Integer>> result) {

    }

}
