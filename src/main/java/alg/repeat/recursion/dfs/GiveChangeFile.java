package alg.repeat.recursion.dfs;

import java.util.*;
import java.util.stream.IntStream;

class GiveChangeApp {
    public static void main(String[] args) {
        System.out.println("aa");
        List<List<Integer>> lists = startBackTrack(new int[]{1, 3, 4}, 6);
    }

    static List<List<Integer>> startBackTrack(int[] coins, int sum) {
        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(coins);
//        int[] sortCoins = IntStream.of(coins)
//                .boxed()
//                .sorted(Collections.reverseOrder())
//                .mapToInt(Integer::intValue)
//                .toArray();
        List<Integer> path = new ArrayList<>();
        backtrack(coins, sum, path, result);
        return result;
    }

    static void backtrack(int[] sortCoins, int remain, List<Integer> path, List<List<Integer>> result) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            for (int coin : sortCoins) {
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, depRemain, path, result);
                path.remove(path.size()-1);
            }
        }
    }

}

class GiveChangeApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
        Set<List<Integer>> lists = startBackTrack(new int[]{1, 3, 4}, 6);
    }

    static Set<List<Integer>> startBackTrack(int[] coins, int sum) {
        Set<List<Integer>> result = new HashSet<>();
//        Arrays.sort(coins);
//        int[] sortCoins = IntStream.of(coins)
//                .boxed()
//                .sorted(Collections.reverseOrder())
//                .mapToInt(Integer::intValue)
//                .toArray();
        List<Integer> path = new ArrayList<>();
        backtrack(coins, sum, path, result);
        return result;
    }

    static void backtrack(int[] sortCoins, int remain, List<Integer> path, Set<List<Integer>> result) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            List<Integer> copyPath = new ArrayList<>(path);
            Collections.sort(copyPath);
            result.add(copyPath);
            return;
        } else {
            for (int coin : sortCoins) {
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, depRemain, path, result);
                path.remove(path.size()-1);
            }
        }
    }

}

class GiveChangeApp3 {
    public static void main(String[] args) {
        System.out.println("aa");
        Set<List<Integer>> lists = startBackTrack(new int[]{1, 3, 4}, 6);
    }

    static Set<List<Integer>> startBackTrack(int[] coins, int sum) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(coins);
        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, path, result);
        return result;
    }

    static void backtrack(int[] sortCoins, int coinStart, int remain, List<Integer> path, Set<List<Integer>> result) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, path, result);
                path.remove(path.size()-1);
            }
        }
    }

}

class GiveChangeApp4 {

    static List<Integer> minCntList;

    public static void main(String[] args) {
        System.out.println("aa");
        List<Integer> list = giveChange(new int[]{1, 3, 4}, 6);
    }

    static List<Integer> giveChange(int[] coins, int sum) {
//        List<Integer> result = null;
        Arrays.sort(coins);
        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, path);
        return minCntList;
    }

    static void backtrack(int[] sortCoins, int coinStart, int remain, List<Integer> path) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            if (minCntList == null) {
                minCntList = new ArrayList<>(path);
            } else {
                minCntList = path.size() < minCntList.size() ? new ArrayList<>(path) : minCntList;
            }
            return;
        } else {
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, path);
                path.remove(path.size()-1);
            }
        }
    }

}
