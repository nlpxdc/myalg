package leetcode.simple.p0009palindromenumber;

class PalindromeNumberApp {
    public static void main(String[] args) {
        System.out.println("aa");
        PalindromeNumberApp app = new PalindromeNumberApp();
        int x = 13562478;
        int i = app.digitCount(x);
//        boolean palindrome = app.isPalindrome(x);

    }

    public boolean isPalindrome(int x) {
//        Integer boxedX = new Integer(x);
//        String xStr = boxedX.toString();
        String xStr = String.valueOf(x);
        char[] xCharAry = xStr.toCharArray();
        for (int i = 0, j = xCharAry.length-1; i < xCharAry.length/2 && j >= xCharAry.length/2; i++, j--) {
            if (xCharAry[i] != xCharAry[j]) {
                return false;
            }
        }
        return true;
    }

    public int digitCount(int x) {
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
