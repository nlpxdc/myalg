package leetcode.medium.p0007reverseint;

class ReverseIntApp {
    public static void main(String[] args) {
        int reverse = reverse(-2147483648);
    }

    public static int reverse(int x) {
        long longX = x;
        long reverseInt = 0;
        if (longX == 0) {
            return 0;
        } else if (longX > 0) {
            for ( ; longX != 0; longX/=10) {
                long mod = longX%10L;
                reverseInt = reverseInt*10L+mod;
            }
            if (reverseInt > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) reverseInt;
        } else {
            for (longX = Math.abs(longX) ; longX != 0; longX/=10) {
                long mod = longX%10L;
                reverseInt = reverseInt*10L+mod;
            }
            if (reverseInt > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) -reverseInt;
        }
    }
}
