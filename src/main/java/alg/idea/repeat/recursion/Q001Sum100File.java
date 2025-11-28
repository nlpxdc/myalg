package alg.idea.repeat.recursion;

class Q001Sum100App {
    public static void main(String[] args) {
        int result = fn(100);
    }

    static int fn(int n) {
        if (n < 0) {
            throw new RuntimeException("invalid param");
        } else if (n == 0) {
            return 0;
        } else {
            int result = fn(n-1) + n;
            return result;
        }
    }

}
