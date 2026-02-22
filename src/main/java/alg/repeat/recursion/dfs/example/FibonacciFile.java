package alg.repeat.recursion.dfs.example;

class FibonacciApp {
    public static void main(String[] args) {
        System.out.println("aa");
        for (int i = 0; i < 100; i++) {
            long val = fibonacci(i);
            if (val < 0) {
                System.out.println("end break");
                break;
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
                break;
            }
            System.out.println(String.format("%d->%d", i, val));
        }
    }

    static long fibonacci(int n) {
        long[] linearDp = new long[10000];
        return dfs(n, linearDp);
    }

    static long dfs(int n, long[] linearDp) {
        long cache = linearDp[n];
        if (cache > 0) {
//            System.out.println(String.format("n:%d, cache:%d", n, cache));
            return cache;
        }
        if (n == 0) {
            linearDp[0] = 0;
            return 0;
        } else if (n == 1) {
            linearDp[1] = 1;
            return 1;
        } else if (n >= 2) {
            long result = dfs(n-2, linearDp) + dfs(n-1, linearDp);
            linearDp[n] = result;
            return result;
        }
        return -1;
    }

}
