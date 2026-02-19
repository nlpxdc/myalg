package alg.paradigm.opowernbrute.math.nested.backtrack;

class GiveChangeApp {

    static int[] coins = {1,3,4};

    public static void main(String[] args) {
        dfs(6);
    }

    static void dfs(int maxDepth) {
        for (int coin : coins) {
            dfsRecur(coin, 1, maxDepth);
            System.out.println();
        }
    }

    static void dfsRecur(int currCoin, int currDepth, int maxDepth) {
        if (currDepth > maxDepth) {
            return;
        }
        if (currDepth < maxDepth) {
            System.out.print(currCoin+",");
        } else if (currDepth == maxDepth) {
            System.out.println(currCoin+",");
        }

        for (int coin : coins) {
            dfsRecur(coin, currDepth+1, maxDepth);
        }
    }

}
