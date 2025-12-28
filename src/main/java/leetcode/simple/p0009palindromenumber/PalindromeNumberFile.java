package leetcode.simple.p0009palindromenumber;

class PalindromeNumberApp {
    public static void main(String[] args) {
        System.out.println("aa");
        PalindromeNumberApp app = new PalindromeNumberApp();
        int x = 121;
        boolean palindrome = app.isPalindrome(x);

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

}
