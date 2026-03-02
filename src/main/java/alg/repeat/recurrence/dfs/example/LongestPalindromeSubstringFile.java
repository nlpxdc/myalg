package alg.repeat.recurrence.dfs.example;

class LongestPalindromeSubstringApp {

    public static void main(String[] args) {
        LongestPalindromeSubstringApp app = new LongestPalindromeSubstringApp();
        String str = app.longestPalindrome("babad");
    }

    public String longestPalindrome(String s) {
        int max = 0;
        int retLeftIdx = 0;
        int retRightIdx = 0;
        for (int startIdx = 0; startIdx < s.length(); startIdx++) {
            for (int endIdx = startIdx; endIdx < s.length(); endIdx++) {
                boolean bePalindrome = bePalindrome(s, startIdx, endIdx);
                if (bePalindrome) {
                    int cnt = endIdx - startIdx + 1;
                    if (cnt > max) {
                        max = cnt;
                        retLeftIdx = startIdx;
                        retRightIdx = endIdx;
                    }
                }
            }
        }
        return s.substring(retLeftIdx, retRightIdx+1);
    }

    boolean bePalindrome(String s, int startIdx, int endIdx) {
        for (int leftIdx = startIdx, rightIdx = endIdx;
             leftIdx < s.length() && rightIdx < s.length() && leftIdx <= rightIdx;
             leftIdx++, rightIdx--) {
            char leftVal = s.charAt(leftIdx);
            char rightVal = s.charAt(rightIdx);
            if (leftVal != rightVal) {
                return false;
            }
        }
        return true;
    }

}
