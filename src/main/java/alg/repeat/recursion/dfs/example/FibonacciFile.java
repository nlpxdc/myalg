package alg.repeat.recursion.dfs.example;

class FibonacciApp {
    public static void main(String[] args) {
        System.out.println("aa");
        for (int i = 0; i < 100; i++) {
            long val = fibonacci(i);
            if (val < 0) {
                System.out.println("end break");
            }
            System.out.println(String.format("%d->%d", i, val));
        }
    }

    static long fibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n >= 2) return fibonacci(n-2)+fibonacci(n-1);
        return -1;
    }

}

class FibonacciApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
        for (int i = 0; i < 100; i++) {
            long val = fibonacci(i);
            if (val < 0) {
                System.out.println("end break");
            }
            System.out.println(String.format("%d->%d", i, val));
        }
    }

    static long fibonacci(long n) {
        long[] dp = new long[10000];
        return dfs(n, dp);
    }

    static long dfs(long n, long[] dp) {
        long cache = dp[(int) n];
        if (cache > 0) {
            return cache;
        }
        if (n == 0) {
            dp[0] = 0;
            return 0;
        } else if (n == 1) {
            dp[1] = 1;
            return 1;
        } else if (n >= 2) {
            return dfs(n-2, dp) + dfs(n-1, dp);
        }
        return -1;
    }

}
