package alg.repeat.recursion.dfs.example;

import java.lang.ref.Reference;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.IntStream;

//backtrack
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

        Arrays.sort(coins);
        List<Integer> path = new ArrayList<>();
        Set<List<Integer>> result = new HashSet<>();
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

    public static void main(String[] args) {
        System.out.println("aa");
        List<Integer> list = giveChange(new int[]{1, 3, 4}, 6);
    }

    static List<Integer> giveChange(int[] coins, int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        ThreadLocal<List<Integer>> minCntListRef = new ThreadLocal<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, path, minCntListRef);
        return minCntListRef.get();
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static void backtrack(int[] sortCoins, int coinStart, int remain, List<Integer> path, ThreadLocal<List<Integer>> minCntListRef) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        //prune
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            if (minCntListRef.get() == null) {
//                minCntList = new ArrayList<>(path);
                minCntListRef.set(new ArrayList<>(path));
            } else {
//                minCntList = path.size() < minCntList.size() ? new ArrayList<>(path) : minCntList;
                List<Integer> minCntList = minCntListRef.get();
                minCntListRef.set(path.size() < minCntList.size() ? new ArrayList<>(path) : minCntList);
            }
            return;
        } else {
            //bound
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, path, minCntListRef);
                path.remove(path.size()-1);
            }
        }
    }

}

class GiveChangeApp5 {

    public static void main(String[] args) {
        System.out.println("aa");
        Set<List<Integer>> set = giveChange(new int[]{1,2, 3, 4}, 5);
    }

    static Set<List<Integer>> giveChange(int[] coins, int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        ThreadLocal<Set<List<Integer>>> minCntListSet = new ThreadLocal<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, path, minCntListSet);
        return minCntListSet.get();
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static void backtrack(int[] sortCoins, int coinStart, int remain, List<Integer> path, ThreadLocal<Set<List<Integer>>> minCntListSetRef) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        //prune
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            Set<List<Integer>> minCntListSet = minCntListSetRef.get();
            if (minCntListSet == null || minCntListSet.isEmpty()) {
                minCntListSetRef.set(new HashSet<>(Collections.singletonList(new ArrayList<>(path))));
            } else {
                List<Integer> firstMinCntList = minCntListSet.stream().findFirst().orElse(null);
                if (path.size() < firstMinCntList.size()) {
                    minCntListSetRef.set(new HashSet<>(Collections.singletonList(new ArrayList<>(path))));
                } else if (path.size() == firstMinCntList.size()) {
                    minCntListSet.add(new ArrayList<>(path));
                }
            }
            return;
        } else {
            //bound
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                path.add(coin);
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, path, minCntListSetRef);
                path.remove(path.size()-1);
            }
        }
    }

}

class GiveChangeApp6 {

    public static void main(String[] args) {
        System.out.println("aa");
        Integer minCnt = giveChange(new int[]{1, 3, 4}, 6);
    }

    static Integer giveChange(int[] coins, int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        ThreadLocal<Integer> minCntRef = new ThreadLocal<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, 0, minCntRef);
        return minCntRef.get();
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static void backtrack(int[] sortCoins, int coinStart, int remain, Integer pathCnt, ThreadLocal<Integer> minCntRef) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        //prune
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            Integer minCnt = minCntRef.get();
            if (minCnt == null) {
                minCntRef.set(pathCnt);
            } else {
                if (pathCnt < minCnt) {
                    minCntRef.set(pathCnt);
                }
            }
            return;
        } else {
            //bound
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                pathCnt++;
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, pathCnt, minCntRef);
                pathCnt--;
            }
        }
    }

}

class GiveChangeApp6b {

    public static void main(String[] args) {
        System.out.println("aa");
        Integer minCnt = giveChange(new int[]{1, 3, 4}, 6);
    }

    static Integer giveChange(int[] coins, final int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        List<Integer> path = new ArrayList<>();
        int minCnt = backtrack(sum, coins, 0, sum, 0, sum + 1);
        return minCnt;
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static int backtrack(final int sum, int[] sortCoins, int coinStart, int remain, Integer pathCnt, int minCnt) {
        if (sortCoins == null || sortCoins.length == 0) {
            return minCnt;
        }
        //prune
        if (remain < 0) {
            return minCnt;
        } else if (remain == 0) {
            if (minCnt >= sum+1) {
                return pathCnt;
            } else {
                if (pathCnt < minCnt) {
                    return pathCnt;
                }
            }
            return minCnt;
        } else {
            //bound
            if (sortCoins[coinStart] > remain) {
                return minCnt;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                pathCnt++;
                int depRemain = remain - coin;
                minCnt = backtrack(sum, sortCoins, i, depRemain, pathCnt, minCnt);
                pathCnt--;
            }
            return minCnt;
        }
    }
}

class GiveChangeApp6c {

    public static void main(String[] args) {
        System.out.println("aa");
        int minCnt = giveChange(new int[]{1, 3, 4}, 6);
    }

    static int giveChange(int[] coins, final int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
//        List<Integer> path = new ArrayList<>();
        int[] dp = new int[sum+1];
        Arrays.fill(dp, sum+1);
        int minCnt = backtrack(sum, coins, 0, sum, dp, 0, sum + 1);
        return minCnt;
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static int backtrack(final int sum, int[] sortCoins, int coinStart,
                         int remain, int[] dp,
                         int pathCnt,
                         int minCnt) {
        if (sortCoins == null || sortCoins.length == 0) {
            return dp[remain] = minCnt;
        }
        //prune
        if (remain < 0) {
            return minCnt;
        } else if (remain == 0) {
            return dp[remain] = Math.min(pathCnt, minCnt);
        } else {
            //bound
            int cache = dp[remain];
            if (cache < sum+1) {
                return cache;
            }
            if (sortCoins[coinStart] > remain) {
                return dp[remain] = minCnt;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                pathCnt++;
                int depRemain = remain - coin;
                minCnt = Math.min(minCnt, backtrack(sum, sortCoins, i,
                                                    depRemain, dp,
                                                    pathCnt,
                                                    minCnt)) ;
                pathCnt--;
            }
            return minCnt;
        }
    }
}

class GiveChangeApp6d {

    public static void main(String[] args) {
        System.out.println("aa");
        int minCnt = giveChange(new int[]{1, 3, 4}, 6);
    }

    static int giveChange(int[] coins, final int sum) {
        AtomicReference<Integer> minCntRef = new AtomicReference<>();
        minCntRef.set(sum+1);
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
//        List<Integer> path = new ArrayList<>();
        int[] dp = new int[sum+1];
        Arrays.fill(dp, sum+1);
        backtrack(sum, coins, 0, sum, dp, 0, minCntRef);
        return minCntRef.get();
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static void backtrack(final int sum, int[] sortCoins, int coinStart,
                         int remain, int[] dp,
                         int pathCnt,
                         AtomicReference<Integer> minCntRef) {
        Integer minCnt = minCntRef.get();
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        //prune
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            minCnt = Math.min(pathCnt, minCnt);
            dp[remain] = minCnt;
            minCntRef.set(minCnt);
//            if (minCntRef.get() > minCnt) {
//                minCntRef.set(minCnt);
//            }
            return;
        } else {
            //bound
            int cache = dp[remain];
            if (cache < sum+1) {
                return;
            }
            if (sortCoins[coinStart] > remain) {
                dp[remain] = minCnt;
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                pathCnt++;
                int depRemain = remain - coin;
                backtrack(sum, sortCoins, i,
                        depRemain, dp,
                        pathCnt,
                        minCntRef);
                pathCnt--;
            }
        }
    }
}

class GiveChangeApp7 {

    public static void main(String[] args) {
        System.out.println("aa");
        Integer minCnt = giveChange(new int[]{1, 3, 4}, 6);
    }

    static Integer giveChange(int[] coins, int sum) {
//        AtomicReference<List<Integer>> minCntListRef = new AtomicReference<>();
        ThreadLocal<Integer> minCntRef = new ThreadLocal<>();
        Arrays.sort(coins);
//        minCntList = new ArrayList<>(Collections.nCopies(sum, 1));
        int[] linearTab = new int[sum];
        Arrays.fill(linearTab, sum+1);
//        List<Integer> path = new ArrayList<>();
        backtrack(coins, 0, sum, linearTab, sum, 0, minCntRef);
        return minCntRef.get();
    }

    //这里错了，可能存在数量相同的不同组合，所以要定义一个容器，另外赋值逻辑要变一下
    static void backtrack(int[] sortCoins, int coinStart, int remain, int[] linearTab, int sum, Integer pathCnt, ThreadLocal<Integer> minCntRef) {
        if (sortCoins == null || sortCoins.length == 0) {
            return;
        }
        if (coinStart >= sortCoins.length) {
            return;
        }
        int cache = linearTab[remain];
        if (cache < sum+1) {
            return;
        }
        //prune
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            Integer minCnt = minCntRef.get();
            if (minCnt == null) {
                linearTab[remain] = pathCnt;
                minCntRef.set(pathCnt);
            } else {
                if (pathCnt < minCnt) {
                    linearTab[remain] = pathCnt;
                    minCntRef.set(pathCnt);
                }
            }
            return;
        } else {
            //bound
            if (sortCoins[coinStart] > remain) {
                return;
            }
            for (int i = coinStart; i < sortCoins.length; i++) {
                int coin = sortCoins[i];
                pathCnt++;
                int depRemain = remain - coin;
                backtrack(sortCoins, i, depRemain, linearTab, sum, pathCnt, minCntRef);
                pathCnt--;
            }
        }
    }

}

//记录选择信息，进行选择回溯，一个，以及所有

//只给出数量即可，可以不考虑不同组合
