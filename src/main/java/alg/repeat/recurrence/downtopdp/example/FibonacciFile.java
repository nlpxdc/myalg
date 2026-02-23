package alg.repeat.recurrence.downtopdp.example;

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
            long prev2Tab = 0, prev1Tab = 1;
            for (int i = 2; i < n; i++) {
                long t = prev1Tab;
                prev1Tab += prev2Tab;
                prev2Tab = t;
            }
            return prev2Tab + prev1Tab;
        }

        return -1;
    }

}
