package leetcode.simple.p0009palindromenumber;

class PalindromeNumberApp {
    public static void main(String[] args) {
        System.out.println("aa");
        PalindromeNumberApp app = new PalindromeNumberApp();
        int maxValue = Integer.MAX_VALUE;
        int x = 10;
        int i = app.digitCount(x);
        boolean palindrome = app.isPalindrome(x);


    }

    public boolean isPalindrome(int x) {
        if (0<=x && x<= 9) {
            return true;
        } else if (x < 0) {
            return false;
        } else if (x %10 == 0) {
            return false;
        } else {
            int reverseNum = 0;
            if (x > reverseNum) {
                reverseNum =
                x /= 10;
            }
            return true;
        }

    }

    static int reverse(int x) {
        long rev = 0;                // 用 64 位防止溢出
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (rev < Integer.MIN_VALUE || rev > Integer.MAX_VALUE) ? 0 : (int) rev;
    }

    public boolean isPalindromeV1(int x) {
        if (0<=x && x<= 9) {
            return true;
        } else if (x < 0) {
            return false;
        } else {
            int cnt = digitCount(x);
            for (int i = 0, j = cnt-1; i < cnt/2 && j >= cnt/2; i++, j--) {
                if (digitAt(x, i) != digitAt(x, j)) {
                    return false;
                }
            }
            return true;
        }

    }

    static final int[] POW10 = {              // 10^0 … 10^9
            1, 10, 100, 1000, 10000, 100000,
            1000000, 10000000, 100000000, 1000000000
    };
    static int digitAt(int n, int k) {        // k=0 是个位
        if (n < 0) n = -n;
        return (n / POW10[k]) % 10;           // 1 次查表 + 1 次除 + 1 次模
    }

    public int digitCount(int n) {
        if (n < 0) n = -n;
        int l = 0, r = 9;                 // 共 10 个元素
        while (l <= r) {
            int m = (l + r) >>> 1;
            if (n >= POW10[m]) l = m + 1;
            else               r = m - 1;
        }
        return l;
    }

    public int digitCount2(int x) {
        int cnt = 0;
        for (; x > 0; ) {
            x /= 10;
            cnt++;
        }
        return cnt;
    }

//    for (char c : String.valueOf(n).toCharArray()) {
//        int d = c - '0';
//    ...
//    }

}
