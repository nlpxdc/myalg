package alg.repeat.recurrence.tabulationdp.example;

class FibonacciApp {
    public static void main(String[] args) {
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
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n >= 2) {
            long prev2Dp = 0, prev1Dp = 1;
            for (int i = 2; i < n; i++) {
                long t = prev1Dp;
                prev1Dp += prev2Dp;
                prev2Dp = t;
            }
            return prev2Dp + prev1Dp;
        }

        return -1;
    }

}
