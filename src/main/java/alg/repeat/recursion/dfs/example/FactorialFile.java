package alg.repeat.recursion.dfs.example;

class FactorialApp {
    public static void main(String[] args) {
        System.out.println("aa");
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            long val = factorial(i);
            if (val < 0) {
                System.out.println("end break");
                break;
            }
            System.out.println(String.format("%d->%d", i, val));
        }
    }

    static long factorial(int n) {
        return dfs(n);
    }

    static long dfs(int n) {
        if (n < 1) throw new RuntimeException();
        if (n == 1) return 1;
//        if (n >= 2) return n * dfs(n-1);
        if (n >= 2) return n + dfs(n-1);
        return -1;
    }

}

class FactorialApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            long val = factorial(i);
            if (val < 0) {
                System.out.println("end break");
                break;
            }
            System.out.println(String.format("%d->%d", i, val));
        }
    }

    static long factorial(int n) {
        long[] dp = new long[100000];
        return dfs(n, dp);
    }

    static long dfs(int n, long[] dp) {
        if (n < 1) throw new RuntimeException();
        long cache = dp[n];
        if (cache > 0) return cache;
        if (n == 1) return 1;
//        if (n >= 2) return n * dfs(n-1);
        if (n >= 2) return n + dfs(n-1, dp);
        return -1;
    }

}
