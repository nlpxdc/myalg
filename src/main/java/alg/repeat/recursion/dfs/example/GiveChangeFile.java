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
