package alg.repeat.recurrence.dfs.example;

import java.util.Arrays;

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

class LongestPalindromeSubstringApp2 {

    public static void main(String[] args) {
        LongestPalindromeSubstringApp2 app = new LongestPalindromeSubstringApp2();
        String str = app.longestPalindrome("abb");
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        //奇偶预处理
        String preStr = preHandleStr(s);

        //中心点循环枚举
        int max = 0;
        int[] maxObj = new int[]{max, 0, 0};
        for (int midIdx = 0; midIdx < preStr.length(); midIdx++) {
            int[] midMaxObj = bePalindrome(preStr, midIdx);
            if (midMaxObj[0] > max) {
                max = midMaxObj[0];
                maxObj = midMaxObj;
            }
        }

        //中心扩散判断，同时剪枝
        if (maxObj[0] > 0) {
            String substring = preStr.substring(maxObj[1], maxObj[2] + 1);
            String replace = substring.replace("#", "");
            return replace;
        } else {
            return s.substring(0,1);
        }
    }

    String preHandleStr(String s) {

        char[] charAry = new char[s.length() + s.length() + 1];
        charAry[0] = '#';
        for (int i = 0; i < s.length(); i++) {
            charAry[2*i+1] = s.charAt(i);
        }
        for (int i = 0; i < s.length()-1; i++) {
            charAry[2*i+2] = '#';
        }
        charAry[charAry.length-1] = '#';
        return new String(charAry);
    }

    int[] bePalindrome(String s, int midIdx) {
        int max = 0;
        int[] maxObj = new int[]{max, midIdx, midIdx};
        for (int leftIdx = midIdx, rightIdx = midIdx;
             leftIdx >= 0 && rightIdx <= s.length()-1;
             leftIdx--,rightIdx++) {
            char leftVal = s.charAt(leftIdx);
            char rightVal = s.charAt(rightIdx);
            if (leftVal == rightVal) {
                ++max;
                maxObj[0] = max;
                maxObj[1] = leftIdx;
                maxObj[2] = rightIdx;
            } else {
                break;
            }
        }
        return maxObj;
    }

}
