package alg.repeat.recurrence.tabulationdp.example;

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
        if (n < 1) throw new RuntimeException();
        if (n == 1) return 1;
        if (n >= 2) {
            long prev = 1;
            for (int i = 2; i <= n; i++) {
                prev *= i;
            }
            return prev;
        }
        return -1;
    }

}
